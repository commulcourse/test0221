package shop.mtcoding.test0221.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.test0221.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.test0221.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.test0221.handler.ex.CustomException;
import shop.mtcoding.test0221.model.User;
import shop.mtcoding.test0221.model.UserRepository;

@Service
public class UserService {

        @Autowired
        public UserRepository userRepository;

        @Transactional
        public void 회원가입(JoinReqDto joinReqDto) {
                User sameUser = userRepository.findByUsername(joinReqDto.getUsername());
                if (sameUser != null) {
                        throw new CustomException("동일한 username이 존재합니다");
                }
                int result = userRepository.insert(joinReqDto.getUsername(), joinReqDto.getPassword(),
                                joinReqDto.getEmail());
                if (result != 1) {
                        throw new CustomException("회원가입실패");
                }
        }

        @Transactional(readOnly = true)
        public User 로그인(LoginReqDto loginReqDto) {
                User principal = userRepository.findByUsernameAndPassword(
                                loginReqDto.getUsername(), loginReqDto.getPassword());
                if (principal == null) {
                        throw new CustomException("유저네임 혹은 패스워드가 잘못 입력되었습니다");
                }
                return principal;
        }
}
