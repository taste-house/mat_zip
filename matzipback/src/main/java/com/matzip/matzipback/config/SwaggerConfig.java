package com.matzip.matzipback.config;

import com.matzip.matzipback.sercurity.filter.CustomAuthenticationFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import java.util.Optional;

@Configuration
public class SwaggerConfig {

    private final ApplicationContext applicationContext;

    public SwaggerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("MatZip Back API") // API의 제목
                .description("맛집 백엔드 API") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }

    // Swagger에 CustomLoginFilter 활성화
    @Bean
    OpenApiCustomizer springSecurityLoginEndpointCustomizer() {
        FilterChainProxy filterChainProxy = applicationContext.getBean(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, FilterChainProxy.class);
        return openAPI -> {
            for (SecurityFilterChain filterChain : filterChainProxy.getFilterChains()) {
                Optional<CustomAuthenticationFilter> optionalFilter =
                        filterChain.getFilters().stream()
                                .filter(CustomAuthenticationFilter.class::isInstance)
                                .map(CustomAuthenticationFilter.class::cast)
                                .findAny();
                if (optionalFilter.isPresent()) {
                    CustomAuthenticationFilter customAuthenticationFilter = optionalFilter.get();
                    Operation operation = new Operation();
                    Schema<?> schema = new ObjectSchema()
                            .addProperties("userEmail", new StringSchema())
                            .addProperties("userPassword", new StringSchema());
                    RequestBody requestBody = new RequestBody().content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType().schema(schema)));
                    operation.requestBody(requestBody);
                    ApiResponses apiResponses = new ApiResponses();
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.OK.value()), new ApiResponse().description(HttpStatus.OK.getReasonPhrase()));
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), new ApiResponse().description(HttpStatus.FORBIDDEN.getReasonPhrase()));
                    operation.responses(apiResponses);
                    operation.addTagsItem("Login");
                    operation.setSummary("로그인");
                    operation.setDescription("로그인 기능");
                    PathItem pathItem = new PathItem().post(operation);
                    openAPI.getPaths().addPathItem("/back/api/vi/auth/login", pathItem);
                }
            }
        };
    }
}
