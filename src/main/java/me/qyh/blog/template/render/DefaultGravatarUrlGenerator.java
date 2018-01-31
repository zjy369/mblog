/*
 * Copyright 2018 qyh.me
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
package me.qyh.blog.template.render;

public final class DefaultGravatarUrlGenerator implements GravatarUrlGenerator {

	private final String urlPrefix;

	public DefaultGravatarUrlGenerator(String urlPrefix) {
		super();
		String finalPrefix = urlPrefix;
		if (!finalPrefix.endsWith("/")) {
			finalPrefix += "/";
		}
		this.urlPrefix = finalPrefix;
	}

	@Override
	public String getUrl(String md5) {
		return urlPrefix + md5;
	}
}