package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.ckjava.xutils.XmlUtils;

public class TestXmlUtils extends XmlUtils {

	@Test
	public void resolveXmlString() {
		try {
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><RequestResponse xmlns=\"http://tempuri.org/\"><RequestResult><Response><Header ShouldRecordPerformanceTime=\"false\" Timestamp=\"2018-02-08 16:05:43:87107\" ResultCode=\"Fail\" ResultNo=\"90000001\" ResultMsg=\"Error occured while deserializing request. Service log has details.\" RequestBodySize=\"0\" SerializeMode=\"Xml\" RouteStep=\"7\" RequestID=\"9f010a96-af13-4a9b-93d6-a697781cd84d\" /></Response></RequestResult></RequestResponse></soap:Body></soap:Envelope>";
			List<String> dataList = resolveXmlString(xmlString);
			assertTrue(dataList.contains("ResultNo=ResultNo=\"90000001\""));
			
			dataList.clear();
			xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <books>    <book id=\"001\">       <title>Harry Potter</title>       <author>J K. Rowling</author>    </book>    <book id=\"002\">       <title>Learning XML</title>       <author>Erik T. Ray</author>    </book> </books> ";
			dataList = resolveXmlString(xmlString);
			assertTrue(dataList.contains("id=id=\"001\""));
			assertTrue(dataList.contains("id=id=\"002\""));
			assertTrue(dataList.contains("title=<title>Harry Potter</title>"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
