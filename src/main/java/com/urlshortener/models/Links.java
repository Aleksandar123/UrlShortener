package com.urlshortener.models;

/**
 * Created by aleksandar on 28.12.16.
 */
public class Links {

    private long id;
    private String longUrl;
    private String shortUrl;
    private boolean tempUrl;

    public Links() {
    }

    public Links(long id, String longUrl, String shortUrl, boolean tempUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.tempUrl = tempUrl;
    }

    public Links(String longUrl, String shortUrl, boolean tempUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.tempUrl = tempUrl;
    }

    @Override
    public String toString() {
        return "Links{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", tempUrl=" + tempUrl +
                '}';
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public boolean isTempUrl() {
        return tempUrl;
    }

    public void setTempUrl(boolean tempUrl) {
        this.tempUrl = tempUrl;
    }
}
