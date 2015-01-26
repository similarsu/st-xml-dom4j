package cn.st.xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import cn.st.xml.dom4j.vistor.MyVistor;

public class Dom4jTest {
	@Test
	public void read() throws Exception{
		SAXReader reader = new SAXReader();  
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("university.xml");  
        Document doc = reader.read(in);  
        Element root = doc.getRootElement();  
        readNode(root, ""); 
	}
	
	@Test
	public void read2() throws Exception{
		SAXReader reader = new SAXReader();  
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("university.xml");  
        Document doc = reader.read(in);  
        doc.accept(new MyVistor());
	}
	
	 public void readNode(Element root, String prefix) {  
	        if (root == null) return;  
	        // 获取属性  
	        List<Attribute> attrs = root.attributes();  
	        if (attrs != null && attrs.size() > 0) {  
	            System.err.print(prefix);  
	            for (Attribute attr : attrs) {  
	                System.err.print(attr.getValue() + " ");  
	            }  
	            System.err.println();  
	        }  
	        // 获取他的子节点  
	        List<Element> childNodes = root.elements();  
	        prefix += "\t";  
	        for (Element e : childNodes) {  
	            readNode(e, prefix);  
	        }  
	    }  
	
	@Test
	public void write(){
		 try {  
	            // 创建一个xml文档  
	            Document doc = DocumentHelper.createDocument();  
	            Element university = doc.addElement("university");  
	            university.addAttribute("name", "tsu");  
	            // 注释  
	            university.addComment("这个是根节点");  
	            Element college = university.addElement("college");  
	            college.addAttribute("name", "cccccc");  
	            college.setText("text");  
	              
	            File file = new File("src/main/resource/dom4j-modify.xml");  
	            if (file.exists()) {  
	                file.delete();  
	            }  
	            file.createNewFile();  
	            XMLWriter out = new XMLWriter(new FileWriter(file));  
	            out.write(doc);  
	            out.flush();  
	            out.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}
}
