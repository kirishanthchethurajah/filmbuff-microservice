package com.kira.filmfreak.filmkatalogservice.kontroller;

import com.kira.filmfreak.filmkatalogservice.model.BenutzerBewertungen;
import com.kira.filmfreak.filmkatalogservice.model.FilmkatalogEintrag;
import com.kira.filmfreak.filmkatalogservice.service.BewertungInfoService;
import com.kira.filmfreak.filmkatalogservice.service.FilmInfoService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("katalog")
public class FilmKatalogKontroller {


    private WebClient.Builder webClientBuilder;

    private DiscoveryClient discoveryClient;

    private BewertungInfoService bewertungInfoService;
    private FilmInfoService filmInfoService;

    public FilmKatalogKontroller(WebClient.Builder webClientBuilder, DiscoveryClient discoveryClient, BewertungInfoService bewertungInfoService, FilmInfoService filmInfoService) {
        this.webClientBuilder = webClientBuilder;
        this.discoveryClient = discoveryClient;
        this.bewertungInfoService = bewertungInfoService;
        this.filmInfoService = filmInfoService;
    }


    @GetMapping("/{benutzerId}")
//    @HystrixCommand(fallbackMethod = "erhaltenFallbackKatalog")
    public List<FilmkatalogEintrag> erhaltenKatalog(@PathVariable  String benutzerId){

        BenutzerBewertungen benutzerBewertungen = bewertungInfoService.erhaltenBenutzerBewertung(benutzerId);
        System.out.println(benutzerBewertungen.getBewertungList().get(0).getFilmId());
        //sync way
        return benutzerBewertungen.getBewertungList().stream().peek(bewertung -> System.out.println(bewertung.getFilmId()))
                .map(bewertung -> filmInfoService.erhaltenFilmInfo(bewertung))
                .collect(Collectors.toList());


        // async way - block make its sync
        /*Film film = webClientBuilder.build()
                .get()
                .uri("http://localhost:8086/film/"+ bewertung.getFilmId())
                .retrieve()
                .bodyToMono(Film.class)
                .block();*/

    }







    public List<FilmkatalogEintrag> erhaltenFallbackKatalog(@PathVariable  String benutzerId){
        return Arrays.asList(new FilmkatalogEintrag("Kein Film","",0));
    }

    }
