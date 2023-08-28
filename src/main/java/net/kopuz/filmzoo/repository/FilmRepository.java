package net.kopuz.filmzoo.repository;

import net.kopuz.filmzoo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByTitle(String title);
    List<Film> findAllByTitle(String title);
    Optional<Film> findById(String id);
}
