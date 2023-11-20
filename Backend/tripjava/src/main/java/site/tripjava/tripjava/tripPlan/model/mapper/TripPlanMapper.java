package site.tripjava.tripjava.tripPlan.model.mapper;

import site.tripjava.tripjava.tripPlan.model.Day;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;

import java.sql.SQLException;
import java.util.List;

public interface TripPlanMapper {

    void registerTripPlan(TravelPlan travelPlan) throws SQLException;

    void registerDayPlan(Day day) throws SQLException;

    void registerTripDayPlanDetail(Day day) throws SQLException;

    List<TravelPlan> getTravelPlanList(Long id) throws SQLException;

    List<Day> getTravelDetailPlan(Long travelId) throws SQLException;

    void deleteTripPlan(Long travelId) throws SQLException;
}
