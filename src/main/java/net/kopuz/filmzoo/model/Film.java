package net.kopuz.filmzoo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
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

    public Film() {
    }

    public Film(String id, String title, String type, String year, String poster, Double rate) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.year = year;
        this.poster = poster;
        this.rate = 0.0;
    }

    public Film(String title, String type, String year, String poster) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.poster = poster;
        this.rate = 0.0;
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

    public void setRate(Double rate) {
        this.rate = rate;
    }


}
