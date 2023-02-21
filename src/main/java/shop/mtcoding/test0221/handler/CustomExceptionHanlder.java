package shop.mtcoding.test0221.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.test0221.dto.ResponseDto;
import shop.mtcoding.test0221.handler.ex.CustomApiException;
import shop.mtcoding.test0221.handler.ex.CustomException;
import shop.mtcoding.test0221.util.Script;

@RestControllerAdvice
public class CustomExceptionHanlder {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        String responsbody = Script.back(e.getMessage());
        return new ResponseEntity<>(responsbody, e.getStatus()); // body, 상태값

    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> customApiException(CustomApiException e) {
        return new ResponseEntity<>(new ResponseDto<>(-1, e.getMessage(), null), e.getStatus());
    }
}
// @REstControllerAdvice, ResponseEntity - 데이터를 리턴할 때 쓴다.
