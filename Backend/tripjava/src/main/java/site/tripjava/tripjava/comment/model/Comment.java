package site.tripjava.tripjava.comment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Comment {
    private int commentId;
    private int contentId;
    private String commentContent;
    private String memberId;
    private Date createDate;

}
