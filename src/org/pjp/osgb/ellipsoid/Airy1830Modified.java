package org.pjp.osgb.ellipsoid;

public final class Airy1830Modified extends AbstractEllipsoid {

	public static final Ellipsoid INSTANCE = new Airy1830Modified();

	@Override
	public double semiMajor() {
		return 6377340.189;
	}

	@Override
	public double semiMinor() {
		return 6356034.447;
	}

}
