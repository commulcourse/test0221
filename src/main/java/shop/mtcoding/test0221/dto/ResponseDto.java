package shop.mtcoding.test0221.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ResponseDto<T> {
    private int code;
    private String msg;
    private T data;
}
