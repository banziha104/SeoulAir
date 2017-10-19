package com.veryworks.iyeongjun.seoulair.domain;

/**
 * Created by iyeongjun on 2017. 10. 19..
 */

public class TermData {
    private String title;
    private String contents;
    private String imgURL;
    private String firstContents;
    private String secondContents;

    public TermData() {
        divideContents(getContents());
    }
    private void divideContents(String str){
        setFirstContents(str.substring(0,50));
        setSecondContents(str.substring(50));
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
