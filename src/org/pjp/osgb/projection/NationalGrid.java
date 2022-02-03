package org.pjp.osgb.projection;

import org.pjp.osgb.Coord;
import org.pjp.osgb.LatLon;
import org.pjp.osgb.ellipsoid.Airy1830;
import org.pjp.osgb.ellipsoid.Ellipsoid;

public final class NationalGrid extends AbstractProjection {

	public static final Projection INSTANCE = new NationalGrid();

	@Override
	public Ellipsoid ellipsoid() {
		return Airy1830.INSTANCE;
	}

	@Override
	public Coord originOffset() {
		return new Coord(400000.0, -100000.0);
	}

	@Override
	public double scaleFactor() {
		return 0.9996012717;
	}

	@Override
	public LatLon trueOrigin() {
		return LatLon.createFromDegrees(49.0, -2.0);
	}

}
