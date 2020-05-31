package com.kira.filmbuff.filminfosservcie.kontroller;

import com.kira.filmbuff.filminfosservcie.model.Film;
import com.kira.filmbuff.filminfosservcie.model.FilmDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/film")
@RestController
public class FilmInfosKontroller {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    public FilmInfosKontroller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{kinoId}")
    public Film erhaltenMovie(@PathVariable String kinoId){
        FilmDetail detail = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" +kinoId + "?api_key=" + apiKey, FilmDetail.class);
        return new Film(kinoId,detail.getTitel(),detail.getZusammenFassung());
    }
}
