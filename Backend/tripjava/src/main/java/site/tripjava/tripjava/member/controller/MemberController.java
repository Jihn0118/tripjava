package site.tripjava.tripjava.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.tripjava.tripjava.member.model.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/join")
    public String showJoin(){
        return "이건뭐임";
    }

    @PostMapping("/join")
    public String join(){
        return "redirect:/";
    }
}
