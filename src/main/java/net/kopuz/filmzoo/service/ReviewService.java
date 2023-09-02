package net.kopuz.filmzoo.service;

import net.kopuz.filmzoo.dto.ReviewDto;
import net.kopuz.filmzoo.dto.ReviewDtoConverter;
import net.kopuz.filmzoo.model.Film;
import net.kopuz.filmzoo.model.Review;
import net.kopuz.filmzoo.repository.FilmRepository;
import net.kopuz.filmzoo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;
    private final ReviewDtoConverter reviewDtoConverter;

    public ReviewService(ReviewRepository reviewRepository, FilmRepository filmRepository, ReviewDtoConverter reviewDtoConverter) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
        this.reviewDtoConverter = reviewDtoConverter;
    }

    public Set<ReviewDto> getAllReviewsByFilmId(String filmId){
        Optional<Film> film = filmRepository.findById(filmId);
        Set<Review> reviews = new HashSet<>();

        if(film.isPresent()){
            reviews = film.get().getReviews();
        }
        else{
            throw new RuntimeException();
        }

        return reviews.stream().map(review -> reviewDtoConverter.convert(review)
            ).collect(Collectors.toSet());


    }

    public void saveReview(Review review){
        /*Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            double rate = action=="plus" ? film.get().getRate() + 1 : film.get().getRate() - 1;
            if(rate < 0) rate = 0 ;

            film.get().setRate(rate);

            filmRepository.save(film.get());
        }*/
        reviewRepository.save(review);

    }
}
