package site.tripjava.tripjava.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;
import site.tripjava.tripjava.attraction.model.service.AttractionService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<List<AttractionInfo>> searchAttraction(SearchCondition searchCondition) throws SQLException {
        System.out.println(searchCondition);
        return new ResponseEntity<List<AttractionInfo>>(attractionService.searchAttraction(searchCondition), HttpStatus.OK);
    }

    @GetMapping("/sidoName")
    public ResponseEntity<String[]> getSidoName() throws SQLException {
        System.out.println("1111");
        return new ResponseEntity<String[]>(attractionService.getSidoName(), HttpStatus.OK);
    }
}
