/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lay.shop.pacs.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author 江家雷
 * @date 2017年5月25日 下午6:02:50
 * @since
 */
public final class ObjectValidator{

	private ObjectValidator(){}
	
	public final static boolean isNullOrEmpty(Object value){
		if (null == value){
			return true;
		}
		// *****************************************************************************

		// 字符串
		if (value instanceof String){// 比较字符串长度, 效率高
			return value.toString().trim().length() <= 0;
		}

		// 集合
		if (value instanceof Collection){
			return ((Collection<?>) value).isEmpty();
		}

		// map
		if (value instanceof Map){
			return ((Map<?, ?>) value).isEmpty();
		}

		// 枚举
		if (value instanceof Enumeration){
			return !((Enumeration<?>) value).hasMoreElements();
		}

		// Iterator迭代器
		if (value instanceof Iterator){
			return !((Iterator<?>) value).hasNext();
		}

		boolean arrayFlag = arrayIsNullOrEmpty(value);
		if (arrayFlag){
			return true;
		}
		// 这里可以扩展
		return false;
	}

	public final static boolean isNotNullOrEmpty(Object value){
		return !isNullOrEmpty(value);
	}

	/**
	 * 数组 类型的验证,区分 primitive 和包装类型.
	 */
	private static boolean arrayIsNullOrEmpty(Object value){
		// ***********************************************************
		// 数组 Integer/String...自定义的对象User.等数组也 instanceof Object[]
		if (value instanceof Object[]){
			return ((Object[]) value).length == 0;
		}

		// ***********************************************************
		// primitive ints
		if (value instanceof int[]){
			return ((int[]) value).length == 0;
		}

		// primitive long
		if (value instanceof long[]){
			return ((long[]) value).length == 0;
		}

		// primitive float
		if (value instanceof float[]){
			return ((float[]) value).length == 0;
		}

		// primitive double
		if (value instanceof double[]){
			return ((double[]) value).length == 0;
		}

		// primitive char
		if (value instanceof char[]){
			return ((char[]) value).length == 0;
		}

		// primitive boolean
		if (value instanceof boolean[]){
			return ((boolean[]) value).length == 0;
		}

		// primitive byte
		if (value instanceof byte[]){
			return ((byte[]) value).length == 0;
		}

		// primitive short
		if (value instanceof short[]){
			return ((short[]) value).length == 0;
		}
		return false;
	}
}