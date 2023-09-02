package net.kopuz.filmzoo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String title;
    private String type;
    private String year;
    private String poster;
    private Double rate;

    @OneToMany(mappedBy = "film",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    public Film() {
    }

    public Film(String title, String type, String year, String poster, Double rate, Set<Review> reviews) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.poster = poster;
        this.rate = rate;
        this.reviews = reviews;
    }

    public Film(String title, String type, String year, String poster) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }

    public String getPoster() {
        return poster;
    }

    public Double getRate() {
        return rate;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
