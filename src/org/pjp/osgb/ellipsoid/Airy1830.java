package org.pjp.osgb.ellipsoid;

public final class Airy1830 extends AbstractEllipsoid {

	public static final Ellipsoid INSTANCE = new Airy1830();

	@Override
	public double semiMajor() {
		return 6377563.396;
	}

	@Override
	public double semiMinor() {
		return 6356256.910;
	}

}
