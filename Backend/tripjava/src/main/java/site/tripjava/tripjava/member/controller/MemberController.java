package site.tripjava.tripjava.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.tripjava.tripjava.member.model.MemberDto;
import site.tripjava.tripjava.member.model.service.MemberService;
import site.tripjava.tripjava.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {
    private MemberService memberService;
    private JWTUtil jwtUtil;

    public MemberController(MemberService memberService, JWTUtil jwtUtil){
        super();
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody MemberDto memberDto) {
        System.out.println("로그인 들어왔다!!!");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            MemberDto loginUser = memberService.login(memberDto);

            if(loginUser != null) {
                String accessToken = jwtUtil.createAccessToken(loginUser.getId());
                String refreshToken = jwtUtil.createRefreshToken(loginUser.getId());

//				발급받은 refresh token을 DB에 저장.
                memberService.saveRefreshToken(loginUser.getId(), refreshToken);

                System.out.println("야호" + memberService.getRefreshToken(loginUser.getId()).toString());
//				JSON으로 token 전달.
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }

        } catch (Exception e) {
            log.debug("로그인 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userId") String userId,
            HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                MemberDto memberDto = memberService.userInfo(userId);
                resultMap.put("userInfo", memberDto);
                status = HttpStatus.OK;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/logout/{userId}")
    public ResponseEntity<?> removeToken(@PathVariable ("userId") String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            memberService.deleteRefreshToken(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refreshToken");
        log.debug("token : {}, memberDto : {}", token, memberDto);
        if (jwtUtil.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(memberDto.getId()))) {
                String accessToken = jwtUtil.createAccessToken(memberDto.getId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("리프레쉬토큰도 사용불가!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        try {
            memberService.join(memberDto);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("회원가입 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        try {
            memberService.deleteMember(id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("회원탈퇴 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable String id, @RequestBody MemberDto memberDto){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        memberDto.setId(id);

        try {
            memberService.updateMember(memberDto);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("회원 정보 수정 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/checkid/{id}")
    public ResponseEntity<?> checkId(@PathVariable String id){
        System.out.println("아이디체크 들어옴");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            int result = memberService.checkId(id);
            status = HttpStatus.OK;
            resultMap.put("result", result);
        } catch (Exception e) {
            System.out.println("아이디 체크 실패");
            log.error("아이디 체크 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
