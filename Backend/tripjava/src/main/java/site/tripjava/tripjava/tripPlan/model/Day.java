package site.tripjava.tripjava.tripPlan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Day {
    // 여행 날짜
    private int day;
    // 여행 날짜별 상세 정보를 담는 list
    private List<TravelPlanDetail> details;

}
