[![Build Status](https://travis-ci.org/dzoverflow/xutils.svg?branch=master-github)](https://travis-ci.org/dzoverflow/xutils) [![codecov](https://codecov.io/gh/dzoverflow/xutils/branch/master-github/graph/badge.svg)](https://codecov.io/gh/dzoverflow/xutils)

## 如何使用

已经发布到 [Maven Central Repository](https://mvnrepository.com/artifact/com.ckjava/xutils)

```xml
<dependency>
    <groupId>com.ckjava</groupId>
    <artifactId>xutils</artifactId>
    <version>1.0.0</version>
</dependency>
```

最新版本是 `1.0.0`

## 方法列表

#### FileUtils

- joinPath : 将文件目录字符串拼接起来

#### IOUtils

- getString : 从 {@code java.io.InputStream } 中以特定字符集读取出字符串

#### ArrayUtils

- getValue : 根据下标从数组中获取数据
- getSize : 获取数组的长度
- join : 将数组以分隔符 separator 为分界合并起来
- merge : 将多个数组合并成一个后返回

#### ObjectUtils 

- isEmptyObject : 对象的所有字段都为空才返回true
- isNotEmptyObject : 对象的所有字段都不为空才返回 true
- getObjectString : 将一个对象中不为空的字段拼接成 key1=value1&key2=value2
- fillMapWithString : 将 key1=value1&key2=value2的字符串存入Map中
- objectToBytes : 将Java对象Object转换成Byte字节数组
- bytesToObject : 将 Byte字节数组 转成 Java 对象

#### CommandUtils
- execTask : 执行命令

#### StringUtils

- getStringByFilter : 在字符串中截取指定边界的字符串
- extractVariable : 从字符串中提取占位符变量, 占位符变量 : ${group_id}
- replaceVariable : 将指定字符串中的占位符变量(${group_id})替换成具体的值

#### XmlUtils

- resolveXmlString: 将 xml 字符串中所有的对象以 key=value 的字符串形式存入 List 对象中

#### JsonUtils

- resolveJsonString : 将 json 字符串中所有的对象以 key=value 的字符串形式存入 List 对象中
