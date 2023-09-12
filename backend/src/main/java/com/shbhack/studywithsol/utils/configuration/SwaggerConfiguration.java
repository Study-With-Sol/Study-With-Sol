package com.shbhack.studywithsol.utils.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

    /**
     * local 접속 UR
     * http://localhost:8080/swagger-ui/
     */

    @Bean
    public Docket swaggerAPI(){
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shbhack.studywithsol"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Study With Sol")
                .description("신한은행 해커톤 팀 : SES")
                .version("1.0")
                .build();
    }

}
