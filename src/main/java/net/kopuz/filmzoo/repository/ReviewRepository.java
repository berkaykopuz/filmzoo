package net.kopuz.filmzoo.repository;

import net.kopuz.filmzoo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

}
