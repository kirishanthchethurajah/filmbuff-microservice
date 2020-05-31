package com.kira.filmfreak.filmkatalogservice.service;

import com.kira.filmfreak.filmkatalogservice.model.Bewertung;
import com.kira.filmfreak.filmkatalogservice.model.Film;
import com.kira.filmfreak.filmkatalogservice.model.FilmkatalogEintrag;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmInfoService {

    private RestTemplate restTemplate;

    public FilmInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    //Bulk head - separate thread pool bucket
    @HystrixCommand(fallbackMethod = "erhaltenFallBackFilmInfo",
    threadPoolKey = "filmInfoPool",
    threadPoolProperties = {
            @HystrixProperty(name="coreSize",value = "20"),
            @HystrixProperty(name="maxQueueSize", value = "10")
    })

    public FilmkatalogEintrag erhaltenFilmInfo(Bewertung bewertung) {
        Film film = restTemplate.getForObject("http://film-infos-service/film/"+ bewertung.getFilmId(), Film.class);
        return new FilmkatalogEintrag(bewertung.getFilmId(),film.getFilmBeschreibung(),bewertung.getFilmBewertung());
    }

    public FilmkatalogEintrag erhaltenFallBackFilmInfo(Bewertung bewertung) {
         return new FilmkatalogEintrag(bewertung.getFilmId(),"Kein Einzelheiten ist verfügbar",bewertung.getFilmBewertung());
    }
}
