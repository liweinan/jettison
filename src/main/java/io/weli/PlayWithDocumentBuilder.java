package io.weli;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedXMLOutputFactory;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class PlayWithDocumentBuilder {
   public static void main(String[] args) throws Exception {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setNamespaceAware(true);
      factory.setIgnoringElementContentWhitespace(false);
      DocumentBuilder builder = factory.newDocumentBuilder();

      ////////////////////////////////////////////////////////////////////////
      String xmlStr = "<kermit>the frog</kermit>";
      Element elem = builder.parse(new InputSource(new StringReader(xmlStr))).getDocumentElement();

      ////////////////////////////////////////////////////////////////////////
      Configuration conf = new Configuration();
      conf.setIgnoreNamespaces(false);

      Map xnsToJns = new HashMap();
      xnsToJns.put("http://somens", "somens");
      conf.setXmlToJsonNamespaces(xnsToJns);

      ////////////////////////////////////////////////////////////////////////
      /// serialize
      DOMSource source = new DOMSource(elem);

      XMLInputFactory readerFactory = XMLInputFactory.newInstance();

      // need `wstl` dependency
      // 	<dependency>
      //		<groupId>woodstox</groupId>
      //		<artifactId>wstx-asl</artifactId>
      //		<version>3.2.2</version>
      //		<!--<scope>test</scope>-->
      //	</dependency>
      XMLEventReader eventReader = readerFactory
            .createXMLEventReader(readerFactory
                  .createXMLStreamReader(source));

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      XMLEventWriter eventWriter = new MappedXMLOutputFactory(conf)
            .createXMLEventWriter(System.out);

      eventWriter.add(eventReader);
      eventWriter.close();
   }
}
