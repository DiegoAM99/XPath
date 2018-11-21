/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpath;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Diego Álvarez
 */
public class XPath {
    public int EjecutaXPath() {

        try {

            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(new File("LibrosXML.xml"));
            
            //Crea el objeto XPath
            javax.xml.xpath.XPath xpath = XPathFactory.newInstance().newXPath();

            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile("/Libros/*/Autor");

            //Ejecut la consulta indicando que se ejecute sobre el DOM y que devolverá
            //el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;

            //Ahora recorre la lista para sacar los resultados
            String salida = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                salida = salida + "\n"
                        + nodeList.item(i).getChildNodes().item(0).getNodeValue();
            }
            System.out.println(salida);
            return 0;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return -1;
        }
    }
}
