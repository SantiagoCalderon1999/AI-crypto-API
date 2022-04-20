package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.evaluator.Accuracy;
import ai.djl.training.initializer.NormalInitializer;
import ai.djl.training.listener.TrainingListener;
import ai.djl.training.loss.Loss;
import ai.djl.training.optimizer.Adam;
import ai.djl.training.tracker.Tracker;
import com.cryptoai.javaapi.binanceconnection.entity.Result;

public class TrainStock {

    public void Train(){
        DefaultTrainingConfig config = setupTrainingConfig();


        Result result = null;
    }

    public static DefaultTrainingConfig setupTrainingConfig(){
        return new DefaultTrainingConfig(Loss.l2Loss())
                .optOptimizer(Adam.builder().optLearningRateTracker(Tracker.fixed(1e-6f)).build())
                .addEvaluator(new Accuracy())
                .optInitializer(new NormalInitializer())
                .addTrainingListeners(TrainingListener.Defaults.basic());
    }
}
