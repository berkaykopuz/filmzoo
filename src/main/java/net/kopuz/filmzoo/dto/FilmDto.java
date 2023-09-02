package net.kopuz.filmzoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.kopuz.filmzoo.model.Film;
import net.kopuz.filmzoo.model.Review;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record FilmDto(String id,
                      String title,
                      String type,
                      String year,
                      String poster,
                      Double rate,
                      Set<ReviewDto> reviewDtos) {

}
