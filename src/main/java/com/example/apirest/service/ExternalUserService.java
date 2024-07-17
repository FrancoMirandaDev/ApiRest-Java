package com.example.apirest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserService {

    public String getExternalUserInfo() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users";
        return restTemplate.getForObject(url, String.class);
    }
}
