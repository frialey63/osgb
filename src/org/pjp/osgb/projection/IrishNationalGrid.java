package org.pjp.osgb.projection;

import org.pjp.osgb.Coord;
import org.pjp.osgb.LatLon;
import org.pjp.osgb.ellipsoid.Airy1830Modified;
import org.pjp.osgb.ellipsoid.Ellipsoid;

public final class IrishNationalGrid extends AbstractProjection {

	public static final Projection INSTANCE = new IrishNationalGrid();

	@Override
	public Ellipsoid ellipsoid() {
		return Airy1830Modified.INSTANCE;
	}

	@Override
	public Coord originOffset() {
		return new Coord(200000.0, 250000.0);
	}

	@Override
	public double scaleFactor() {
		return 1.000035;
	}

	@Override
	public LatLon trueOrigin() {
		return LatLon.createFromDegrees(53.5, -8.0);
	}

}
