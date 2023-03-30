package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.example.entity.WeatherResponse;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    //constant keys for access to api
    private static final String API_KEY = "c55bd268c7msh7951b95eed978efp174697jsna7895dbba8f5";
    private static final String API_HOST = "weatherbit-v1-mashape.p.rapidapi.com5";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================Weather Api=================");

        System.out.print("Enter the latitude: ");
        String latitude = scanner.next();

        System.out.print("Enter the longitude: ");
        String longitude = scanner.next();

        System.out.println(">>>>>>>>>>Processing<<<<<<<<<<");

        //call weather Api
        WeatherResponse weatherResponse = callWeatherApi(latitude, longitude);

        System.out.println("---Api response---");
        System.out.println("City name: " + weatherResponse.getCityName());
        System.out.println("Country code: " + weatherResponse.getCountryCode());
        System.out.println("Temperature: " + weatherResponse.getData().get(0).getTemp());

    }

    private static WeatherResponse callWeatherApi(String latitude, String longitude) {
        OkHttpClient client = new OkHttpClient();

        //create request object
        Request request = new Request.Builder()
                .url("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/minutely?lat=" + latitude + "&lon=" + longitude)
                .get()
                .addHeader("X-RapidAPI-Key", API_KEY)
                .addHeader("X-RapidAPI-Host", API_HOST)
                .build();

        Response response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //do call for api
            response = client.newCall(request).execute();

            //get response from api
            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                //map response to local model class
                return objectMapper.readValue(responseBody.string(), WeatherResponse.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}