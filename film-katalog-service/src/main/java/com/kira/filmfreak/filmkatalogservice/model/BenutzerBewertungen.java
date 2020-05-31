package com.kira.filmfreak.filmkatalogservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BenutzerBewertungen {
    private String benutzerId;
    private List<Bewertung> bewertungList;
}
