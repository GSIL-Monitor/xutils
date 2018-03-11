package com.ckjava.xutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
	
	/**
	 * 对象的所有字段都为空才返回true
	 * 
	 * @param obj Object类型的对象
	 * @return 对象的所有字段都为空 返回true, 否则返回 false
	 */
	public static boolean isEmptyObject(Object obj) {
		if (obj == null || obj.toString().equals("")) {
			return true;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				boolean flag = field.isAccessible();
				boolean isStatic = Modifier.toString(field.getModifiers()).contains("static");
				if (isStatic) {
					continue;
				}
				if (!flag) {
					field.setAccessible(true);
				}
				Object fieldObj = field.get(obj);
				if (fieldObj != null && !fieldObj.toString().equals("")) {
					return false;
				}
			} catch (IllegalAccessException e) {
				logger.error("ObjectUtils isEmptyObject has error", e);
			}
		}
		return true;
	}
	
	/**
	 * 对象的所有字段都不为空才返回 true
	 * 
	 * @param obj Object类型的对象
	 * @return 对象的所有字段都不为空 返回true, 否则返回 false
	 */
	public static boolean isNotEmptyObject(Object obj) {
		if (obj == null || obj.toString().equals("")) {
			return false;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				boolean flag = field.isAccessible();
				boolean isStatic = Modifier.toString(field.getModifiers()).contains("static");
				if (isStatic) {
					continue;
				}
				if (!flag) {
					field.setAccessible(true);
				}
				Object fieldObj = field.get(obj);
				if (fieldObj == null || fieldObj.toString().equals("")) {
					return false;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 将一个对象中不为空的字段拼接成 {@code key1=value1&key2=value2}
	 * 
	 * @param obj Object类型的对象
	 * @return 字符串
	 */
	public static String getObjectString(Object obj) {
		if (obj == null || obj.toString().equals(StringUtils.EMPTY)) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuilder data = new StringBuilder();
		for (Field field : fields) {
			try {
				boolean flag = field.isAccessible();
				boolean isStatic = Modifier.toString(field.getModifiers()).contains("static");
				if (isStatic) {
					continue;
				}
				if (!flag) {
					field.setAccessible(true);
				}
				Class<?> clazz = field.getType();
				if (clazz.equals(obj.getClass())) {
					continue;
				}
				Object fieldObj = field.get(obj);
				if (fieldObj != null && !fieldObj.toString().equals("")) {
					data.append(field.getName()).append("=").append(String.valueOf(fieldObj)).append("&");
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return data.toString();
	}
	
	/**
	 * 将 {@code key1=value1&key2=value2} 的字符串存入Map中
	 * 
	 * @param data 字符串
	 * @return {@code Map<String, String> } 对象
	 */
	public static Map<String, String> fillMapWithString(String data) {
		Map<String, String> dataMap = new HashMap<>();
		if (StringUtils.isBlank(data)) {
			return dataMap;
		}
		String[] datas = data.split(SPLITER.AND);
		for (String str : datas) {
			String[] keyValue = str.split(SPLITER.EQUAL);
			String key = ArrayUtils.getValue(keyValue, 0);
			String value = ArrayUtils.getValue(keyValue, 1);
			dataMap.put(StringUtils.trim(key), StringUtils.trim(value));
		}
		return dataMap;
	}
	
	/**
	 * 将 {@code key1=value1 spliter key2=value2} 的字符串存入Map中 <br>
	 * 当 spliter 为 {@code & } 的时候, data 字符串为 {@code key1=value1&key2=value2 }<br>
	 * 当 spliter 为 {@code ; } 的时候, data 字符串为 {@code key1=value1;key2=value2 }<br>
	 * 
	 * @param data 字符串
	 * @param spliter 字符串分割器
	 * @param limit 参考 {@code java.lang.String.split(String regex, int limit) } 中的 limit 参数用法
	 * @return {@code Map<String, String> } 对象
	 */
	public static Map<String, String> fillMapWithString(String data, String spliter, int limit) {
		Map<String, String> dataMap = new HashMap<>();
		if (StringUtils.isBlank(data)) {
			return dataMap;
		}
		String[] datas = data.split(spliter);
		for (String str : datas) {
			String[] keyValue = str.split(SPLITER.EQUAL, limit);
			String key = ArrayUtils.getValue(keyValue, 0);
			String value = ArrayUtils.getValue(keyValue, 1);
			dataMap.put(StringUtils.trim(key), StringUtils.trim(value));
		}
		return dataMap;
	}
	
	/**
	 * 将 {@code key1{@code spliter2}value1{@code spliter1}key2{@code spliter2}value2} 的字符串存入Map中 <br>
	 * 比如当 spliter1 为 {@code &}, spliter2 为 {@code =} 的时候, data 字符串为 {@code key1=value1&key2=value2 }<br>
	 * 
	 * @param data 字符串
	 * @param spliter1 字符串分割器1
	 * @param spliter2 字符串分割器2
	 * @return {@code Map<String, String> } 对象
	 */
	public static Map<String, String> fillMapWithString(String data, String spliter1, String spliter2) {
		Map<String, String> dataMap = new HashMap<>();
		if (StringUtils.isBlank(data)) {
			return dataMap;
		}
		String[] datas = data.split(spliter1);
		for (String str : datas) {
			String[] keyValue = str.split(spliter2);
			String key = ArrayUtils.getValue(keyValue, 0);
			String value = ArrayUtils.getValue(keyValue, 1);
			dataMap.put(StringUtils.trim(key), StringUtils.trim(value));
		}
		return dataMap;
	}
	
	/**
	 * 将Java对象Object转换成Byte字节数组
	 * 
	 * @param object Serializable对象
	 * @return byte[] 对象
	 */
	public static byte[] objectToBytes(final Serializable object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos =  null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
        	logger.error("ObjectUtils.objectToBytes has error", e);
			return null;
		} finally {
        	try {
        		oos.close();
            	baos.close();
			} catch (Exception e) {
			}
        }
    }
	
	/**
	 * 将 Byte字节数组 转成 Java 对象
	 * @param bytes byte[]对象
	 * @return Object对象
	 */
	public static Object bytesToObject(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois =  null;
        try {
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	logger.error("ObjectUtils.bytesToObject has error", e);
			return null;
		} finally {
        	try {
        		ois.close();
        		bais.close();
			} catch (Exception e) {
			}
        }
    }
	
	/**
	 * 将 List 中的对象中的属性存放到 Map 中
	 * 
	 * @param <T> 任意对象
	 * @param dataList List 集合, 包含对象 T
	 * @param mKey List 集合中的对象 T 的方法名, 返回的值存放到 Map 中作为 key
	 * @param mValue List 集合中的对象 T 的方法名, 返回的值存放到 Map 中作为 value
	 * @return {@code Map<String, String>} Map 对象 
	 * 
	 */
	public static <T> Map<String, String> mPull(List<T> dataList, String mKey, String mValue) {
		Map<String, String> resultMap = new HashMap<String, String>();
		for (int i = 0, c = CollectionUtils.getSize(dataList); i < c; i++) {
			T t = dataList.get(i);
			Class<? extends Object> clazz = t.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			
			String key = null;
			String value = null;
			Object[] emptyObj = new Object[]{};
			
			for (Method method : methods) {
				String methodName = method.getName();
				Object methodReturnValue = null;
				try {
					methodReturnValue = method.invoke(t, emptyObj);
				} catch (Exception e) {
					logger.error("CollectionUtils mPull has error", e);
				}
				if (methodName.equals(mKey)) {
					key = StringUtils.getStr(methodReturnValue);
				}
				if (methodName.equals(mValue)) {
					value = StringUtils.getStr(methodReturnValue);
				}
			}
			
			if (StringUtils.isNotBlank(key)) {
				resultMap.put(key, value);
			}
		}
		return resultMap;
	}

}
