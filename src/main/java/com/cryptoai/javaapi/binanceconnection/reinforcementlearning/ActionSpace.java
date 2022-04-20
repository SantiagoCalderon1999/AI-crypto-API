package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import ai.djl.ndarray.NDList;
import ai.djl.util.RandomUtils;

import java.util.ArrayList;

public class ActionSpace extends ArrayList<NDList> {

    public NDList randomAction(){
        return get(RandomUtils.nextInt());
    }
}
