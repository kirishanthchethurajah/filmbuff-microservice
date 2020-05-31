package com.kira.filmfreak.filmkatalogservice.service;

import com.kira.filmfreak.filmkatalogservice.model.BenutzerBewertungen;
import com.kira.filmfreak.filmkatalogservice.model.Bewertung;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class BewertungInfoService {
    private RestTemplate restTemplate;

    public BewertungInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(fallbackMethod = "erhaltenFallBackBenutzerBewertung",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    },threadPoolKey = "BewertungInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "20"),
                    @HystrixProperty(name="maxQueueSize", value = "10")
            })
    public BenutzerBewertungen erhaltenBenutzerBewertung(String benutzerId) {
        return restTemplate.getForObject("http://bewertungen-daten-service/bewertungen/benutzers/"+benutzerId, BenutzerBewertungen.class);
    }

    public BenutzerBewertungen erhaltenFallBackBenutzerBewertung(String benutzerId) {
        BenutzerBewertungen benutzerBewertungen = new BenutzerBewertungen();
        benutzerBewertungen.setBenutzerId(benutzerId);
        benutzerBewertungen.setBewertungList(Arrays.asList(new Bewertung("0",0)));
        return benutzerBewertungen;
    }





}
