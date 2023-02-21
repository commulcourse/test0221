package shop.mtcoding.test0221.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

        private Integer id;
        private String username;
        private String password;
        private String email;
        private Timestamp createdAt;
}
