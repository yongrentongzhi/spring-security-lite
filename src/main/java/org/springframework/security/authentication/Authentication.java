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

package org.springframework.security.authentication;

import org.springframework.security.authority.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;


/**
 * 保存用户的实现类，不同的认证方式对应不同的Authentication实例
 * @author 余涛
 */
public interface Authentication extends Principal, Serializable {

	/**
	 * 获取用户的权限
	 */
	Collection<? extends GrantedAuthority> getAuthorities();

	/**
	 * 获取用户的凭证
	 */
	Object getCredentials();

	/**
	 * 获取用户携带的详细信息，可能是当前的请求之类的
	 */
	Object getDetails();

	/**
	 * 获取当前用户信息，可能是一个用户名，也可能是一个用户对象
	 */
	Object getPrincipal();

	/**
	 * 当前用户是否认证成功
	 */
	boolean isAuthenticated();

}
