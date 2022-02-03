package org.pjp.osgb.projection;

import org.pjp.osgb.Coord;
import org.pjp.osgb.LatLon;
import org.pjp.osgb.ellipsoid.Ellipsoid;

public interface Projection extends ProjectionConstants {

	double scaleFactor();

	LatLon trueOrigin();

	Coord originOffset();

	Ellipsoid ellipsoid();
}
