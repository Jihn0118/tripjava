package site.tripjava.tripjava.member.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest     // 스프링부트 관련 컴포넌트 테스트 할 때 붙여야 함, IoC 컨테이너 작동 시킴, 컨트롤러, 서비스, 매퍼 테스트할 때 있어야 함
@AutoConfigureMockMvc   // http 요청과 응답 테스트 할 때 MockMVC 사용
@Transactional // 실제로 테스트에서 발생한 DB 작업이 영구적으로 적용 되지 않도록, test+ 트랜잭션 => 자동 롤백
//@ActiveProfiles("text") // application-test.properties를 활성화 시킴
public class MemberControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("회원가입 폼")
    void t001() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/member/join"))
                .andDo(print()); // 크게 의미 없고, 그냥 확인용

        // /member/join으로 get 요청해서 resultActions 결과 확인

        // THEN
        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showJoin"))
                .andExpect(status().is2xxSuccessful());
    }
}
