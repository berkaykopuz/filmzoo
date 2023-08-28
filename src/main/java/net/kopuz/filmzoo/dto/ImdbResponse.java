package net.kopuz.filmzoo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public record ImdbResponse ( String Title,
         String Year,
         String imdbID,
         String Type,
         String Poster){


}
