package site.tripjava.tripjava.tripPlan.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.tripPlan.model.mapper.TripPlanMapper;

@Service
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

    private final TripPlanMapper tripPlanMapper;
}
