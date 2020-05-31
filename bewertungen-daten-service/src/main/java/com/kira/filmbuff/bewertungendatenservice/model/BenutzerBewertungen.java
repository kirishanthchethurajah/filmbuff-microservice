package com.kira.filmbuff.bewertungendatenservice.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
@Data
public class BenutzerBewertungen {
    private String benutzerId;
    private List<Bewertung> bewertungList;
    private void initData(String benutzerId){
        this.setBenutzerId(benutzerId);
        this.setBewertungList(Arrays.asList(
                new Bewertung("100",7),
                new Bewertung("550",8)
        ));
    }

}
