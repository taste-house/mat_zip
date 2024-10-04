package com.matzip.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

/*
	# 요청 경로를 재작성. '/first-service/' 이후의 모든 경로를 추출
  # 1. 정규 표현식: (?<segment>.*)
  # (?<segment>...): 이 부분은 정규 표현식에서 named capturing group을 정의.
  # segment라는 이름을 가진 캡처 그룹을 만들고, 이 그룹이 매칭하는 내용을 추출
  #.*: 이 패턴은 임의의 문자가 0개 이상 있는 경우를 의미. 즉, /first-service/ 이후의 모든 내용을 포함할 수 있다.
  # 만약 요청 경로가 /first-service/example/path라면, (?<segment>.*)는 example/path를 캡처
  # 2. 경로 재작성: /$\{segment}
  # /$\{segment}: 이 부분은 캡처된 내용을 사용하여 경로를 재작성하는 부분
  # $\{segment}는 앞서 정의한 segment 그룹에서 캡처한 내용을 참조. 즉, 캡처된 example/path가 이 위치에 삽입.
* */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator routes(RouteLocatorBuilder builder) {
//		return builder
//				.routes()
//				.route(r -> r.path("/back/v3/api-docs").and().method(HttpMethod.GET).uri("lb://MATZIPBACK"))
////				.route(r -> r.path("/product-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://product-service"))
//				.build();
//	}
}
