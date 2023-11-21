package site.tripjava.tripjava.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.comment.model.Comment;
import site.tripjava.tripjava.comment.model.service.CommentService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/{contentId}")
    public ResponseEntity<List<Comment>> getCommentList(@PathVariable int contentId) throws SQLException {
        System.out.println("리스트 가지러 왔습니다");
        return ResponseEntity.ok(commentService.getCommentList(contentId));
    }

    @PostMapping
    public ResponseEntity<Void> enrollComment(@RequestBody Comment comment) throws SQLException {
        System.out.println("등록하러 왔습니다");
        commentService.enrollComment(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) throws SQLException {
        System.out.println("지우러 왔습니다");
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
