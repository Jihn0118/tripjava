package site.tripjava.tripjava.tripPlan.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.tripjava.tripjava.tripPlan.model.Day;
import site.tripjava.tripjava.tripPlan.model.TravelPlan;
import site.tripjava.tripjava.tripPlan.model.TravelPlanDetail;
import site.tripjava.tripjava.tripPlan.model.mapper.TripPlanMapper;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

    private final TripPlanMapper tripPlanMapper;

    @Override
    @Transactional
    public void registerTripPlan(TravelPlan travelPlan) throws SQLException {
        // 로그인 구현시 제거, db user_id not null 조건
        travelPlan.setUserId(1L);
        tripPlanMapper.registerTripPlan(travelPlan);
        List<Day> days = travelPlan.getDays();
        if (days != null && !days.isEmpty()) {
            for (Day day : days) {
                day.setTravelId(travelPlan.getTravelId());
                tripPlanMapper.registerDayPlan(day);
                List<TravelPlanDetail> travelPlanDetailList = day.getDetails();
                if (travelPlanDetailList != null && !travelPlanDetailList.isEmpty()) {
                    tripPlanMapper.registerTripDayPlanDetail(day);
                }
            }
        }
    }

    @Override
    public List<TravelPlan> getTravelPlanList(Long id) throws SQLException {
        return tripPlanMapper.getTravelPlanList(id);
    }

    @Override
    public List<Day> getTravelDetailPlan(Long travelId) throws SQLException {
        return tripPlanMapper.getTravelDetailPlan(travelId);
    }

    @Override
    public void deleteTripPlan(Long travelId) throws SQLException {
        tripPlanMapper.deleteTripPlan(travelId);
    }

    @Override
    @Transactional
    public void modifyTripPlan(TravelPlan travelPlan) throws SQLException {
        tripPlanMapper.deleteTripPlan(travelPlan.getTravelId());
        tripPlanMapper.registerTripPlan(travelPlan);
        List<Day> days = travelPlan.getDays();
        if (days != null && !days.isEmpty()) {
            for (Day day : days) {
                day.setTravelId(travelPlan.getTravelId());
                tripPlanMapper.registerDayPlan(day);
                List<TravelPlanDetail> travelPlanDetailList = day.getDetails();
                if (travelPlanDetailList != null && !travelPlanDetailList.isEmpty()) {
                    tripPlanMapper.registerTripDayPlanDetail(day);
                }
            }
        }
    }
}
