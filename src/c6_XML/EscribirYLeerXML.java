package c6_XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscribirYLeerXML {

	public static void main(String[] args) {
        String rutaArchivo = "maraton.xml";

        try {
        	
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document documento = dBuilder.newDocument();

        Element raiz = documento.createElement("maraton");
        raiz.setAttribute("nombre", "Maratón Internacional Ciudad Galileo");
        raiz.setAttribute("edicion", "15");
        documento.appendChild(raiz); 

        Element infoGeneral = documento.createElement("informacion_general");
        raiz.appendChild(infoGeneral);
        
	        Element fecha = documento.createElement("fecha");
	        fecha.setTextContent("2026-11-15");
	        infoGeneral.appendChild(fecha);
	        
	        Element ciudad = documento.createElement("ciudad");
	        ciudad.setTextContent("Valladolid");
	        infoGeneral.appendChild(ciudad);
	        
	        Element distancia = documento.createElement("distancia");
	        distancia.setAttribute("unidad", "km");        
	        distancia.setTextContent("42.195");
	        infoGeneral.appendChild(distancia);
	    
	    Element participantes = documento.createElement("participantes");
	    raiz.appendChild(participantes);
	    
	    	Element corredor = documento.createElement("corredor");
	    	corredor.setAttribute("dorsal", "101");
	    	corredor.setAttribute("categoria", "Popular");
	    	participantes.appendChild(corredor);
	    	
		    	Element nombre = documento.createElement("nombre");
		    	nombre.setTextContent("Carlos");
		    	corredor.appendChild(nombre);
		    	
		    	Element apellidos = documento.createElement("apellidos");
		    	apellidos.setTextContent("Gómez Ruiz");
		    	corredor.appendChild(apellidos);
	    	
		    	Element edad = documento.createElement("edad");
		    	edad.setTextContent("29");
		    	corredor.appendChild(edad);
		    	
		    	Element pais = documento.createElement("pais");
		    	pais.setTextContent("España");
		    	corredor.appendChild(pais);
		    	
		    	Element tiempoFinal = documento.createElement("tiempo_final");
		    	tiempoFinal.setTextContent("03:14:35");
		    	corredor.appendChild(tiempoFinal);
		    	
		    
	    
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource fuente = new DOMSource(documento);
        StreamResult resultado = new StreamResult(new File(rutaArchivo));
        transformer.transform(fuente, resultado);
        
        System.out.println(">> ¡XML escrito y formateado correctamente!");
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}

}
