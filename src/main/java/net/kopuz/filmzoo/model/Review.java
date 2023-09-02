package net.kopuz.filmzoo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Types;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String username;

    @Lob
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="film_id", nullable = false)
    private Film film;

    public Review() {
    }

    public Review(String username, String comment, Film film) {
        this.username = username;
        this.comment = comment;
        this.film = film;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
