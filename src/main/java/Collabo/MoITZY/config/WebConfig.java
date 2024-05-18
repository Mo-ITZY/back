package Collabo.MoITZY.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // CORS를 적용할 패턴 지정
                .allowedOrigins("http://localhost:5173") // 허용할 출처 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 지정
                .allowedHeaders("*") // 허용할 헤더 지정
                .allowCredentials(true) // 쿠키 전송 허용 여부 지정
                .maxAge(3600); // 프리플라이트 요청에 대한 캐시 시간(초) 지정
    }
}
