package com.example.apirest.service;

import com.example.apirest.entity.ExternalUser;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserService {

    public List<ExternalUser> getExternalUserInfo() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users";
        ExternalUser[] usersArray = restTemplate.getForObject(url, ExternalUser[].class);
        return Arrays.asList(usersArray);
    }
}