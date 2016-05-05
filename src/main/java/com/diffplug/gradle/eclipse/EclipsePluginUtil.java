/*
 * Copyright 2016 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.gradle.eclipse;

import java.util.function.Consumer;

import org.gradle.api.Project;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;

import com.diffplug.gradle.ProjectPlugin;

public class EclipsePluginUtil {
	/** Applies the EclipsePlugin and provides the eclipse model for modification. */
	public static void modifyEclipseProject(Project project, Consumer<EclipseModel> modifier) {
		// make sure the eclipse plugin has been applied
		ProjectPlugin.getPlugin(project, EclipsePlugin.class);

		// exclude the build folder
		project.afterEvaluate(p -> {
			EclipseModel eclipseModel = p.getExtensions().getByType(EclipseModel.class);
			modifier.accept(eclipseModel);
		});
	}
}