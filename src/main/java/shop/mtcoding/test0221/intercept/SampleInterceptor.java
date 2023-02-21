package shop.mtcoding.test0221.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import shop.mtcoding.test0221.model.User;

@Component
public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            // 로그인되지 않은 사용자는 로그인 페이지로 이동
            response.sendRedirect("/loginForm");
            return false;
        }
        return true;
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