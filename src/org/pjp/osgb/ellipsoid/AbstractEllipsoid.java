package org.pjp.osgb.ellipsoid;

public abstract class AbstractEllipsoid implements Ellipsoid {

	private static Ellipsoid defaultEllipsoid = Airy1830.INSTANCE;

	public static Ellipsoid getDefaultEllipsoid() {
		return defaultEllipsoid;
	}

	public static void setDefaultEllipsoid(Ellipsoid defaultEllipsoid) {
		AbstractEllipsoid.defaultEllipsoid = defaultEllipsoid;
	}

	@Override
	public double eccentricitySquared() {
		final double aSqr = a() * a();
		final double bSqr = b() * b();

		return (aSqr - bSqr) / aSqr;
	}

	@Override
	public double a() {
		return semiMajor();
	}

	@Override
	public double b() {
		return semiMinor();
	}

	@Override
	public double e2() {
		return eccentricitySquared();
	}

}
