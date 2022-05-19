package com.cryptoai.javaapi.binanceconnection.web.controllers;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.web.exception.NonexistentZipFileException;
import com.cryptoai.javaapi.binanceconnection.web.models.InputConfiguration;
import com.cryptoai.javaapi.binanceconnection.web.models.Result;
import com.cryptoai.javaapi.binanceconnection.web.behavior.ResultsBehavior;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import com.cryptoai.javaapi.binanceconnection.binance.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class CryptoRestController {

    private ResultsBehavior resultsBehavior;
    private TrainingHelper trainingHelper;

    @Autowired
    public CryptoRestController(ResultsBehavior resultsBehavior, TrainingHelper trainingHelper){
        this.resultsBehavior = resultsBehavior;
        this.trainingHelper = trainingHelper;
    }

    @GetMapping(path = "/crypto", produces = "application/json")
    public ResponseEntity<List<Result>> getCryptoData(@RequestBody InputConfiguration inputConfiguration,
                                                     HttpServletRequest request) {

        DataFactory.newCryptoDataFromCandlesticks(inputConfiguration.getCurrencyPairSymbol(), inputConfiguration.getStartDate());

        Long randomFileId = NetworkInitializer.initializeNetwork(inputConfiguration.getSeed(), inputConfiguration.getStepsPerTraining());

        List<Result> resultInfo = resultsBehavior.computeResults();

        HttpHeaders headers = new HttpHeaders();

        if(request!=null)
            headers.add("link-to-zip-file", request.getRequestURL() + "/" + randomFileId.toString());

        return new ResponseEntity<>(resultInfo, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/crypto/{id}", produces = "application/zip")
    public @ResponseBody byte[] getNeuralNetwork(@PathVariable long id) throws IOException {

        String fileName = "src/main/resources/network-" + id + ".zip";
        File f = new File(fileName);

        if (Files.exists(Paths.get(fileName))){
            InputStream in = new FileInputStream(fileName);
            return in.readAllBytes();
        }

        throw new NonexistentZipFileException("The file id does not exist - " + id);

    }

}
