package site.tripjava.tripjava.tripPlan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;
import site.tripjava.tripjava.tripPlan.model.service.TripPlanService;

import java.sql.SQLException;

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
}
