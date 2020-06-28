package me.silloy.spring.start;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.initializr.generator.project.MutableProjectDescription;
import io.spring.initializr.versionresolver.DependencyManagementVersionResolver;
import io.spring.initializr.web.support.SaganInitializrMetadataUpdateStrategy;
import java.io.IOException;
import java.nio.file.Files;
import me.silloy.spring.start.config.CustomInitializrConfiguration;
import me.silloy.spring.start.project.ProjectDescriptionCustomizerConfiguration;
import me.silloy.spring.start.support.CacheableDependencyManagementVersionResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@Import({ProjectDescriptionCustomizerConfiguration.class, CustomInitializrConfiguration.class})
@SpringBootConfiguration
@EnableAutoConfiguration
public class StartApplication {

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }


  @Bean
  public ErrorPageRegistrar errorPageRegistrar() {
    return (registry) -> {
      registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
      registry.addErrorPages(new ErrorPage("/error/index.html"));
    };
  }

  @Bean
  public SaganInitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy(
      RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
    return new SaganInitializrMetadataUpdateStrategy(restTemplateBuilder.build(), objectMapper);
  }

  @Bean
  public DependencyManagementVersionResolver dependencyManagementVersionResolver() throws IOException {
    return new CacheableDependencyManagementVersionResolver(DependencyManagementVersionResolver
        .withCacheLocation(Files.createTempDirectory("version-resolver-cache-")));
  }
}
