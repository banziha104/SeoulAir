package com.veryworks.iyeongjun.seoulair.domain;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class Data {
    private String total;

    private Items[] items;

    private String start;

    private String lastBuildDate;

    private String display;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDisplay ()
    {
        return display;
    }

    public void setDisplay (String display)
    {
        this.display = display;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", items = "+items+", start = "+start+", lastBuildDate = "+lastBuildDate+", display = "+display+"]";
    }
}
