
package org.springframework.security.model;


import java.io.Serializable;

/**
 * 存储配置属性
 */
public interface ConfigAttribute extends Serializable {

	String getAttribute();
}
