package com.ckjava.xutils;

public interface Constants {

	public class CHARSET {
		public static final String UTF8 = "UTF-8";
		public static final String GB2312 = "GB2312";
		public static final String ISO88591 = "ISO-8859-1";
		public static final String GBK = "GBK";
	}
	
	public class TIMEFORMAT {
		public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
		public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
		public static final String DATE = "yyyy-MM-dd";
		public static final String TIME = "HH:mm:ss";
		public static final String YEAR = "yyyy";
		public static final String MONTH = "MM";
		public static final String DAY = "dd";
		public static final String WEEKDAY = "E";
		public static final String[] PATTERNS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm", "yyyy-MM",
				"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
				"yyyy.MM.dd HH:mm", "yyyy.MM" };
	}
	
	public class SPLITER {
		public static final String EQUAL = "=";
		public static final String AT = "@";
		public static final String AND = "&";
		public static final String COMMA = ",";
		public static final String COLON = ":";
		public static final String SEMICOLON = ";";
		public static final String VERTICAL = "|";
		public static final String ATVERTICAL = "\\|@\\|";
		public static final String ANDVERTICAL = "\\|&\\|";
	}
	
	public class OSTYPE {
		public static final String WINDOWS = "windows";
		public static final String MAC = "mac";
		public static final String LINUX = "linux";	
	}
	
	public class CONCAT {
		public static final String ATVERTICAL = "|@|";
		public static final String ANDVERTICAL = "|&|";
		public static final String AT = "@";
		public static final String AND = "&";
		public static final String EQUAL = "=";	
	}
	
	public class REG {
		public static final String VARIABLE = "(\\$\\{[^\\}.]*\\})";
	}
	
	public class STATUS {
		public static final String ON = "on";
		public static final String OFF = "off";
		public static final String YES = "yes";
		public static final String SUCCESS = "success";
		public static final String FAIL = "fail";
	}
	
	public class HTTPMETHOD {
		public static final String GET = "get";
		public static final String POST = "post";
		public static final String PUT = "put";
		public static final String DELETE = "delete";
		public static final String HEAD = "head";
		public static final String TRACE = "trace";
		public static final String CONNECT = "connect";
	}
	
	public class DBOPERATION {
		public static final String INSERT = "insert";
		public static final String UPDATE = "update";
		public static final String DELETE = "delete";
	}
	
	public class QUERYTYPE {
		public static final String DB = "db";
		public static final String CLOG = "clog";
		public static final String API = "api";
	}
	
	public class DBTYPE {
		public static final String TITAN = "titan";
		public static final String MYSQL = "mysql";
		public static final String ORACLE = "oracle";
		public static final String MSSQL = "mssql";
	}
	
}
