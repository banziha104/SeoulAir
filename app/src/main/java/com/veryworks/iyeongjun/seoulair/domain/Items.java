package com.veryworks.iyeongjun.seoulair.domain;

/**
 * Created by iyeongjun on 2017. 10. 11..
 */

public class Items {
    private String pubDate;

    private String originallink;

    private String title;

    private String description;

    private String link;

    public String getPubDate ()
    {
        return pubDate;
    }

    public void setPubDate (String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getOriginallink ()
    {
        return originallink;
    }

    public void setOriginallink (String originallink)
    {
        this.originallink = originallink;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pubDate = "+pubDate+", originallink = "+originallink+", title = "+title+", description = "+description+", link = "+link+"]";
    }
}
