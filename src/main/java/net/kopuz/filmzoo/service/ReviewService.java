package net.kopuz.filmzoo.service;

import net.kopuz.filmzoo.dto.ReviewDto;
import net.kopuz.filmzoo.model.Film;
import net.kopuz.filmzoo.model.Review;
import net.kopuz.filmzoo.repository.FilmRepository;
import net.kopuz.filmzoo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;

    public ReviewService(ReviewRepository reviewRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    public List<ReviewDto> getAllReviewsByFilmId(String filmId){
        List<Review> reviewList = reviewRepository.findAllByFilmId(filmId);

        return reviewList.stream().map(review -> {
            return ReviewDto.convert(review);
        }
        ).collect(Collectors.toList());
    }

    public void saveReview(String filmId,
                           String userName,
                           String comment,
                           String action){
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            double rate = action=="plus" ? film.get().getRate() + 1 : film.get().getRate() - 1;
            if(rate < 0) rate = 0 ;

            film.get().setRate(rate);

            filmRepository.save(film.get());
        }

        Review review = new Review(filmId, userName, comment);
        reviewRepository.save(review);

    }
}
