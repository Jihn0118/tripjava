package site.tripjava.tripjava.heart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.heart.model.BoardAndMember;
import site.tripjava.tripjava.heart.model.HeartRequest;
import site.tripjava.tripjava.heart.model.service.HeartService;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/count/{contentId}")
    public ResponseEntity<Integer> getHeartCount(@PathVariable int contentId) throws SQLException {
        return ResponseEntity.ok(heartService.getHeartCount(contentId));
    }

    @GetMapping("/heartState")
    public ResponseEntity<Boolean> getHeartState(BoardAndMember boardAndMember) throws SQLException{
        return ResponseEntity.ok(heartService.getHeartState(boardAndMember));
    }

    @PostMapping
    public ResponseEntity<Void> doHeart(@RequestBody HeartRequest heartRequest) throws SQLException {
        heartService.doHeart(heartRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelHeart(@RequestBody HeartRequest heartRequest) throws SQLException {
        heartService.cancelHeart(heartRequest);
        return ResponseEntity.ok().build();
    }

}
