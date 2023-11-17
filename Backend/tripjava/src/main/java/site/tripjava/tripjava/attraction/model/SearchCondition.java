package site.tripjava.tripjava.attraction.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchCondition {

    // 관광지 타입(음식점, 명소, 숙소)
    private String contentType;
    // 키워드 검색
    private String keyword;
    // 시도 코드
    private String sidoCode;

}
