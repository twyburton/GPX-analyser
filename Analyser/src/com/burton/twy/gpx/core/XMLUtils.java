package com.burton.twy.gpx.core;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

	public static Node getChildNode(String elemName, Node n){
		
		NodeList list = n.getChildNodes();
		
		for(int i = 0 ; i < list.getLength(); i++){
			Node node = list.item(i);
			
			if(node.getNodeName().equals(elemName)){
				return node;
			}
		}
		
		return null;
	}
	
}
