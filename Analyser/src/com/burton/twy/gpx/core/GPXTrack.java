package com.burton.twy.gpx.core;

import java.util.ArrayList;
import java.util.List;

public class GPXTrack {

	private String time;
	private String name;
	private int type;
	
	public GPXTrack(){
		
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	private List<GPXTrackSegment> segments = new ArrayList<GPXTrackSegment>();
	
	public void addSegment(GPXTrackSegment seg ){
		segments.add(seg);
	}
	
	public int getSegmentsSize(){
		return segments.size();
	}
	
	public GPXTrackSegment getSegment(int i){
		return segments.get(i);
	}
	
}
