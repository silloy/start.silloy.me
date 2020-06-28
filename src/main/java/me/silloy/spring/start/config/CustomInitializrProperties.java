package me.silloy.spring.start.config;

import io.spring.initializr.metadata.InitializrProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author shaohuasu
 * @since 1.8
 */
@Data
@ConfigurationProperties("custom")
public class CustomInitializrProperties {
  @NestedConfigurationProperty
  InitializrProperties initializr = new InitializrProperties();
}
