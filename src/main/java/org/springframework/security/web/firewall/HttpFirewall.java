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
package org.springframework.security.web.firewall;

import org.springframework.security.exception.RequestRejectedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可用于拒绝潜在危险请求和/或包装它们以控制其行为的接口。
 * 该实现被注入到FilterChainProxy中，并将在通过过滤器链发送任何请求之前被调用。 如果还应限制响应行为，它还可以提供响应包装器。
 * @author Luke Taylor
 */
public interface HttpFirewall {

	/**
	 * Provides the request object which will be passed through the filter chain.
	 *
	 * @throws RequestRejectedException if the request should be rejected immediately
	 */
	FirewalledRequest getFirewalledRequest(HttpServletRequest request)
			throws RequestRejectedException;

	/**
	 * Provides the response which will be passed through the filter chain.
	 *
	 * @param response the original response
	 * @return either the original response or a replacement/wrapper.
	 */
	HttpServletResponse getFirewalledResponse(HttpServletResponse response);
}
