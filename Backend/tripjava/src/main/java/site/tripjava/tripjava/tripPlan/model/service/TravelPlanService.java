package site.tripjava.tripjava.tripPlan.model.service;

import site.tripjava.tripjava.tripPlan.model.Day;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;

import java.sql.SQLException;
import java.util.List;

public interface TravelPlanService {

    void registerTripPlan(TravelPlan travelPlan) throws SQLException;

    List<TravelPlan> getTravelPlanList(Long id) throws SQLException;

    List<Day> getTravelDetailPlan(Long travelId) throws SQLException;

    void deleteTripPlan(Long travelId) throws SQLException;

    void modifyTripPlan(TravelPlan travelPlan) throws  SQLException;
}
