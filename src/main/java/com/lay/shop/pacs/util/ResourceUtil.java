package com.lay.shop.pacs.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 获取资源，目前的主要方法都是读取Classpath下的资源
 * @author liuliu
 *
 */
public class ResourceUtil {
	
	/**
	 * 获取资源流
	 * @param resourceName
	 * @return
	 */
	public static InputStream getResourceAsStream(String resourceName) {
        return getResourceAsStream(resourceName,null);
    }

	/**
	 * 获取资源流
	 * @param resourceName
	 * @param callingClass
	 * @return
	 */
	public static InputStream getResourceAsStream(String resourceName, Class<?> callingClass) {
        URL url = getResource(resourceName, callingClass);
        try {
            return (url != null) ? url.openStream() : null;
        } catch (IOException e) {
            return null;
        }
    }
	
	/**
	 * 获取资源URL
	 * @param resourceName
	 * @return
	 */
	public static URL getResource(String resourceName) {
        return getResource(resourceName, null);
    }
		
	/**
	 * 获取资源URL
	 * @param resourceName
	 * @param callingClass
	 * @return
	 */
	public static URL getResource(String resourceName, Class<?> callingClass) {
        URL url = null;
        url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null) {
            url = ResourceUtil.class.getClassLoader().getResource(resourceName);
        }
        if (url == null && callingClass != null) {
            url = callingClass.getClassLoader().getResource(resourceName);
        }
        return url;
    }
}
