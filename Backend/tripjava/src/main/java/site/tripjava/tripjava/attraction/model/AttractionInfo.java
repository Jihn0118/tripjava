package site.tripjava.tripjava.attraction.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionInfo {
    //관광지 ID
    private long contendId;
    // 이름
    private String title;
    // 주소
    private String addr;
    // 이미지
    private String image;
    // 위도
    private BigDecimal latitude;
    // 경도
    private BigDecimal longitude;
    // 좋아요
    private int heart;

}
