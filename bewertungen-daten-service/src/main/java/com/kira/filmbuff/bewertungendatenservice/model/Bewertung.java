package com.kira.filmbuff.bewertungendatenservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Bewertung {
    private String filmId;
    private double filmBewertung;
}
