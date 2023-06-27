package com.oxog.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    //bean 외부 라이브러리를 종속
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(api -> api.path("/first-service/**")// 1.path를 확인하고
                        .filters(f -> f// 2.필터를 적용하여
                                .addRequestHeader("first-request", "first-service-requestHeader") // key , value 형태로
                                .addResponseHeader("first-response", "first-service-responseHeader"))
                        .uri("http://localhost:8081"))//uri로 이동시켜준다.

                .route(api -> api.path("/second-service/**")
                        .filters(f -> f
                                .addRequestHeader("second-request", "second-service-requestHeader")
                                .addResponseHeader("second-response", "second-service-responseHeader"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
