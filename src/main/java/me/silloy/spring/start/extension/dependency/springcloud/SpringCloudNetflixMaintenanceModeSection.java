/*
 * Copyright 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.silloy.spring.start.extension.dependency.springcloud;

import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.io.text.BulletedSection;
import io.spring.initializr.generator.io.text.Section;
import io.spring.initializr.generator.spring.documentation.PreDefinedSection;
import java.util.Set;

/**
 * A {@link Section} that provides information about Spring Cloud Netflix maintenance mode
 * dependencies requested by the user.
 *
 * @author Olga Maciaszek-Sharma
 */
class SpringCloudNetflixMaintenanceModeSection extends PreDefinedSection {

	SpringCloudNetflixMaintenanceModeSection(Set<String> maintenanceModeDependencies,
			TemplateRenderer templateRenderer) {
		super("Spring Cloud Netflix Maintenance Mode");
		BulletedSection<String> dependencies = new BulletedSection<>(templateRenderer,
				"spring-cloud-netflix-maintenance-mode");
		maintenanceModeDependencies.forEach(dependencies::addItem);
		addSection(dependencies);
	}

}
