package com.kira.filmfreak.filmkatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmkatalogEintrag {
    private String name;
    private String rezension;
    private double bewerbung;
}
