package ua.nure.it;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

public class XSLT {

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			usage();
			return;
		} 
		
		String xsl = args[0];
		String[] files = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			files[i - 1] = args[i];
		}

		System.out.println("XSL: " + xsl);
		for (int i = 0; i < files.length; i++) {
			System.out.println("XML: " + files[i]);
			String html = files[i] + ".html";
			transform(new FileInputStream(xsl), new FileInputStream(files[i]), new FileOutputStream(html));
			System.out.println("HTML: " + html + "\nOk.");
		}
		System.out.println("Done.");
	}

	private static void usage() {
		System.out.println("Usage:\njava XSLT xslt_file xml_file [xml_file ...]");
	}

	public static void transform(InputStream xslIn, InputStream xmlIn, OutputStream htmlOut) throws Exception {
		Source xslSrc = new StreamSource(xslIn);
		Source xmlSrc = new SAXSource(new InputSource(xmlIn));
		Result htmlRes = new StreamResult(htmlOut);

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xslSrc);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(xmlSrc, htmlRes);
	}
}
