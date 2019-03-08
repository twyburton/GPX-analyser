package com.burton.twy.gpx.core;

import java.util.ArrayList;
import java.util.List;

public class GPXTrackSegment {

	public GPXTrackSegment(){
		
	}
	
	
	private List<GPXTrackPoint> trkpts = new ArrayList<GPXTrackPoint>();


	public void addTrackPoint(GPXTrackPoint trkpt){
		trkpts.add(trkpt);
	}
	
	public int getTrackPointsSize(){
		return trkpts.size();
	}
	
	public GPXTrackPoint getTrackPoint( int i ){
		return trkpts.get(i);
	}
	
	
	
}
