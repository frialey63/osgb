package org.pjp.osgb;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import static org.pjp.osgb.util.MoreMath.sqr;

import org.pjp.osgb.ellipsoid.AbstractEllipsoid;
import org.pjp.osgb.ellipsoid.Ellipsoid;
import org.pjp.osgb.util.ArgChecker;

public final class LatLon {

	public static LatLon createFromDegrees(double lat, double lon, double height) {
		return new LatLon(lat, lon, height);
	}

	public static LatLon createFromDegrees(double lat, double lon) {
		return createFromDegrees(lat, lon, 0.0);
	}

	public static LatLon createFromRadians(double phi, double lam, double height) {
		return new LatLon(Math.toDegrees(phi), Math.toDegrees(lam), height);
	}

	public static LatLon createFromRadians(double phi, double lam) {
		return createFromRadians(phi, lam, 0.0);
	}

	private final double lat;

	private final double lon;

	private final double height;

	private LatLon(double lat, double lon, double height) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.height = ArgChecker.checkNegative("height", height);
	}

	public LatLon(DMS lat, DMS lon, double height) {
		super();
		this.lat = lat.decimalDegrees();
		this.lon = lon.decimalDegrees();
		this.height = ArgChecker.checkNegative("height", height);
	}

	public LatLon(DMS lat, DMS lon) {
		this(lat, lon, 0.0);
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "LatLon [height=" + height + ", lat=" + lat + ", lon=" + lon + "]";
	}

	public double phi() {
		return toRadians(lat);
	}

	public double lam() {
		return toRadians(lon);
	}

	public double H() {
		return height;
	}

	public DMS latitude() {
		return new DMS(Math.abs(lat), lat < 0.0);
	}

	public DMS longitude() {
		return new DMS(Math.abs(lon), lon < 0.0);
	}

	public Cartesian toCartesian(Ellipsoid ellipsoid) {
		final double sinPhi = sin(phi());
		final double cosPhi = cos(phi());

		final double nu = ellipsoid.a() / sqrt(1 - ellipsoid.e2() * sqr(sinPhi));

		final double x = (nu + H()) * cosPhi * cos(lam());

		final double y = (nu + H()) * cosPhi * sin(lam());

		final double z = ((1 - ellipsoid.e2()) * nu + H()) * sinPhi;

		return new Cartesian(x, y, z);
	}


	public Cartesian toCartesian() {
		return toCartesian(AbstractEllipsoid.getDefaultEllipsoid());
	}
}
