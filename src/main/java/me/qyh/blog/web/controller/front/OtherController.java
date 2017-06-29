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
package me.qyh.blog.web.controller.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.qyh.blog.core.entity.Fragment;
import me.qyh.blog.core.exception.LogicException;
import me.qyh.blog.core.security.Environment;
import me.qyh.blog.core.service.TemplateService;
import me.qyh.blog.core.vo.DataTag;
import me.qyh.blog.web.JsonResult;
import me.qyh.blog.web.Webs;
import me.qyh.blog.web.template.ParseConfig;
import me.qyh.blog.web.template.TemplateRender;
import me.qyh.blog.web.template.TemplateRenderException;

@Controller
public class OtherController {

	@Autowired
	private TemplateRender templateRender;
	@Autowired
	private TemplateService templateService;

	@GetMapping({ "data/{tagName}", "space/{alias}/data/{tagName}" })
	@ResponseBody
	public JsonResult queryData(@PathVariable("tagName") String tagName,
			@RequestParam Map<String, String> allRequestParams, HttpServletRequest request,
			HttpServletResponse response) throws LogicException {
		Map<String, String> attMap = new HashMap<>();
		for (Map.Entry<String, String> it : allRequestParams.entrySet()) {
			attMap.put(it.getKey(), it.getValue());
		}
		DataTag tag = new DataTag(Webs.decode(tagName), attMap);
		return templateService.queryData(tag, true).map(bind -> new JsonResult(true, bind))
				.orElse(new JsonResult(false));
	}

	@GetMapping({ "fragment/{fragment}", "space/{alias}/fragment/{fragment}" })
	@ResponseBody
	public JsonResult queryFragment(@PathVariable("fragment") String fragment,
			@RequestParam Map<String, String> allRequestParams, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return new JsonResult(true,
					templateRender.render(Fragment.getTemplateName(Webs.decode(fragment), Environment.getSpace()), null,
							request, response, new ParseConfig(true)));
		} catch (TemplateRenderException e) {
			return new JsonResult(false, e.getRenderErrorDescription());
		}
	}
}
