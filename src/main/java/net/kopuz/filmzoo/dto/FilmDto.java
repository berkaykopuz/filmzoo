package net.kopuz.filmzoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.kopuz.filmzoo.model.Film;

import java.time.LocalDateTime;

public record FilmDto(String id,
                      String title,
                      String type,
                      String year,
                      String poster,
                      Double rate) {
    public static FilmDto convert(Film from){
        return new FilmDto(from.getId() ,from.getTitle(), from.getType(), from.getYear(), from.getPoster(), from.getRate());
    }
}
