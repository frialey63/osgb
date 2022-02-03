package org.pjp.osgb;

import org.pjp.osgb.util.ArgChecker;
import org.pjp.osgb.util.ArgChecker.Predicate;

public final class NGRS {

	private final double easting;

	private final double northing;

	public NGRS(double easting, double northing) {
		super();

		this.easting = ArgChecker.checkPredicate(new Predicate() {

			@Override
			public boolean isTrue(double arg) {
				return (arg >= 0.0) && (arg < 700000.0);
			}
		}, "easting inside grid limits", easting);

		this.northing = ArgChecker.checkPredicate(new Predicate() {

			@Override
			public boolean isTrue(double arg) {
				return (arg >= 0.0) && (arg < 1300000.0);
			}
		}, "northing inside grid limits", northing);

	}

	public double getEasting() {
		return easting;
	}

	public double getNorthing() {
		return northing;
	}

	@Override
	public String toString() {
		return "NGRS [easting=" + easting + ", northing=" + northing + "]";
	}

	private char[][] majorCodes = { { 'S', 'T' }, { 'N', 'O' }, { 'H', 'J' } };
	private char[]   minorCodes = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public String gridSquare() {
		final double majorEasting = Math.floor(easting / 500000);
		final double minorEasting = Math.floor((easting - majorEasting * 500000) / 100000);

		final double majorNorthing = Math.floor(northing / 500000);
		final double minorNorthing = Math.floor((northing - majorNorthing * 500000) / 100000);

		final char majorCode = majorCodes[(int) majorNorthing][(int) majorEasting];
		final char minorCode = minorCodes[(int) (minorEasting + 5 * (4 - minorNorthing))];

		StringBuffer result = new StringBuffer();
		result.append(majorCode);
		result.append(minorCode);

		return result.toString();
	}
}
