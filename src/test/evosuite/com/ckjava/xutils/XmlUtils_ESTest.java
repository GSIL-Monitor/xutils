/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 15 10:37:47 GMT 2018
 */

package com.ckjava.xutils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ckjava.xutils.XmlUtils;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javax.imageio.metadata.IIOMetadataNode;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.UnmarshalException;
import javax.xml.transform.TransformerException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.lang.MockException;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.LocatorImpl;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class XmlUtils_ESTest extends XmlUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      Attributes attributes0 = mock(Attributes.class, new ViolatedAssumptionAnswer());
      doReturn((-2066)).when(attributes0).getLength();
      xmlUtils_SaxHandler0.startElement("<", "u", ".xml", attributes0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.endElement("|wh", (String) null, (String) null);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.endElement("<", "<", ".xml");
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      char[] charArray0 = new char[1];
      // Undeclared exception!
      try { 
        xmlUtils_SaxHandler0.characters(charArray0, 4951, 2763);
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.processingInstruction("application/x-msdownload", "");
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      char[] charArray0 = new char[6];
      // Undeclared exception!
      try { 
        xmlUtils_SaxHandler0.ignorableWhitespace(charArray0, 0, (-179));
        fail("Expecting exception: StringIndexOutOfBoundsException");
      
      } catch(StringIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Class<String> class0 = String.class;
      // Undeclared exception!
      try { 
        XmlUtils.xml2Obj((String) null, class0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.io.StringReader", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      FileSystemHandling.shouldAllThrowIOExceptions();
      try { 
        XmlUtils.resolveXmlString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.FileUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      // Undeclared exception!
      try { 
        XmlUtils.obj2Xml((Object) null, (Map<String, Object>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.XmlUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      IIOMetadataNode iIOMetadataNode0 = new IIOMetadataNode("");
      IIOMetadataNode iIOMetadataNode1 = new IIOMetadataNode();
      Node node0 = iIOMetadataNode0.appendChild(iIOMetadataNode1);
      Node node1 = iIOMetadataNode0.replaceChild(node0, node0);
      try { 
        XmlUtils.convertNode2XmlString(node1);
        fail("Expecting exception: TransformerException");
      
      } catch(TransformerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Node node0 = mock(Node.class, new ViolatedAssumptionAnswer());
      doReturn((short)0).when(node0).getNodeType();
      doReturn((Document) null).when(node0).getOwnerDocument();
      try { 
        XmlUtils.appendObj2Node(">", node0);
        fail("Expecting exception: MarshalException");
      
      } catch(MarshalException e) {
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Node node0 = mock(Node.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        XmlUtils.appendObj2Node((Object) null, node0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.XmlUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      // Undeclared exception!
      try { 
        XmlUtils.appendObj2Node("INSENSITIVE", (Node) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // node parameter must not be null
         //
         verifyException("javax.xml.bind.helpers.AbstractMarshallerImpl", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      AttributesImpl attributesImpl0 = new AttributesImpl();
      xmlUtils_SaxHandler0.startElement("<", ".xml", ".xml", attributesImpl0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      Attributes attributes0 = mock(Attributes.class, new ViolatedAssumptionAnswer());
      doReturn(1594).when(attributes0).getLength();
      doReturn((String) null, (String) null, (String) null).when(attributes0).getQName(anyInt());
      doReturn((String) null, (String) null).when(attributes0).getValue(anyInt());
      // Undeclared exception!
      try { 
        xmlUtils_SaxHandler0.startElement("<", "u", ".xml", attributes0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.XmlUtils$SaxHandler", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.startElement("directory must not be null", "directory must not be null", "directory must not be null", (Attributes) null);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      char[] charArray0 = new char[8];
      // Undeclared exception!
      try { 
        xmlUtils_SaxHandler0.characters(charArray0, 2, 2);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.XmlUtils$SaxHandler", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      char[] charArray0 = new char[5];
      xmlUtils_SaxHandler0.characters(charArray0, 0, 0);
      assertArrayEquals(new char[] {'\u0000', '\u0000', '\u0000', '\u0000', '\u0000'}, charArray0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.endDocument();
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.skippedEntity("JIJztz?4");
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      LocatorImpl locatorImpl0 = new LocatorImpl();
      MockException mockException0 = new MockException();
      SAXParseException sAXParseException0 = new SAXParseException("", locatorImpl0, mockException0);
      try { 
        xmlUtils_SaxHandler0.fatalError(sAXParseException0);
        fail("Expecting exception: SAXParseException");
      
      } catch(SAXParseException e) {
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      SAXParseException sAXParseException0 = mock(SAXParseException.class, new ViolatedAssumptionAnswer());
      xmlUtils_SaxHandler0.warning(sAXParseException0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      SAXParseException sAXParseException0 = new SAXParseException("directory must not be null", (String) null, "Kg|y-({>$vej", 5, 352);
      xmlUtils_SaxHandler0.error(sAXParseException0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      xmlUtils_SaxHandler0.startDocument();
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      XmlUtils.SaxHandler xmlUtils_SaxHandler0 = new XmlUtils.SaxHandler((List<String>) null);
      char[] charArray0 = new char[5];
      xmlUtils_SaxHandler0.ignorableWhitespace(charArray0, 0, 0);
      assertArrayEquals(new char[] {'\u0000', '\u0000', '\u0000', '\u0000', '\u0000'}, charArray0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Object object0 = new Object();
      Node node0 = mock(Node.class, new ViolatedAssumptionAnswer());
      doReturn((short)0).when(node0).getNodeType();
      doReturn((Document) null).when(node0).getOwnerDocument();
      try { 
        XmlUtils.appendObj2Node(object0, node0);
        fail("Expecting exception: JAXBException");
      
      } catch(JAXBException e) {
      }
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      try { 
        XmlUtils.resolveXmlString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        fail("Expecting exception: FileNotFoundException");
      
      } catch(FileNotFoundException e) {
         //
         // D:\\workspace\\git-workspace\\play\\xutils\\1392409281320.xml (\u7CFB\u7EDF\u627E\u4E0D\u5230\u6307\u5B9A\u7684\u6587\u4EF6\u3002)
         //
         verifyException("java.io.FileInputStream", e);
      }
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      String string0 = XmlUtils.convertNode2XmlString((Node) null);
      assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", string0);
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      XmlUtils xmlUtils0 = new XmlUtils();
      try { 
        XmlUtils.obj2Xml(xmlUtils0, (Map<String, Object>) null);
        fail("Expecting exception: MarshalException");
      
      } catch(MarshalException e) {
      }
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      Class<Object> class0 = Object.class;
      try { 
        XmlUtils.xml2Obj("Ny", class0);
        fail("Expecting exception: UnmarshalException");
      
      } catch(UnmarshalException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("javax.xml.bind.helpers.AbstractUnmarshallerImpl", e);
      }
  }
}
