package com.veryworks.iyeongjun.seoulair.domain;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class NewsData {
    private static NewsData instance;
    private Data data;
    private NewsData(){

    }

    public static NewsData getInstance(){
        if (instance ==null){
            instance = new NewsData();
        }
        return instance;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
