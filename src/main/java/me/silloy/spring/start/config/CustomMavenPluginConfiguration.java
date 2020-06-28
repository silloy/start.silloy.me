package me.silloy.spring.start.config;

import io.spring.initializr.generator.buildsystem.maven.MavenBuild;
import io.spring.initializr.generator.buildsystem.maven.MavenBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author shaohuasu
 * @since 1.8
 */
@ConditionalOnBuildSystem(MavenBuildSystem.ID)
@ProjectGenerationConfiguration
@ConditionalOnRequestedDependency("custom-maven-plugin")
class CustomMavenPluginConfiguration {

  @Bean
  public BuildCustomizer<MavenBuild> customPluginConfigurer() {
    return (MavenBuild build) -> {
      build.dependencies().ids().filter(it -> it.equals("custom-maven-plugin"))
          .findFirst()
          .map(r -> build.dependencies().get(r)).map(r -> {
        build.plugins().add(r.getGroupId(), r.getArtifactId(),
            (plugin) -> plugin.execution("my-execution",
                (first) -> first.goal("goal").configuration((conf) -> {conf.add("failOnSeverity", "MAJOR");})
            ));
        return build;
      }).orElse(build);
    };
  }


  @Bean
  public BuildCustomizer<MavenBuild> customPluginDependencyRemoval() {
    return build -> build.dependencies().remove("custom-maven-plugin");
  }
}
