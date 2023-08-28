package net.kopuz.filmzoo.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static String API_URL = "https://api.collectapi.com/imdb/imdbSearchByName?query=";
    public static String API_KEY;

    @Value("${film.api-key}")
    public void setApiKey(String apiKey){
        Constant.API_KEY = apiKey;
    }
}
