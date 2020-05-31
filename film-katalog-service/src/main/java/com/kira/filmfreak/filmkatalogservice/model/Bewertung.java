package com.kira.filmfreak.filmkatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bewertung {
    private String filmId;
    private double filmBewertung;
}
