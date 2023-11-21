package site.tripjava.tripjava.comment.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.comment.model.Comment;
import site.tripjava.tripjava.comment.model.mapper.CommentMapper;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentList(int contentId) throws SQLException {
        return commentMapper.getCommentList(contentId);
    }

    @Override
    public void enrollComment(Comment comment) throws SQLException {
        commentMapper.enrollComment(comment);
    }

    @Override
    public void deleteComment(int commentId) throws SQLException {
        commentMapper.deleteComment(commentId);
    }
}
