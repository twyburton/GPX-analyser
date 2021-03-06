package com.burton.twy.analyser.core;

import com.burton.twy.gpx.core.GPX;
import com.burton.twy.gpx.core.GPXTrack;
import com.burton.twy.gpx.core.GPXTrackSegment;

public class Main {

	public static void main(String[] args) {
		
		
		GPX gpx = new GPX();
		
		gpx.parse("D:/Documents/Cycling/rides/2019.02.17.gpx");
		
		GPXTrack trk = gpx.getTrack(0);
		GPXTrackSegment seg = trk.getSegment(0);
		
		System.out.println(seg.getTrackPointsSize());
		
		System.out.println(Calculator.getDistanceforSegment(seg));

	}

}
