package com.bm.commont.config;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

/**
 * 描述:
 * 配置Swagger2的相关配置信息，可以扫描多个包下的接口
 * URL ：返回所有接口数据：http://localhost:8080/v2/api-docs
 * URL： 首页：http://localhost:8080/swagger-ui.html
 *
 * @author 北明软件
 * @create 2020-07-30 10:37
 */
@Slf4j
@Configuration
@EnableSwagger2
@SuppressWarnings("ALL")
public class Swagger2Config {
    public Swagger2Config() {
        super();
    }

    /**
     * 定义分隔符
     */
    private static final String SPLITOR = ";";

    /**
     * 扫描包下的接口，生成API在线文档
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(basePackage("com.bm.sys.controller;com.bm.word.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("WEB后台管理平台API文档").description("RESTFUL风格接口")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * @param basePackage 所有包路径
     * @return Predicate<RequestHandler>
     * @author qinguoqing
     * @description 重写basePackage方法，使能够实现多包访问
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)::apply).orElse(true);
    }

    /**
     * @param basePackage 所有包路径
     * @return Function<Class < ?>, Boolean>
     * @author qinguoqing
     * @description 重写basePackage方法，使能够实现多包访问
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                assert input != null;
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * @param input
     * @return Optional<? extends Class < ?>>
     * @author qinguoqing
     * @description 重写basePackage方法，使能够实现多包访问
     */
    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

}
