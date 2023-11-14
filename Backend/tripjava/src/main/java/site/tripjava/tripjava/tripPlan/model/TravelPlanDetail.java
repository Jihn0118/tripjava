package site.tripjava.tripjava.tripPlan.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelPlanDetail {

    // 방문하는 순서를 저장하는 sequence
    private int sequence;
    // 여행지 정보 테이블 외래키
    private Long contentId;

}
