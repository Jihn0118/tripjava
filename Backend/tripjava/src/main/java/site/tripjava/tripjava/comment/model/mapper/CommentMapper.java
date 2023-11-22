package site.tripjava.tripjava.comment.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.tripjava.tripjava.comment.model.Comment;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentList(int contentId) throws SQLException;

    void enrollComment(Comment comment) throws SQLException;

    void deleteComment(int commentId) throws SQLException;

}
