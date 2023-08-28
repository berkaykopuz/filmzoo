package net.kopuz.filmzoo.controller;

import net.kopuz.filmzoo.dto.FilmDto;
import net.kopuz.filmzoo.dto.ReviewDto;
import net.kopuz.filmzoo.service.FilmService;
import net.kopuz.filmzoo.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/films")
public class FilmController {
    private final FilmService filmService;
    private final ReviewService reviewService;

    public FilmController(FilmService filmService, ReviewService reviewService) {
        this.filmService = filmService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{title}")
    public String filmList(Model model, @PathVariable("title") String title){
        List<FilmDto> filmDtos= filmService.getFilmsByTitle(title);
        model.addAttribute("films", filmDtos);
        return "one-film";
    }

    @GetMapping("/{id}/comments")
    public String filmReview(Model model, @PathVariable("id") String id){
        List<ReviewDto> reviewDtos = reviewService.getAllReviewsByFilmId(id);
        model.addAttribute("reviews", reviewDtos);
        return "film-review";
    }

    @PostMapping("/rate")
    public String rateSubmit(@RequestParam String filmId,
                             @RequestParam String userName,
                             @RequestParam String review,
                             @RequestParam String action){
        reviewService.saveReview(filmId, userName, review, action);
        return "redirect:/films/filmId/comments";
    }

}
