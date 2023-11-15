package site.tripjava.tripjava.tripPlan.model.service;

import site.tripjava.tripjava.tripPlan.model.TravelPlan;

import java.sql.SQLException;

public interface TripPlanService {

    void registerTripPlan(TravelPlan travelPlan) throws SQLException;

}
