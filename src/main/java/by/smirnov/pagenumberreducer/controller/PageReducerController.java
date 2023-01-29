package by.smirnov.pagenumberreducer.controller;

import by.smirnov.pagenumberreducer.response.ReducerResponse;
import by.smirnov.pagenumberreducer.service.PageReducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reducedPageNumbers")
public class PageReducerController {

    private final PageReducerService service;

    @GetMapping
    public ResponseEntity<ReducerResponse> show(String rawPageNumbers) {
        ReducerResponse response = service.reduce(rawPageNumbers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
