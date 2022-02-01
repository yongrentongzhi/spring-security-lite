
package org.springframework.security.authentication;


import org.springframework.security.exception.AuthenticationException;

/**
 * 指示一个类可以处理特定的 Authentication
 *
 * Authentication 拥有众多不同的实现类，这些不同的实现类由不同的 AuthenticationProvider 处理。
 *
 * @author 余涛
 */
public interface AuthenticationProvider {

	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;


	boolean supports(Class<?> authentication);
}
