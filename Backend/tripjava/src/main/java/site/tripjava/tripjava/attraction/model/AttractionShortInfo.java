package site.tripjava.tripjava.attraction.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AttractionShortInfo {
    public class AttractionInfo {
        private Long contentId;
        private String title;
        private String addr1;
        private String firstImage;
    }

}
