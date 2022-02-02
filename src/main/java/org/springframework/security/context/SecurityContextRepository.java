/*
 * Copyright 2002-2016 the original author or authors.
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
package org.springframework.security.context;

import org.springframework.security.web.HttpRequestResponseHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于在请求之间保持SecurityContext的策略。
 * SecurityContextPersistenceFilter使用它来获取应该用于当前执行线程的上下文，并在上下文从线程本地存储中删除并且请求完成后存储该上下文。
 * 使用的持久性机制将取决于实现，但最常见的是HttpSession将用于存储上下文
 */
public interface SecurityContextRepository {

	/**
	 *
	 * 获取所提供请求的安全上下文。对于未经身份验证的用户，应该返回一个空的上下文实现。此方法不应返回 null。
	 * HttpRequestResponseHolder参数的使用允许实现返回请求或响应（或两者）的包装版本，
	 * 允许它们访问请求的特定于实现的状态。从持有者获得的值将传递给过滤器链，
	 * 并在最终调用时传递给saveContext方法。
	 * 实现可能希望返回SaveContextOnUpdateOrErrorResponseWrapper的一个子类作为响应对象，
	 * 这可以保证在发生错误或重定向时保持上下文。
	 * @param requestResponseHolder holder for the current request and response for which
	 * the context should be loaded.
	 *
	 * @return The security context which should be used for the current request, never
	 * null.
	 */
	SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder);

	/**
	 * Stores the security context on completion of a request.
	 *
	 * @param context the non-null context which was obtained from the holder.
	 * @param request
	 * @param response
	 */
	void saveContext(SecurityContext context, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * Allows the repository to be queried as to whether it contains a security context
	 * for the current request.
	 *
	 * @param request the current request
	 * @return true if a context is found for the request, false otherwise
	 */
	boolean containsContext(HttpServletRequest request);
}
