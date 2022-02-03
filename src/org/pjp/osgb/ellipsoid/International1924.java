package org.pjp.osgb.ellipsoid;

public final class International1924 extends AbstractEllipsoid {

	public static final Ellipsoid INSTANCE = new International1924();

	@Override
	public double semiMajor() {
		return 6378388.000;
	}

	@Override
	public double semiMinor() {
		return 6356911.946;
	}

}
