package com.veryworks.iyeongjun.seoulair.domain;

/**
 * Created by iyeongjun on 2017. 10. 19..
 */

public class TermData {
    private String title;
    private String contents;
    private int imgSource;
    private String firstContents;
    private String secondContents;

    public TermData() {
        divideContents(getContents());
    }

    public TermData(String title, int imgSource, String contents) {
        this.title = title;
        this.contents = contents;
        this.imgSource = imgSource;
        divideContents(getContents());
    }

    private void divideContents(String str){
        setFirstContents(str.substring(0,30));
        setSecondContents(str.substring(30));
    }
    public String getFirstContents() {
        return firstContents;
    }

    public void setFirstContents(String firstContents) {
        this.firstContents = firstContents;
    }

    public String getSecondContents() {
        return secondContents;
    }

    public void setSecondContents(String secondContents) {
        this.secondContents = secondContents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }
}
