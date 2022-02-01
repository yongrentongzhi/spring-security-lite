

package org.springframework.security.authority;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {

	String getAuthority();
}
