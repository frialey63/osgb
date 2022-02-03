package org.pjp.osgb;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static org.pjp.osgb.util.MoreMath.sec;
import static org.pjp.osgb.util.MoreMath.sqr;

import org.pjp.osgb.ellipsoid.Ellipsoid;
import org.pjp.osgb.projection.Projection;

public final class Converter {

	private static class Helper {
		private final double nu;
		private final double rho;
		private final double eta2;

		public Helper(Projection proj, double phi) {
			Ellipsoid ellipsoid = proj.ellipsoid();
			final double a = ellipsoid.a();
			final double e2 = ellipsoid.e2();

			final double t1 = a * proj.F0();
			final double t2 = 1 - e2 * sqr(sin(phi));

			this.nu = t1 * Math.pow(t2, -0.5);
			this.rho = t1 * (1 - e2) * pow(t2, -1.5);
			this.eta2 = nu / rho - 1;
		}
	}

	public static LatLon toLatLon(Projection proj, Coord coord) {
		Ellipsoid ellipsoid = proj.ellipsoid();
		final double a = ellipsoid.a();

		double temp = coord.getNorthing() - proj.N0();
		double phi = proj.phi0();

		do {
			phi = temp / (a * proj.F0()) + phi;
			final double M = M(proj, phi);
			temp = coord.getNorthing() - proj.N0() - M;

		} while (temp >= 0.00001);

		Helper h = new Helper(proj, phi);

		final double tanPhi = tan(phi);
		final double tanPhi2 = sqr(tan(phi));
		final double tanPhi4 = sqr(tanPhi2);
		final double tanPhi6 = tanPhi2 * tanPhi4;

		final double nu3 = pow(h.nu, 3);
		final double nu5 = pow(h.nu, 5);
		final double nu7 = pow(h.nu, 7);

		final double VII = tanPhi / (2 * h.rho * h.nu);
		final double VIII = tanPhi * (5 + 3 * tanPhi2 + h.eta2 - 9 * tanPhi2 * h.eta2) / (24 * h.rho * nu3);
		final double IX = tanPhi * (61 + 90 * tanPhi2 + 45 * tanPhi4) / (720 * h.rho * nu5);

		final double secPhi = sec(phi);

		final double X = secPhi / h.nu;
		final double XI = secPhi * (h.nu / h.rho + 2 * tanPhi2) / (6 * nu3);
		final double XII = secPhi * (5 + 28 * tanPhi2 + 24 * tanPhi4) / (120 * nu5);
		final double XIIA = secPhi * (61 + 662 * tanPhi2 + 1320 * tanPhi4 +  720 * tanPhi6) / (5040 * nu7);

		final double delEasting = coord.getEasting() - proj.E0();
		final double delEasting2 = sqr(delEasting);
		final double delEasting3 = delEasting * delEasting2;
		final double delEasting4 = sqr(delEasting2);
		final double delEasting5 = delEasting * delEasting4;
		final double delEasting6 = delEasting2 * delEasting4;
		final double delEasting7 = delEasting * delEasting6;

		phi = phi - VII * delEasting2 + VIII * delEasting4 - IX * delEasting6;
		final double lam = proj.lam0() + X * delEasting - XI * delEasting3 + XII * delEasting5 - XIIA * delEasting7;

		return LatLon.createFromRadians(phi, lam);
	}

	public static Coord toCoord(Projection proj, LatLon ll) {
		final double phi = ll.phi();
		final double sinPhi = sin(phi);
		final double cosPhi = cos(phi);
		final double cosPhi3 = pow(cosPhi, 3);
		final double cosPhi5 = pow(cosPhi, 5);
		final double tanPhi2 = sqr(tan(phi));
		final double tanPhi4 = sqr(tanPhi2);

		Helper h = new Helper(proj, phi);

		final double M = M(proj, phi);

		final double I = M + proj.N0();
		final double II = (h.nu / 2) * sinPhi * cosPhi;
		final double III = (h.nu / 24) * sinPhi * cosPhi3 * (5 - tanPhi2 + 9 * h.eta2);
		final double IIIA = (h.nu / 720) * sinPhi * cosPhi5 * (61 - 58 * tanPhi2 + tanPhi4);
		final double IV = h.nu * cosPhi;
		final double V = (h.nu / 6) * cosPhi3 * ((h.nu / h.rho) - tanPhi2);
		final double VI = (h.nu / 120) * cosPhi5 * (5 - 18 * tanPhi2 + tanPhi4 + 14 * h.eta2 - 58 * tanPhi2 * h.eta2);

		final double delLam = ll.lam() - proj.lam0();
		final double delLam2 = sqr(delLam);
		final double delLam3 = delLam * delLam2;
		final double delLam4 = delLam * delLam3;
		final double delLam5 = delLam * delLam4;
		final double delLam6 = delLam * delLam5;

		final double N = I + II * delLam2 + III * delLam4 + IIIA * delLam6;
		final double E = proj.E0() + IV * delLam + V * delLam3 + VI * delLam5;

		return new Coord(E, N);
	}

	private static double M(Projection proj, double phi) {
		Ellipsoid ellipsoid = proj.ellipsoid();
		final double a = ellipsoid.a();
		final double b = ellipsoid.b();

		final double n = (a - b) / (a + b);
		final double n2 = sqr(n);
		final double n3 = n * n2;

		final double phi0 = proj.phi0();

		final double term1 = (1 + n + 5 * n2 / 4 + 5 * n3 / 4) * (phi - phi0);
		final double term2 = (3 * n + 3 * n2 + 21 * n3 / 8) * sin(phi - phi0) * cos(phi + phi0);
		final double term3 = (15 * n2 / 8 + 15 * n3 / 8) * sin(2 * (phi - phi0)) * cos(2 * (phi + phi0));
		final double term4 = (35 * n3 / 24) * sin(3 * (phi - phi0)) * cos(3 * (phi + phi0));

		return b * proj.F0() * (term1 - term2 + term3 - term4);
	}

	private Converter() {
		// prevent instantiation
	}
}
