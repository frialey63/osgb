package org.pjp.osgb.projection;

import org.pjp.osgb.Coord;
import org.pjp.osgb.LatLon;
import org.pjp.osgb.ellipsoid.Ellipsoid;
import org.pjp.osgb.ellipsoid.International1924;

public final class UTMZone31 extends AbstractProjection {

	public static final Projection INSTANCE = new UTMZone30();

	@Override
	public Ellipsoid ellipsoid() {
		return International1924.INSTANCE;
	}

	@Override
	public Coord originOffset() {
		return new Coord(500000.0, 0.0);
	}

	@Override
	public double scaleFactor() {
		return 0.9996;
	}

	@Override
	public LatLon trueOrigin() {
		return LatLon.createFromDegrees(0.0, 3.0);
	}

}
