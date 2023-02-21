package shop.mtcoding.test0221.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 컨트롤러 실행 전에 수행될 코드 작성
        return true; // true 반환 시, 다음 단계 수행 / false 반환 시, 컨트롤러 실행 중단
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 컨트롤러 실행 후, View 렌더링 전에 수행될 코드 작성
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // View 렌더링 후, 클라이언트에게 응답 전에 수행될 코드 작성
    }
}