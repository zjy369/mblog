/*
 * Copyright 2016 qyh.me
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
package me.qyh.blog.template.event;

import org.springframework.context.ApplicationEvent;

import me.qyh.blog.core.event.EventType;
import me.qyh.blog.template.entity.Page;

/**
 * 页面操作事件
 * 
 * @author Administrator
 *
 */
public class PageEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EventType type;
	private final Page page;

	public PageEvent(Object source, EventType type, Page page) {
		super(source);
		this.type = type;
		this.page = page;
	}

	public EventType getType() {
		return type;
	}

	public Page getPage() {
		return page;
	}

}
