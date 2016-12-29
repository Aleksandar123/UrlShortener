package com.urlshortener.services;

import com.urlshortener.models.Links;

/**
 * Created by aleksandar on 28.12.16.
 */
public interface LinksService {

    Links create(Links link);
    Links getByShortUrl(String shortUrl);
    Links getByLongUrl(String longUrl);
    void deleteById(Long id);


}
