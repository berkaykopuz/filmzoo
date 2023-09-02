package net.kopuz.filmzoo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.kopuz.filmzoo.constant.Constant;
import net.kopuz.filmzoo.dto.FilmDto;
import net.kopuz.filmzoo.dto.FilmDtoConverter;
import net.kopuz.filmzoo.model.Film;
import net.kopuz.filmzoo.repository.FilmRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmDtoConverter filmDtoConverter;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FilmService(FilmRepository filmRepository, FilmDtoConverter filmDtoConverter, RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.filmRepository = filmRepository;
        this.filmDtoConverter = filmDtoConverter;
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public List<FilmDto> getFilmsByTitle(String title){
        List<Film> filmList = filmRepository.findAllByTitle(title);

        if(filmList.isEmpty()){
            return getFilmsFromIMDB(title);
        }
        else{
            return filmList.stream().map(film -> filmDtoConverter.convert(film)).collect(Collectors.toList());
        }



    }

    public Film getFilmById(String filmId){
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()){
            return film.get();
        }
        else{
            return null;
        }
    }

    private List<FilmDto> getFilmsFromIMDB(String title) {
        String url = Constant.API_URL + title;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        List<Film> filmList = new ArrayList<>();

        try {
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            if(jsonNode.has("result")){
                JsonNode resultNode = jsonNode.get("result");
                for(JsonNode filmNode : resultNode){
                    Film film = new Film(
                            filmNode.get("Title").asText(),
                            filmNode.get("Type").asText(),
                            filmNode.get("Year").asText(),
                            filmNode.get("Poster").asText()
                    );
                    filmList.add(film);
                }
            }

        filmRepository.saveAll(filmList); // save to db.

        List<FilmDto> convertList = new ArrayList<>();
        for(Film film : filmList){
            convertList.add(filmDtoConverter.convert(film));
        }
        return convertList;

        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
    public FilmDto filmToDto(Film film){
        return filmDtoConverter.convert(film);
    }


}
