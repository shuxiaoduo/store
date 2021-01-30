package com.example.store.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */
@Configuration
class CORSConf {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:8086")
                        .allowCredentials(true);
            }
        };
    }
}
//@Configuration
//public class Config implements WebMvcConfigurer {
//
//    public CorsConfiguration buildConfig(){
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*"); //允许任何域名
//        config.addAllowedHeader("*"); //允许任何头
//        config.addAllowedMethod("*"); //允许任何方法
//        config.addAllowedOrigin("*");
//        config.allow
//        config.setMaxAge(Duration.ofDays(30));   //设置30天之内不在发送OPTIONS预请求
//        return config;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig());
//        return new CorsFilter(source);
//    }
//
//}
