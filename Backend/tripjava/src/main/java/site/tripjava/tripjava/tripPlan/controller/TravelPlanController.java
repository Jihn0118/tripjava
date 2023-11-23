package site.tripjava.tripjava.tripPlan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.tripPlan.model.Day;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;
import site.tripjava.tripjava.tripPlan.model.service.TravelPlanService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class TravelPlanController {

    private final TravelPlanService tripPlanService;

    @PostMapping
    public ResponseEntity<Void> registerTravelPlans(@RequestBody TravelPlan travelPlan) throws SQLException {
        System.out.println(travelPlan);
        tripPlanService.registerTripPlan(travelPlan);
        return ResponseEntity.ok().build();
    }

    // 유저아이디 받아서 모든 여행계획 데이터 가져오기
    // security 적용하면 api 변경  => tripjava/plan (get)
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<TravelPlan>> getTripPlanList(@PathVariable String userId) throws SQLException {
        System.out.println(userId);
//        return tripPlanService.getTravelPlanList(id);
        return new ResponseEntity<List<TravelPlan>>(tripPlanService.getTravelPlanList(userId), HttpStatus.OK);
    }

    // 계획 상세
    @GetMapping("/{travelId}")
    public ResponseEntity<List<Day>> getTripPlan(@PathVariable Long travelId) throws SQLException {
        System.out.println(travelId);
        return new ResponseEntity<List<Day>>(tripPlanService.getTravelDetailPlan(travelId), HttpStatus.OK);
    }

    // 계획 지우기
    @DeleteMapping("/{travelId}")
    public ResponseEntity<Void> deleteTripPlan(@PathVariable Long travelId) throws SQLException{
        System.out.println(travelId);
        tripPlanService.deleteTripPlan(travelId);
        return ResponseEntity.ok().build();
    }

    // 계획 수정
    @PutMapping
    public ResponseEntity<Void> modifyTripPlan(@RequestBody TravelPlan travelPlan) throws SQLException{
        System.out.println(travelPlan);
        tripPlanService.modifyTripPlan(travelPlan);
        return ResponseEntity.ok().build();
    }
}
