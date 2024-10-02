package dev.kamal.product_service.authCommons;

import dev.kamal.product_service.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
        UserDto userDto = restTemplate.getForObject(
                "http://localhost:4141/users/validate/" + token,
                UserDto.class
        );

        return userDto;
    }
}
