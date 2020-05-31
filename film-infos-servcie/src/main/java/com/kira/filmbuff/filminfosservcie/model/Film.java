package com.kira.filmbuff.filminfosservcie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Film {
    private String filmId;
    private String name;
    private String filmBeschreibung;
}
