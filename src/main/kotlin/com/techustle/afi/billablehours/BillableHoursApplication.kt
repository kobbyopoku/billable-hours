package com.techustle.afi.billablehours

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@CrossOrigin(origins = ["*"])
@SpringBootApplication
class BillableHoursApplication

    fun main(args: Array<String>) {
        runApplication<BillableHoursApplication>(*args)
    }



@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiEndPointsInfo())
    }

    fun apiEndPointsInfo(): ApiInfo {
        return  ApiInfoBuilder().title("Billable Rating System")
                .description("A Kotlin REST Api ")
                .contact( Contact("Godwin Opoku Duah", "https://www.linkedin.com/in/godwin-duah/", "gentlekobby@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
