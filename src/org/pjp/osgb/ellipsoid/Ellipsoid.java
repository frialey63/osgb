package org.pjp.osgb.ellipsoid;

public interface Ellipsoid extends EllipsoidConstants {

	double semiMajor();

	double semiMinor();

	double eccentricitySquared();
}
