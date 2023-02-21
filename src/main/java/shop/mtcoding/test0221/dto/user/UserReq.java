package shop.mtcoding.test0221.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserReq {

        @Setter
        @Getter
        public static class JoinReqDto {

                private String username;
                private String password;
                private String email;
        }

        @AllArgsConstructor
        @Getter
        public static class LoginReqDto {

                private String username;
                private String password;
        }
}