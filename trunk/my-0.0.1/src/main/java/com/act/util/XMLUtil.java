
package com.act.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * 
 * @author act
 */
public class XMLUtil {

	private final static Hashtable CACHE = new Hashtable();

	private final static String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 缺省按XML指明的encoding读
	 * 
	 * @param file
	 * @return
	 */
	public static Document getDocument(String file) {
		return getDocument(file, DEFAULT_ENCODING, true);
	}

	public static Document getDocument(String file, boolean cache) {
		return getDocument(file, DEFAULT_ENCODING, cache);
	}

	public static Document getDocument(String file, String encoding) {
		return getDocument(file, encoding, true);
	}

	public static boolean isModified(String file) {
		CachedDocument doc = null;
		doc = (CachedDocument) CACHE.get(file);
		File f = new File(file);
		return !(doc != null && f != null && doc.lastModified == f.lastModified());
	}

	public static void unload(String file) {
		CACHE.remove(file);
	}

	public static Document getDocument(String file, String encoding, boolean cache) {
		CachedDocument doc = null;
		doc = (CachedDocument) CACHE.get(file);
		File f = new File(file);
		if (doc != null && f != null && doc.lastModified == f.lastModified()) {
			return doc.document;
		}
		try {
			doc = new XMLUtil().creat();
			doc.filepath = file;
			doc.document = parse(f.toURL(), encoding);
			doc.lastModified = f.lastModified();
			if (cache) {
				CACHE.put(file, doc);
			}
		} catch (Exception e) {
			throw new java.lang.IllegalArgumentException(e.getMessage());
		}
		return doc.document;
	}
	
	public static  Document creatDoc() {
		Document doc = new DOMDocument();
		doc.setXMLEncoding(DEFAULT_ENCODING);
		Element docel = new DOMElement("constants");
		doc.setRootElement(docel);
		return doc;
	}
	
	private CachedDocument creat() {
		return new CachedDocument();
	}

	public class CachedDocument {

		String filepath;

		Document document;

		long lastModified;
	}

	public static Document parseText(String s) {
		try {
			return DocumentHelper.parseText(s);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Document parse(URL url, String encoding) throws Exception {
		SAXReader reader = null;
		try {
			reader = new SAXReader("org.apache.xerces.parsers.SAXParser");
		} catch (Exception ex) {
			reader = new SAXReader();
		}
		// System.out.println("org.xml.sax.driver:"+System.getProperty("org.xml.sax.driver"));
		// System.out.println("XMLUtil
		// SAXParser:"+reader.getXMLReader().getClass());
		Document document = null;
		if (encoding == null) {
			document = reader.read(url);
		} else {
			document = reader.read(new InputStreamReader(url.openStream(), encoding));
		}
		return document;
	}

	public XMLUtil() {
	}

	public static void write(File file, Document doc) {
		String encoding = "UTF-8";
		write(file, doc, encoding);
	}

	public static void write(File file, Document doc, String encoding) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			OutputFormat outformat = OutputFormat.createPrettyPrint();
			outformat.setEncoding(encoding);
			XMLWriter writer = new XMLWriter(out, outformat);
			writer.write(doc);
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			throw new java.lang.IllegalStateException("Can't write file:" + file.getAbsoluteFile() + "\n caused by:" + ex.getMessage());
		}
	}

	public static String getEncoding(String f) {
		String encoding = "UTF-8";
		File file = new File(f);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String xml = reader.readLine();
			reader.close();
			xml = xml.toLowerCase();
			int pos = xml.indexOf("encoding");
			if (pos < 0) {
				return encoding;
			}
			xml = xml.substring(pos + "encoding".length());
			pos = xml.indexOf("\"");
			xml = xml.substring(pos + 1);
			pos = xml.indexOf("\"");
			xml = xml.substring(0, pos).trim();
			return xml.toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encoding;
	}
	
	/**
	 * 读取xml为string
	 * @param doc
	 * @return
	 */
	public static String toXML(Document doc) {
		return toXML(doc, DEFAULT_ENCODING);
	}
	public static String toXML(Document doc,String encoding) {
		OutputFormat outputFormat = new OutputFormat("", true);
		outputFormat.setEncoding(encoding);
		StringWriter out = new StringWriter();
		XMLWriter writer = new XMLWriter(out, outputFormat);
		try {
			writer.write(doc);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}