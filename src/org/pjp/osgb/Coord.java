package org.pjp.osgb;

public class Coord {

	private final double easting;

	private final double northing;

	public Coord(double easting, double northing) {
		super();
		this.easting = easting;
		this.northing = northing;
	}

	public double getEasting() {
		return easting;
	}

	public double getNorthing() {
		return northing;
	}

	@Override
	public String toString() {
		return "Coordinate [easting=" + easting + ", northing=" + northing + "]";
	}


}
