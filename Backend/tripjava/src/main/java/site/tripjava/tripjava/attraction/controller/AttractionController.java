package site.tripjava.tripjava.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import site.tripjava.tripjava.attraction.model.service.AttractionService;

@RestController
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

}
