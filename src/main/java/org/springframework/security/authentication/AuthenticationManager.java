
package org.springframework.security.authentication;


import org.springframework.security.exception.AuthenticationException;

/**
 * 处理认证请求
 *
 * @author 余涛
 */
public interface AuthenticationManager {

	/**
	 * 返回 Authentication 表示认证成功，抛出异常表示用户输入了无效的凭证，返回 null 表示不能断定
	 * @param authentication
	 * @return
	 * @throws AuthenticationException
	 */
	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;
}
