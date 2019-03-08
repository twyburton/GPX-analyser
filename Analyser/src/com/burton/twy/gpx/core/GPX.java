package com.burton.twy.gpx.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GPX {

	public GPX(){
		
	}
	
	
	private List<GPXTrack> tracks = new ArrayList<GPXTrack>();
	
	public int getTracksSize(){
		return tracks.size();
	}
	
	public GPXTrack getTrack( int i ){
		return tracks.get(i);
	}
	
	
	/**
	 * Parse a gpx file
	 * @param file
	 * @return true if file parsed correctly
	 */
	public boolean parse(String file){
		tracks.clear();
		
		File f = new File(file);
		
		if( !f.exists() ) return false;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("trk");
			
			// == Trk List ==
			for( int i = 0; i < nList.getLength(); i++ ){
				GPXTrack track = new GPXTrack();
				
				Node node = nList.item(i);
				//System.out.println(node.getNodeName());
				
				NodeList trackNodes = node.getChildNodes();
				
				for( int j = 0; j < trackNodes.getLength(); j++){
					Node trackNode = trackNodes.item(j);
					 
					
					switch (trackNode.getNodeName()){
						case "name":
							track.setName(trackNode.getTextContent());
							break;
						case "type":
							track.setType(Integer.parseInt(trackNode.getTextContent()));
							break;
						case "trkseg":
							
							// == Add Segment to Track ==
							
							GPXTrackSegment seg = new GPXTrackSegment();
							
							NodeList trackSegNodes = trackNode.getChildNodes();
							for( int u = 0 ; u < trackSegNodes.getLength(); u++ ){
								Node trackSegNode = trackSegNodes.item(u);
								
								if( trackSegNode.getNodeName().equals("trkpt")){
									
									GPXTrackPoint trkpt = new GPXTrackPoint();
								
									NamedNodeMap attr = trackSegNode.getAttributes();
									Node lat = attr.getNamedItem("lat");
									Node lon = attr.getNamedItem("lon");
									
									trkpt.setLat(Double.parseDouble(lat.getTextContent()));
									trkpt.setLon(Double.parseDouble(lon.getTextContent()));
							
									NodeList segNodes = trackSegNode.getChildNodes();
									for( int x = 0 ; x < segNodes.getLength(); x++ ){
										Node segNode = segNodes.item(x);
										switch(segNode.getNodeName()){
											case "ele":
												trkpt.setElevation(Double.parseDouble(segNode.getTextContent()));
												break;
											case "time":
												trkpt.setTime(segNode.getTextContent());
												break;
											case "extensions":
												// == Point Extensions ===
												Node gpxTPE = XMLUtils.getChildNode("gpxtpx:TrackPointExtension", segNode);
												
												Node atemp = XMLUtils.getChildNode("gpxtpx:atemp", gpxTPE);
												Node hr = XMLUtils.getChildNode("gpxtpx:hr", gpxTPE);
												Node cad = XMLUtils.getChildNode("gpxtpx:cad", gpxTPE);
												
												if( atemp != null ) trkpt.setAtemp(Integer.parseInt(atemp.getTextContent()));
												if( hr != null ) trkpt.setHr(Integer.parseInt(hr.getTextContent()));
												if( cad != null ) trkpt.setCad(Integer.parseInt(cad.getTextContent()));
												
												break;
										}
										
									}
									
									seg.addTrackPoint(trkpt);
								}
						
							}
							
							track.addSegment(seg);
							
							
							break;
							
							
					}
				
				}
				
				tracks.add(track);
				
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
         
		
		return true;
	}
	
	
}