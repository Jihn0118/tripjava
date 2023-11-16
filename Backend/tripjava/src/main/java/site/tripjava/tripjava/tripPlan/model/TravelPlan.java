package site.tripjava.tripjava.tripPlan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelPlan {

    // 여행 번호
    private Long travelId;
    // 작성자 아이디
    private Long userId;
    // 여행 시작 날짜
    private Date startDate;
    // 여행 마지막 날짜
    private Date endDate;
    // 여행 이름
    private String travelName;
    // 여행 날짜
    private List<Day> days;

}
