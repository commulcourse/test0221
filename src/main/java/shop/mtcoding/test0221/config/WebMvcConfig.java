package shop.mtcoding.test0221.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shop.mtcoding.test0221.intercept.SampleInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

        @Autowired
        private SampleInterceptor sampleInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(sampleInterceptor)
                                .addPathPatterns("/**") // 인터셉터를 적용할 URL 패턴 설정
                                .excludePathPatterns("/exclude/**"); // 인터셉터를 적용하지 않을 URL 패턴 설정
        }
}