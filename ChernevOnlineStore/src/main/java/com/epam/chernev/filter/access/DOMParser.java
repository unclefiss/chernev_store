package com.epam.chernev.filter.access;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DOMParser {

    private DOMParser() {

    }

    private static final Logger log = Logger.getLogger(DOMParser.class.getName());

    public static Map<String, List<String>> getConstraints(InputStream inputStream) {
        Map<String, List<String>> constraints = new HashMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            Node root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            for (int i = 1; i < nodes.getLength(); i++) {
                Node constraint = nodes.item(i);
                if (constraint.getNodeType() != Node.TEXT_NODE) {
                    parseConstraint(constraints, constraint);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.warning(e.getMessage());
        }
        return constraints;
    }

    private static void parseConstraint(Map<String, List<String>> map, Node constraint) {
        String urlPattern = "";
        List<String> roles = new ArrayList<>();
        NodeList attributes = constraint.getChildNodes();
        for (int j = 0; j < attributes.getLength(); j++) {
            Node attribute = attributes.item(j);
            if (attribute.getNodeName().equalsIgnoreCase("url-pattern")) {
                urlPattern = attribute.getFirstChild().getTextContent().replace("*",".+");
            }
            if (attribute.getNodeName().equalsIgnoreCase("role")) {
                roles.add(attribute.getFirstChild().getTextContent());
            }
        }
        if (!urlPattern.isEmpty()) {
            map.put(urlPattern, roles);
        }
    }
}

