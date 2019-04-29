package com.ashish.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class TestXpath {

    private static final String XPATH_EXPRESSION = "//fltdMessage[@airline=\"SWA\"]";

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/a0m043x/Downloads/testData.xml")); //Change this accordingly.

        // Create XPath object
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expression = xpath.compile(XPATH_EXPRESSION);
        NodeList nList = (NodeList) expression.evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println(node.getAttributes().getNamedItem("airline"));
        }

    }
}
