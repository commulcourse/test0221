package shop.mtcoding.test0221.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.test0221.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) //
public class UserControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private ObjectMapper om;

        @Test
        public void join_test() throws Exception {
                // given
                String requestBody = "username=ssar1&password=1234&email=ssar@nate.com";

                // when
                ResultActions resultActions = mvc.perform(post("/join").content(requestBody)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

                // then
                resultActions.andExpect(status().is3xxRedirection());
        }

        @Test
        public void login_test() throws Exception {
                // given
                String requestBody = "username=ssar&password=1234";

                // when
                ResultActions resultActions = mvc.perform(post("/login").content(requestBody)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)); // 텍스트타입의 파싱전략

                HttpSession session = resultActions.andReturn().getRequest().getSession();
                User principal = (User) session.getAttribute("principal");
                System.out.println(principal);
                // resultActions.andReturn().getModelAndView();
                // resultActions.andReturn().getRequest().getSession();
                // then

                assertThat(principal.getUsername()).isEqualTo("ssar");
                resultActions.andExpect(status().is3xxRedirection());
        }
}