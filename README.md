[![Build Status](https://travis-ci.org/dzoverflow/xutils.svg?branch=master)](https://travis-ci.org/dzoverflow/xutils) [![codecov](https://codecov.io/gh/dzoverflow/xutils/branch/master/graph/badge.svg)](https://codecov.io/gh/dzoverflow/xutils)

[中文版](https://github.com/dzoverflow/xutils/blob/master/zh_cn_README.md)

## How to use

available at [Maven Central Repository](https://mvnrepository.com/artifact/com.ckjava/xutils)

```xml
<dependency>
    <groupId>com.ckjava</groupId>
    <artifactId>xutils</artifactId>
    <version>1.0.0</version>
</dependency>
```

lasted version is `1.0.0`

## Method list

#### FileUtils

- joinPath : Stitch the file directory string together

#### IOUtils

- getString : Read a string from a specific character set from java.io.InputStream

#### ArrayUtils

- getValue : Get data from an array based on subscripts
- getSize : Get the length of the array
- join : The array is delimited by a delimiter
- merge : Will be more than one array after the merger back

#### ObjectUtils 

- isEmptyObject : all the object's fields is null then return true.
- isNotEmptyObject : all the object's fields is not null then return true.
- getObjectString : join the object's not null fields into key1=value1&key2=value2.
- fillMapWithString : put the string like "key1=value1&key2=value2" into a Map object.
- objectToBytes : change the Java Object instance into Byte Array.
- bytesToObject : change the Byte Array into Java Object instance.

#### CommandUtils
- execTask : execute task

#### StringUtils

- getStringByFilter : extract string from source string use by filter
- replaceVariable : replace target string which has string like `${variable}` use by a map set

#### XmlUtils

- resolveXmlString: All objects in the xml string will be stored as a "key=value" string in the List object

#### JsonUtils

- resolveJsonString : All objects in the json string will be stored as a "key=value" string in the List object