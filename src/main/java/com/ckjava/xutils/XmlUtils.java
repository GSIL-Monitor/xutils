package com.ckjava.xutils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUtils implements Constants {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

	/**
	 * 将 xml 字符串转成java对象
	 * 
	 * @param <T>
	 *            任意类对象
	 * @param xml
	 *            xml字符串
	 * @param clazz
	 *            java对象的类对象
	 * @return java对象
	 * @throws JAXBException
	 *             异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2Obj(String xml, Class<T> clazz) throws JAXBException {
		JAXBContext requestContext = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = requestContext.createUnmarshaller();
		StringReader sr = new StringReader(xml);
		return (T) unmarshaller.unmarshal(sr);
	}

	/**
	 * 将一个 org.w3c.dom.Node 节点对象转成 xml 字符串
	 * 
	 * @param node
	 *            org.w3c.dom.Node 节点
	 * @return xml 字符串
	 * @throws Exception
	 *             可能是 TransformerFactoryConfigurationError
	 *             TransformerConfigurationException TransformerException
	 */
	public static String convertNode2XmlString(Node node) throws Exception {
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(new DOMSource(node), result);
		return writer.toString();
	}

	/**
	 * 将指定的对象转成 xml 字符串
	 * 
	 * @param obj
	 *            指定的对象,必须带有 JAXB 相关注解
	 * @param marshallerProperties
	 *            {@link javax.xml.bind.Marshaller } 对象的相关静态属性,比如: JAXB_ENCODING
	 * @return xml 字符串
	 * @throws JAXBException
	 *             异常
	 */
	public static String obj2Xml(Object obj, Map<String, Object> marshallerProperties) throws JAXBException {
		JAXBContext responseContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = responseContext.createMarshaller();

		if (marshallerProperties != null && !marshallerProperties.isEmpty()) {
			for (Iterator<Entry<String, Object>> it = marshallerProperties.entrySet().iterator(); it.hasNext();) {
				Entry<String, Object> entry = it.next();
				marshaller.setProperty(entry.getKey(), entry.getValue());
			}
		}

		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		return sw.toString();
	}

	/**
	 * 将 java 对象转成的 xml 追加到指定的 org.w3c.dom.Node 节点上
	 * 
	 * @param obj
	 *            java 对象
	 * @param node
	 *            org.w3c.dom.Node 节点
	 * @return 新的 org.w3c.dom.Node 节点
	 * 
	 * @throws JAXBException
	 *             异常
	 */
	public static Node appendObj2Node(Object obj, Node node) throws JAXBException {
		JAXBContext responseContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = responseContext.createMarshaller();
		marshaller.marshal(obj, node);
		return node;
	}
	
	/**
	 * 将 xml 字符串中所有的对象以 key=value 的字符串形式存入 List 对象中
	 * 
	 * @param xmlString 原始的 json 字符串
	 * @return {@code List<String> } 集合中是以 key=value 的字符串
	 * @throws Exception 异常对象
	 */
	public static List<String> resolveXmlString(String xmlString) throws Exception {
		// 首先创建临时的xml文件
		Long time = System.currentTimeMillis();
		File xmlFile = new File(String.valueOf(time).concat(".xml"));
		FileUtils.writeStringToFile(xmlFile, xmlString, true, CHARSET.UTF8);
		// 1.实例化SAXParserFactory对象
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.创建解析器
        SAXParser parser = factory.newSAXParser();
        // 3.获取需要解析的文档，生成解析器,最后解析文档
        List<String> dataList = new ArrayList<>();
        SaxHandler dh = new SaxHandler(dataList);
        parser.parse(xmlFile, dh);
        // 最后将临时xml文件删除
        boolean deleteFlag = xmlFile.delete();
        logger.info("删除临时xml文件, deleteFlag = {}", deleteFlag);
        
        return dataList;
	}
	
	public static class SaxHandler extends DefaultHandler {
		
		private List<String> dataList;
		private String element;
		private String elementContent;
		 
	    public SaxHandler(List<String> dataList) {
			super();
			this.dataList = dataList;
		}

		@Override
		public void error(SAXParseException e) throws SAXException {
			super.error(e);
		}

		@Override
		public void fatalError(SAXParseException e) throws SAXException {
			super.fatalError(e);
		}

		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
			System.out.println(new String(ch, start, length));
			super.ignorableWhitespace(ch, start, length);
		}

		@Override
		public void processingInstruction(String target, String data) throws SAXException {
			super.processingInstruction(target, data);
		}

		@Override
		public void skippedEntity(String name) throws SAXException {
			super.skippedEntity(name);
		}

		@Override
		public void warning(SAXParseException e) throws SAXException {
			super.warning(e);
		}

		/* 此方法有三个参数
	       arg0是传回来的字符数组，其包含元素内容
	       arg1和arg2分别是数组的开始位置和结束位置 */
	    @Override
	    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
	    	elementContent = new String(arg0, arg1, arg2);
	    	System.out.print(elementContent);
	    	
	    	if (StringUtils.isNotBlank(elementContent)) {
	    		StringBuilder content = new StringBuilder();
	    		content.append("<").append(element).append(">");
	    		content.append(elementContent);
	    		content.append("</").append(element).append(">");
	    		dataList.add(element + "=" + content.toString());
	    	}
	    	
	        super.characters(arg0, arg1, arg2);
	    }
	 
	    @Override
	    public void endDocument() throws SAXException {
	    	System.out.println("\n结束解析");
	        super.endDocument();
	    }
	 
	    /* arg0是名称空间
	       arg1是包含名称空间的标签，如果没有名称空间，则为空
	       arg2是不包含名称空间的标签 */
	    @Override
	    public void endElement(String arg0, String arg1, String arg2) throws SAXException {
	    	System.out.print("</");
	        System.out.print(arg2);
	        System.out.print(">");
	        super.endElement(arg0, arg1, arg2);
	    }
	 
	    @Override
	    public void startDocument() throws SAXException {
	    	System.out.println("开始解析");
	        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	        System.out.println(s);
	        super.startDocument();
	    }
	 
	    /*arg0是名称空间
	      arg1是包含名称空间的标签，如果没有名称空间，则为空
	      arg2是不包含名称空间的标签
	      arg3很明显是属性的集合 */
	    @Override
	    public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {

	    	System.out.print("<");
	        System.out.print(arg2);
	        element = arg2;
	         
	        if (arg3 != null) {
	            for (int i = 0; i < arg3.getLength(); i++) {
	                System.out.print(" " + arg3.getQName(i) + "=\"" + arg3.getValue(i) + "\"");
	                dataList.add(arg3.getQName(i) + "=" + arg3.getQName(i) + "=\"" + arg3.getValue(i) + "\"");
	            }
	        }
	        System.out.print(">");
	        super.startElement(arg0, arg1, arg2, arg3);
	    }
	}
	
}
