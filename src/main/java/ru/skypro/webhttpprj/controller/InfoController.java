package ru.skypro.webhttpprj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.webhttpprj.service.StudentService;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequestMapping
@RestController
public class InfoController {
    @Value("${server.port:8080}")
    private Integer port;

    @GetMapping("getPort")
    public Integer getPort(){
        return port;
    }

    Logger logger = LoggerFactory.getLogger(InfoController.class);

    @GetMapping("sumMillion")
    public Integer sumMillion(){
        int sum;
        long t;
        t = System.currentTimeMillis();
        sum = Stream.iterate(1, a -> a +1) .limit(1_000_000) .reduce(0, (a, b) -> a + b );
        logger.info("exec in "+ (System.currentTimeMillis()-t) + " milliseconds");//43
        t = System.currentTimeMillis();
        sum = Stream.iterate(1, a -> a +1) .limit(1_000_000).mapToInt(value -> value).sum();
        logger.info("exec in "+ (System.currentTimeMillis()-t) + " milliseconds");//31
        t = System.currentTimeMillis();
        sum = IntStream.rangeClosed(1,1_000_000)  .sum();
        logger.info("exec in "+ (System.currentTimeMillis()-t) + " milliseconds");//7
        return sum;
    }
}
