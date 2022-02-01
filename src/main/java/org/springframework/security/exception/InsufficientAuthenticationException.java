/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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

package org.springframework.security.exception;

/**
 * 如果身份验证请求因凭据不够受信任而被拒绝，则抛出此异常。
 * 如果AccessDecisionVoter对身份验证级别不满意，例如使用记住我机制或匿名执行，则通常会抛出此异常。
 * @author Ben Alex
 */
public class InsufficientAuthenticationException extends AuthenticationException {
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs an <code>InsufficientAuthenticationException</code> with the specified
	 * message.
	 *
	 * @param msg the detail message
	 */
	public InsufficientAuthenticationException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an <code>InsufficientAuthenticationException</code> with the specified
	 * message and root cause.
	 *
	 * @param msg the detail message
	 * @param t root cause
	 */
	public InsufficientAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
