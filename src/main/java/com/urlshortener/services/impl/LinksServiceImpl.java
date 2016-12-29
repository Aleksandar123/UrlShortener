package com.urlshortener.services.impl;

import com.urlshortener.models.Links;
import com.urlshortener.services.LinksService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by aleksandar on 28.12.16.
 */
@Service
public class LinksServiceImpl implements LinksService{

    private List<Links> links = new ArrayList<Links>(){{
        add(new Links(1L, "https://google.com","google",false));
        add(new Links(2L, "https://facebook.com","fcbcom",false));
        add(new Links(3L, "https://reddit.com","reddit",false));
        add(new Links(4L, "https://youtube.com","youtube",false));
        add(new Links(5L, "https://time.com","timemk",false));
    }};

    @Override
    public Links create(Links link) {
        link.setId(this.links.stream().mapToLong(
                l -> l.getId()).max().getAsLong() + 1);
        this.links.add(link);
        return link;
    }

    @Override
    public Links getByShortUrl(String shortUrl) {
         return this.links.stream()
                .filter(l -> Objects.equals(l.getShortUrl(), shortUrl))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Links getByLongUrl(String longUrl) {
        return this.links.stream()
                .filter(l -> Objects.equals(l.getLongUrl(), longUrl))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        for (int i = 0; i < this.links.size(); i++) {
            if (Objects.equals(this.links.get(i).getId(), id)) {
                this.links.remove(i);
                return;
            }
        }
        throw new RuntimeException("Link not found: " + id);
    }

}
