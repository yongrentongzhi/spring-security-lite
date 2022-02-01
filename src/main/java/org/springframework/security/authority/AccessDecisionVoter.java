
package org.springframework.security.authority;


import org.springframework.security.authentication.Authentication;
import org.springframework.security.model.ConfigAttribute;

import java.util.Collection;

/**
 * 投票器
 */
public interface AccessDecisionVoter<S> {


	int ACCESS_GRANTED = 1;
	int ACCESS_ABSTAIN = 0;
	int ACCESS_DENIED = -1;

	// ~ Methods
	// ========================================================================================================

	/**
	 * Indicates whether this {@code AccessDecisionVoter} is able to vote on the passed
	 * {@code ConfigAttribute}.
	 * <p>
	 * This allows the {@code AbstractSecurityInterceptor} to check every configuration
	 * attribute can be consumed by the configured {@code AccessDecisionManager} and/or
	 * {@code RunAsManager} and/or {@code AfterInvocationManager}.
	 *
	 * @param attribute a configuration attribute that has been configured against the
	 * {@code AbstractSecurityInterceptor}
	 *
	 * @return true if this {@code AccessDecisionVoter} can support the passed
	 * configuration attribute
	 */
	boolean supports(ConfigAttribute attribute);

	/**
	 * Indicates whether the {@code AccessDecisionVoter} implementation is able to provide
	 * access control votes for the indicated secured object type.
	 *
	 * @param clazz the class that is being queried
	 *
	 * @return true if the implementation can process the indicated class
	 */
	boolean supports(Class<?> clazz);

	/**
	 * 指示是否授予访问权限。
	 * 决定必须是肯定的 ( ACCESS_GRANTED )、否定的 ( ACCESS_DENIED ) 或AccessDecisionVoter可以弃权 ( ACCESS_ABSTAIN ) 投票。在任何情况下，实现类都不应该返回任何其他值。
	 * @param authentication the caller making the invocation
	 * @param object the secured object being invoked
	 * @param attributes the configuration attributes associated with the secured object
	 *
	 * @return either {@link #ACCESS_GRANTED}, {@link #ACCESS_ABSTAIN} or
	 * {@link #ACCESS_DENIED}
	 */
	int vote(Authentication authentication, S object,
			 Collection<ConfigAttribute> attributes);
}
