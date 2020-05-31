package com.kira.filmbuff.bewertungendatenservice.kontroller;

import com.kira.filmbuff.bewertungendatenservice.model.BenutzerBewertungen;
import com.kira.filmbuff.bewertungendatenservice.model.Bewertung;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bewertungen")
public class BewertungenDatenService {

    @GetMapping("/{filmId}")
    public Bewertung erhaltenBewertung(@PathVariable String filmId){
        return new Bewertung("4234",4);
    }

    @GetMapping("/benutzers/{benutzerId}")
    public BenutzerBewertungen erhaltenAlleBewertungen(@PathVariable String benutzerId){

        BenutzerBewertungen benutzerBewertungen = new BenutzerBewertungen();
        benutzerBewertungen.setBewertungList(Arrays.asList(
                new Bewertung("100",7),
                new Bewertung("550",8)));
        benutzerBewertungen.setBenutzerId(benutzerId);



        return benutzerBewertungen;
    }


}
