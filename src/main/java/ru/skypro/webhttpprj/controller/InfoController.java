package ru.skypro.webhttpprj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class InfoController {
    @Value("${server.port:8080}")
    private Integer port;

    @GetMapping("getPort")
    public Integer getPort(){
        return port;
    }
}
