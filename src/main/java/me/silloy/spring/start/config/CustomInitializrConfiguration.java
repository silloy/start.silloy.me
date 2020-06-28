package me.silloy.spring.start.config;

import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaohuasu
 * @since 1.8
 */
@Configuration
@EnableConfigurationProperties(CustomInitializrProperties.class)
public class CustomInitializrConfiguration {

  @Bean
  public DefaultInitializrMetadataProvider customInitializrMetadataProvider(InitializrProperties initializrProperties,
      CustomInitializrProperties customInitializrProperties,
      InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy) {
    InitializrMetadata var4 = InitializrMetadataBuilder.fromInitializrProperties(customInitializrProperties.getInitializr())
        .withInitializrProperties(initializrProperties, true).build();
    return new DefaultInitializrMetadataProvider(var4, initializrMetadataUpdateStrategy);
  }

}


