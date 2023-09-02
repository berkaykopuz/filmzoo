package net.kopuz.filmzoo.dto;

import net.kopuz.filmzoo.model.Review;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReviewDtoConverter {
    private final FilmDtoConverter converter;

    public ReviewDtoConverter(FilmDtoConverter converter) {
        this.converter = converter;
    }

    public ReviewDto convert(Review from) {
        return new ReviewDto(from.getId() ,from.getUsername(), from.getComment(), converter.convert(from.getFilm()));
    }

    public Set<ReviewDto> convert(Set<Review> reviewList){
        return reviewList.stream().map(this::convert).collect(Collectors.toSet());
    }
}
