package org.pjp.osgb;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static org.pjp.osgb.util.MoreMath.sqr;

import org.pjp.osgb.ellipsoid.AbstractEllipsoid;
import org.pjp.osgb.ellipsoid.Ellipsoid;

public final class Cartesian {

	private static final double LAT_TOL = 0.0000000001;

	private final double x;

	private final double y;

	private final double z;

	public Cartesian(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Cartesian(double x, double y) {
		this(x, y, 0.0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "Cartesian [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public LatLon toLatLon(Ellipsoid ellipsoid) {
		final double lam = atan2(y, x);

		final double p = sqrt(sqr(x) + sqr(y));
		double phi = atan2(z, p * (1 - ellipsoid.e2()));
		double nu;

		while (true) {
			final double sinPhi = sin(phi);
			nu = ellipsoid.a() / sqrt(1 - ellipsoid.e2() * sqr(sinPhi));
			final double newPhi = atan2((z + ellipsoid.e2() * nu * sinPhi), p);

			if (Math.abs(newPhi - phi) < LAT_TOL) {
				break;
			}
			else {
				phi = newPhi;
			}
		}

		final double H = p / cos(phi) - nu;

		return LatLon.createFromRadians(phi, lam, H);
	}

	public LatLon toLatLon() {
		return toLatLon(AbstractEllipsoid.getDefaultEllipsoid());
	}
}
