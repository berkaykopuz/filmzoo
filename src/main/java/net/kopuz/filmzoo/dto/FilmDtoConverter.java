package net.kopuz.filmzoo.dto;

import net.kopuz.filmzoo.model.Film;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmDtoConverter {
   private final ReviewDtoConverter converter;

    public FilmDtoConverter(@Lazy ReviewDtoConverter converter) {
        this.converter = converter;
    }


    public FilmDto convert(Film from){
        return new FilmDto(from.getId() ,from.getTitle(), from.getType(), from.getYear(), from.getPoster(), from.getRate(),
                converter.convert(new HashSet<>(from.getReviews())));
    }

    public Set<FilmDto> convert(Set<Film> filmList){
        return filmList.stream().map(this::convert).collect(Collectors.toSet());
    }
}
