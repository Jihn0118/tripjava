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
        System.out.println("hello");
        System.out.println(boardAndMember);
        return ResponseEntity.ok(heartService.getHeartState(boardAndMember));
    }

    @PostMapping
    public ResponseEntity<Void> doHeart(@RequestBody HeartRequest heartRequest) throws SQLException {
        System.out.println("?-0980979087");
        heartService.doHeart(heartRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelHeart(@RequestBody HeartRequest heartRequest) throws SQLException {
        System.out.println("?123123123123123");
        heartService.cancelHeart(heartRequest);
        return ResponseEntity.ok().build();
    }

}
