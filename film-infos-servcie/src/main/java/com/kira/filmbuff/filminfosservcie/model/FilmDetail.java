package com.kira.filmbuff.filminfosservcie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FilmDetail {


    private String filmId;
    private String titel;
    private String zusammenFassung;
}
