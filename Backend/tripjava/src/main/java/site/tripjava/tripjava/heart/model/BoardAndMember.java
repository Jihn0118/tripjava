package site.tripjava.tripjava.heart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardAndMember {

    private String memberId;
    private int contentId;
}
