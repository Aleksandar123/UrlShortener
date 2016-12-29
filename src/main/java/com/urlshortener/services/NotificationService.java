package com.urlshortener.services;

/**
 * Created by aleksandar on 29.12.16.
 */
public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
}
