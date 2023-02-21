package shop.mtcoding.test0221.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.mtcoding.test0221.model.User;

public class UserDao {
        private SqlSession sqlSession;

        public UserDao(SqlSession sqlSession) {
                this.sqlSession = sqlSession;
        }

        public int addUser(User user) {
                // 비밀번호 인코딩
                String encodedPassword = encodePassword(user.getPassword());
                user.setPassword(encodedPassword);

                // 회원 정보 추가
                return sqlSession.insert("addUser", user);
        }

        public User getUserById(String id) {
                return sqlSession.selectOne("getUserById", id);
        }

        public User loginUser(String id, String password) {
                // 비밀번호 인코딩
                String encodedPassword = encodePassword(password);

                // 로그인 정보 확인
                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                map.put("password", encodedPassword);
                return sqlSession.selectOne("loginUser", map);
        }

        private String encodePassword(String password) {
                try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] bytes = md.digest(password.getBytes());
                        StringBuilder sb = new StringBuilder();
                        for (byte b : bytes) {
                                sb.append(String.format("%02x", b));
                        }
                        return sb.toString();
                } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                }
        }
}
