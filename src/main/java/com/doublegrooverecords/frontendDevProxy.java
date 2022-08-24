package com.doublegrooverecords;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class frontendDevProxy {
    @GetMapping("/dist/**")
    public ResponseEntity<?> frontendResource(HttpServletRequest request) {
        System.out.println(String.format("Proxying: %s", request.getRequestURL()));
        String resourcePath = request.getRequestURL().toString().split("/dist/")[1];
        System.out.println(String.format("Fetching Resource: %s", resourcePath));
        RestTemplate restTemplate = new RestTemplate();
        String devResourcePath = String.format("http://127.0.0.1:3000/%s", resourcePath);
        return restTemplate.exchange(devResourcePath, HttpMethod.GET, null, String.class);
    }
}
