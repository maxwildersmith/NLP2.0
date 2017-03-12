package nlp;

/**
 * Write a description of class ApiHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Thesaurus 
{
    private static final String THE_KEY = "?key=5b2cb908-bbbb-4b36-a189-25191a72f993";
    private static int termNum =0;
    private String url;
    private ArrayList<String> desc, definitions;
    private String term;
    
    public Thesaurus(String term) throws SAXException, IOException, Exception  {
    	//System.out.println("Trying Thesaurus...");
        termNum++;
        url = "http://www.dictionaryapi.com/api/v1/references/thesaurus/xml/"+term.trim().toLowerCase()+THE_KEY;
        desc = new ArrayList<String>();
        definitions= new ArrayList<String>();
        this.term = term;
        //System.out.println("Getting Info...");
        getInfo();
    }

    public String getName(){
    	return term;
    }
    
    public ArrayList<String> getPartOfSpeech(){
    	return desc;
    }
    public ArrayList<String> getDefinitions(){
    	return definitions;
    }
    
    private void getInfo() throws Exception, SAXException, IOException{
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(new URL(url).openConnection().getInputStream());
            doc.getDocumentElement().normalize();
            
            desc = getTagData(doc, "fl");
            definitions = getTagData(doc, "mc"); 
    }
    
    private ArrayList<String> getTagData(Document doc, String tag){
    	ArrayList<String> out = new ArrayList<String>();
    	NodeList items = doc.getElementsByTagName(tag);
        
        if(items.getLength()==0)
        	return null;
        for(int i=0;i<items.getLength();i++)
        	out.add(items.item(i).getTextContent());
    	return out;
    }
    
    /*
    for (int i = 0; i < items.getLength(); i++)
    {
        Node n = items.item(i);

        if (n.getNodeType() != Node.ELEMENT_NODE)
            continue;

        Element e = (Element) n;
        NodeList titleList = e.getElementsByTagName("dt");
        for (int j = 0; j < titleList.getLength(); j++){
            Node dt = titleList.item(j);
            if (dt.getNodeType() != Node.ELEMENT_NODE)
                continue;                   
            Element titleElem = (Element) titleList.item(j);
            Node titleNode = titleElem.getChildNodes().item(0);
            System.out.println(titleNode.getNodeValue());
        }
        
    }*/
}