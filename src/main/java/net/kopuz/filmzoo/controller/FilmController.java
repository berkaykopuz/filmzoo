package net.kopuz.filmzoo.controller;

import net.kopuz.filmzoo.dto.FilmDto;
import net.kopuz.filmzoo.dto.ReviewDto;
import net.kopuz.filmzoo.dto.ReviewDtoConverter;
import net.kopuz.filmzoo.model.Film;
import net.kopuz.filmzoo.model.Review;
import net.kopuz.filmzoo.service.FilmService;
import net.kopuz.filmzoo.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/films")
public class FilmController {
    private final FilmService filmService;
    private final ReviewService reviewService;
    private final ReviewDtoConverter reviewDtoConverter;

    public FilmController(FilmService filmService, ReviewService reviewService, ReviewDtoConverter reviewDtoConverter) {
        this.filmService = filmService;
        this.reviewService = reviewService;
        this.reviewDtoConverter = reviewDtoConverter;
    }

    @GetMapping("/{title}")
    public String filmList(Model model, @PathVariable("title") String title){
        List<FilmDto> filmDtos= filmService.getFilmsByTitle(title);
        model.addAttribute("films", filmDtos);
        return "film-list";
    }

    @GetMapping("/{id}/comments")
    public String filmReview(Model model, @PathVariable("id") String id){
        Film film = filmService.getFilmById(id);
        Set<ReviewDto> reviewDtos = reviewDtoConverter.convert(film.getReviews());

        if(reviewDtos.isEmpty()){
            model.addAttribute("reviews", new ReviewDto("","", "", filmService.filmToDto(film)));
            model.addAttribute("films", film);

        }else{
            model.addAttribute("reviews", reviewDtos);
            model.addAttribute("films", filmService.filmToDto(film));
        }

        return "film-review";
    }

    @PostMapping("/rate")
    public String rateSubmit(@ModelAttribute("reviews") Review review, @ModelAttribute("films") Film film){
        String id = film.getId();
        review.setFilm(film);
        reviewService.saveReview(review);
        return "redirect:/" + id + "/comments";
    }

}
