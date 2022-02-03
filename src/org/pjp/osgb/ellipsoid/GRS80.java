package org.pjp.osgb.ellipsoid;

public final class GRS80 extends AbstractEllipsoid {

	public static final Ellipsoid INSTANCE = new GRS80();

	@Override
	public double semiMajor() {
		return 6378137.000;
	}

	@Override
	public double semiMinor() {
		return 6356752.3141;
	}

}
