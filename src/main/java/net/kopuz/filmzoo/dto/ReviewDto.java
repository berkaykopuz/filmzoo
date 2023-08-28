package net.kopuz.filmzoo.dto;

import net.kopuz.filmzoo.model.Review;

public record ReviewDto(String username,
                        String comment

) {
    public static ReviewDto convert(Review from){
        return new ReviewDto(from.getUsername(), from.getComment());
    }
}
