package site.tripjava.tripjava.tripPlan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.tripPlan.model.Day;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;
import site.tripjava.tripjava.tripPlan.model.service.TripPlanService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class TripPlanController {

    private final TripPlanService tripPlanService;

    @PostMapping
    public ResponseEntity<Void> registerTravelPlans(@RequestBody TravelPlan travelPlan) throws SQLException {
        System.out.println(travelPlan);
        tripPlanService.registerTripPlan(travelPlan);
        return ResponseEntity.ok().build();
    }

    // security 적용하면 api 변경  => tripjava/plan (get)
//    @GetMapping("/{id}")
    public ResponseEntity<List<TravelPlan>> getTripPlanList(@PathVariable Long id) throws SQLException {
        System.out.println(id);
//        return tripPlanService.getTravelPlanList(id);
        return new ResponseEntity<List<TravelPlan>>(tripPlanService.getTravelPlanList(id), HttpStatus.OK);
    }

    @GetMapping("/{travelId}")
    public ResponseEntity<List<Day>> getTripPlan(@PathVariable Long travelId) throws SQLException {
        System.out.println(travelId);
        return new ResponseEntity<List<Day>>(tripPlanService.getTravelDetailPlan(travelId), HttpStatus.OK);
    }
}
