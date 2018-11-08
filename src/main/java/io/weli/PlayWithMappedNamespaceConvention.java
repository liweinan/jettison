package io.weli;

import org.codehaus.jettison.AbstractXMLStreamWriter;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import java.io.StringWriter;

public class PlayWithMappedNamespaceConvention {
   public static void main(String[] args) throws Exception {
      StringWriter strWriter = new StringWriter();
      MappedNamespaceConvention con = new MappedNamespaceConvention();
      AbstractXMLStreamWriter w = new MappedXMLStreamWriter(con, strWriter);

      w.writeStartDocument();
      w.writeStartElement("root");
      w.writeStartElement("child");
      w.writeEndElement();
      w.writeEndElement();
      w.writeEndDocument();

      w.close();
      strWriter.close();
      System.out.println(strWriter.toString());
   }
}
