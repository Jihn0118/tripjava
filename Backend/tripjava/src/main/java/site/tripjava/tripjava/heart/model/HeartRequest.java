package site.tripjava.tripjava.heart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HeartRequest {
    private String memberId;
    private Long contentId;
}
