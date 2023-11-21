package site.tripjava.tripjava.comment.model.service;

import site.tripjava.tripjava.comment.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {


    List<Comment> getCommentList(int contentId) throws SQLException;

    void enrollComment(Comment comment) throws SQLException;

    void deleteComment(int commentId) throws SQLException;


}
