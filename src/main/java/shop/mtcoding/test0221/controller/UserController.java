package shop.mtcoding.test0221.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.test0221.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.test0221.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.test0221.handler.ex.CustomException;
import shop.mtcoding.test0221.model.User;
import shop.mtcoding.test0221.model.UserRepository;
import shop.mtcoding.test0221.service.UserService;
import shop.mtcoding.test0221.util.PasswordEncoder;

@Controller
public class UserController {

        @Autowired
        private HttpSession session;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @PostMapping("/login")
        public String login(LoginReqDto loginReqDto, HttpSession session) {
                if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
                        throw new CustomException("username을 입력해주세요", HttpStatus.BAD_REQUEST);
                }
                if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
                        throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
                }

                // password라는 변수를 만들어 줘도 되고, 값을 바로 Repository 매개변수 자리에 넣어도 됨.
                String password = PasswordEncoder.encode(loginReqDto.getPassword());
                User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), password);

                if (principal == null) {
                        throw new CustomException("아이디 혹은 비밀번호가 틀렸습니다", HttpStatus.BAD_REQUEST);
                }
                session.setAttribute("principal", principal);

                return "redirect:/main";

        }

        @PostMapping("/join")
        public String join(JoinReqDto joinReqDto) {

                if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
                        throw new CustomException("username을 입력해주세요", HttpStatus.BAD_REQUEST);
                }
                if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
                        throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
                }
                if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
                        throw new CustomException("email을 입력해주세요", HttpStatus.BAD_REQUEST);
                }

                userService.회원가입(joinReqDto);

                return "redirect:/loginForm"; // 302
        }

        @GetMapping("/joinForm")
        public String joinForm() {
                return "user/joinForm";
        }

        @GetMapping("/loginForm")
        public String loginForm() {
                return "user/loginForm";
        }

        @GetMapping({ "/", "/main" })
        public String main() {
                User principal = (User) session.getAttribute("principal");

                if (principal == null) {
                        throw new CustomException("로그인을 먼저 해주세요.", HttpStatus.BAD_REQUEST);
                }
                return "main";
        }
}