package com.ckjava.xutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils implements Constants {

	/**
	 * 建议使用 org.apache.commons.lang3.StringEscapeUtils.escapeHtml4();
	 * 
	 * 入库前过滤特殊字符
	 * 
	 * @param original
	 *            Object对象
	 * @return String 类型
	 */
	@Deprecated
	public static String getCleanXmlString(Object original) {
		String data = getStr(original);
		data = data.replaceAll("&", "&amp;");
		data = data.replaceAll("<", "&lt;");
		data = data.replaceAll(">", "&gt;");
		data = data.replaceAll("\"", "&quot;");
		data = data.replaceAll("'", "&apos;");

		data = data.replaceAll("•", "&#8226;"); // 加重号 U+2022
		return data;
	}

	/**
	 * 建议使用 org.apache.commons.lang3.StringEscapeUtils.escapeXml11(input);
	 * 
	 * 回退过滤特殊字符
	 * 
	 * @param original
	 *            Object对象
	 * @return 字符串
	 */
	@Deprecated
	public static String getDecodeCleanXmlString(Object original) {
		String data = getStr(original);
		data = data.replaceAll("&amp;", "&");
		data = data.replaceAll("&lt;", "<");
		data = data.replaceAll("&gt;", ">");
		data = data.replaceAll("&quot;", "\"");
		data = data.replaceAll("&apos;", "'");
		return data;
	}

	/**
	 * 获取Object对象的 String对象
	 * 
	 * @param obj
	 *            Object对象
	 * @return 字符串
	 */
	public static String getStr(Object obj) {
		return obj != null ? obj.toString() : EMPTY;
	}

	/**
	 * 获取Object对象的 String对象，如果为空返回默认值
	 * 
	 * @param obj
	 *            Object对象
	 * @param defaultStr
	 *            默认值
	 * @return 字符串
	 */
	public static String getStr(Object obj, String defaultStr) {
		String str = getStr(obj);
		return str.equals("") ? defaultStr : str;
	}

	/**
	 * 判断某个 Object对象 里面是否含有指定的字符串
	 * 
	 * @param obj
	 *            Object对象
	 * @param str
	 *            指定的字符串
	 * @return true:含有，false:不含有
	 */
	public static boolean objectHasStr(Object obj, String str) {
		String objStr = getStr(obj);
		return objStr.contains(str);
	}

	/**
	 * 判断字符串是否在字符数组中
	 * 
	 * @param str
	 *            字符串
	 * @param strs
	 *            字符数组
	 * @return true:在，false:不在
	 * 
	 *         <p>
	 *         对于参数 str 和 strs 为空的情况下判断如下
	 *         </p>
	 * 
	 *         {@code
	 * <pre>
	 *         if (str == null && strs == null) {
	 *         	return true;
	 *         }
	 *         if (str == null && strs != null) {
	 *         	return false;
	 *         }
	 *         if (str != null && strs == null) {
	 *         	return false;
	 *         }
	 * </pre>
	 * 
	 *         }
	 */
	public static boolean containsStr(String str, String[] strs) {
		if (str == null && strs == null) {
			return true;
		}
		if (str == null && strs != null) {
			return false;
		}
		if (str != null && strs == null) {
			return false;
		}
		for (String string : strs) {
			if (str.contains(string)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 判断字符串是否在字符数组中, 忽略大小写
	 * 
	 * @param str
	 *            字符串
	 * @param strs
	 *            字符数组
	 * @return true:在，false:不在
	 */
	public static boolean containsStrIgnoreCase(String str, String[] strs) {
		if (str == null && strs == null) {
			return true;
		}
		if (str == null && strs != null) {
			return false;
		}
		if (str != null && strs == null) {
			return false;
		}
		for (String string : strs) {
			if (str.toUpperCase().contains(string.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将指定字符串中的占位符变量替换成具体的值
	 * 
	 * 比如目标字符串为 SELECT * FROM data_user WHERE group_id = ${group_id} AND
	 * del_flag = ${del_flag} 替换后为 SELECT * FROM data_user WHERE group_id = '1'
	 * AND del_flag = '0'
	 * 
	 * @param sourceString
	 *            替换前的字符串
	 * @param placeholderMap
	 *            里面的 key 为 占位符 ${group_id} 中的 group_id, value 为具体的值
	 * 
	 * @return 替换后的字符串
	 */
	public static String replaceVariable(String sourceString, Map<String, String> placeholderMap) {
		if (StringUtils.isNotBlank(sourceString) && sourceString.contains("${") && sourceString.contains("}")) {
			Pattern pattern = Pattern.compile(REG.VARIABLE);
			Matcher matcher = pattern.matcher(sourceString);
			while (matcher.find()) {
				String matcherStr = matcher.group();
				String variable = matcherStr.replaceAll("\\$\\{", "").replaceAll("\\}", "");
				String variableValue = placeholderMap.get(variable);
				if (StringUtils.isNotBlank(variableValue)) {
					sourceString = sourceString.replace("${" + variable + "}", variableValue);
				}
			}
		}
		return sourceString;
	}

	/**
	 * 在字符串中截取指定边界的字符串
	 * 
	 * 比如 targetString 为 "{sdfsf10252sdf10252sfslf23y4nsfhispw3}" leftBorder 为
	 * "10252" rightBorder 为 "f" index 为 1 的时候返回字符串 "sd", 为 2 的时候返回字符串 "s", 为 3
	 * 的时候仍然返回字符串 "s"
	 * 
	 * @param targetString
	 *            目标字符串
	 * @param leftBorder
	 *            目标字符串左边界
	 * @param rightBorder
	 *            目标字符串右边界
	 * @param index
	 *            左边界出现的次数, 如果为 2, 那么截取出现第二次满足时候的字符串
	 * 
	 * 
	 * @return 截取的字符串
	 */
	public static String getStringByFilter(String targetString, String leftBorder, String rightBorder, Integer index) {
		int startpos = targetString.indexOf(leftBorder);
		if (startpos > 0) {
			while (index-- > 0) {
				if (startpos > 0) {
					targetString = targetString.substring(startpos + leftBorder.length());
				}
				startpos = targetString.indexOf(leftBorder);
			}
			int endpos = targetString.indexOf(rightBorder);
			if (endpos > 0) {
				targetString = targetString.substring(0, endpos);
			} else {
				targetString = EMPTY;
			}
		} else {
			targetString = EMPTY;
		}
		return targetString;
	}

	/**
	 * 从字符串中提取占位符变量, 占位符变量 : ${group_id}
	 * 
	 * @param targetString
	 *            像 {@code SELECT * FROM data_user WHERE group_id = ${group_id}
	 *            AND del_flag = ${del_flag}}
	 * @return {@code List<String> } 里面有 group_id, del_flag
	 * 
	 */
	public static List<String> extractVariable(String targetString) {
		List<String> variableList = new ArrayList<>();
		if (StringUtils.isNotBlank(targetString) && targetString.contains("${") && targetString.contains("}")) {
			Pattern pattern = Pattern.compile(REG.VARIABLE);
			Matcher matcher = pattern.matcher(targetString);
			while (matcher.find()) {
				String matcherStr = matcher.group();
				String variable = matcherStr.replaceAll("\\$\\{", EMPTY).replaceAll("\\}", EMPTY);
				variableList.add(variable);
			}
		}
		return variableList;
	}

	/**
	 * DNA分析 拼字检查 语音辨识 抄袭侦测
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * @return StringDiff
	 */
	public static StringDiff levenshtein(String str1, String str2) {
		// 计算两个字符串的长度。
		int len1 = str1.length();
		int len2 = str2.length();
		// 建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取三个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
			}
		}
		// 取数组右下角的值，同样不同位置代表不同字符串的比较
		int diffStep = dif[len1][len2];
		// 计算相似度
		float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
		return new StringDiff(str1, str2, diffStep, similarity);
	}

	/**
	 * 字符串比较
	 * 
	 * @author chen_k
	 *
	 */
	public static class StringDiff {
		/**
		 * 字符串1
		 */
		private String str1;
		/**
		 * 字符串2
		 */
		private String str2;
		/**
		 * 差异步骤
		 */
		private int diffStep;
		/**
		 * 相似度
		 */
		private float similarity;
		
		public String getStr1() {
			return str1;
		}

		public void setStr1(String str1) {
			this.str1 = str1;
		}

		public String getStr2() {
			return str2;
		}

		public void setStr2(String str2) {
			this.str2 = str2;
		}

		public int getDiffStep() {
			return diffStep;
		}

		public void setDiffStep(int diffStep) {
			this.diffStep = diffStep;
		}

		public float getSimilarity() {
			return similarity;
		}

		public void setSimilarity(float similarity) {
			this.similarity = similarity;
		}

		public StringDiff(String str1, String str2, int diffStep, float similarity) {
			super();
			this.str1 = str1;
			this.str2 = str2;
			this.diffStep = diffStep;
			this.similarity = similarity;
		}

		public StringDiff() {
			super();
		}

	}

	// 得到最小值
	private static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}

}
