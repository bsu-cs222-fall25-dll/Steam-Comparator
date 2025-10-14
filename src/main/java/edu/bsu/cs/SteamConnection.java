package edu.bsu.cs;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
public class SteamConnection {

    public static Object connectToSteam() throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        String xmlString = "<profile><steamID64>76561198799220336</steamID64><steamID>MySteamProfile</steamID></profile>"; // Example XML
        InputSource is = new InputSource(new StringReader(xmlString));
        Document doc = null;
        try {
            doc = builder.parse(is);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        doc.getDocumentElement().normalize(); // Normalize the XML structure

        NodeList steamID64List = doc.getElementsByTagName("steamID64");
        String steamID = "";
        if (steamID64List.getLength() > 0) {
            Element steamID64Element = (Element) steamID64List.item(0);
            steamID = steamID64Element.getTextContent();
        } else {
            System.out.println("Steam ID not found in the XML.");
        }
        return steamID;
    }

}
