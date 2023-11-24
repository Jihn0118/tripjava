package site.tripjava.tripjava.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;
import site.tripjava.tripjava.attraction.model.Sido;
import site.tripjava.tripjava.attraction.model.service.AttractionService;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @GetMapping("/sido")
    public ResponseEntity<Sido[]> getSidoInfo() throws SQLException {
        return new ResponseEntity<Sido[]>(attractionService.getSidoInfo(), HttpStatus.OK);
    }

    @GetMapping("/detail/{contendId}")
    public ResponseEntity<AttractionInfo> getDetailInfo(@PathVariable int contendId) throws SQLException{
        System.out.println("!@3412312341231231251235");
        return new ResponseEntity<AttractionInfo>(attractionService.getDetailInfo(contendId), HttpStatus.OK);
    }

    @GetMapping("/attractions")
    public ResponseEntity<List<AttractionInfo>> getAllInfosFindById(@RequestParam(name="contentIdList", defaultValue = "") ArrayList<Long> contentIdList) throws Exception {
        System.out.println("모든 데이터 긁어오기");

        System.out.println(contentIdList.toString());
        return new ResponseEntity<List<AttractionInfo>>(attractionService.getAllInfosFindById(contentIdList), HttpStatus.OK);
    }



}
