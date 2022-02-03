package org.pjp.osgb;

import org.pjp.osgb.util.ArgChecker;

public final class DMS {

	public static DMS createEasting(int degrees, int minutes, double seconds) {
		return new DMS(degrees, minutes, seconds, false);
	}

	public static DMS createWesting(int degrees, int minutes, double seconds) {
		return new DMS(degrees, minutes, seconds, true);
	}

	public static DMS createNorthing(int degrees, int minutes, double seconds) {
		return new DMS(degrees, minutes, seconds, false);
	}

	public static DMS createSouthing(int degrees, int minutes, double seconds) {
		return new DMS(degrees, minutes, seconds, true);
	}

	private final int degrees;

	private final int minutes;

	private final double seconds;

	private final boolean westOrSouth;

	public DMS(int degrees, int minutes, double seconds, boolean westOrSouth) {
		super();
		this.degrees = ArgChecker.checkNegative("degrees", degrees);
		this.minutes = ArgChecker.checkNegative("minutes", minutes);
		this.seconds = ArgChecker.checkNegative("seconds", seconds);
		this.westOrSouth = westOrSouth;
	}

	public DMS(int degrees, int minutes, boolean westOrSouth) {
		this(degrees, minutes, 0.0, westOrSouth);
	}

	public DMS(int degrees, boolean westOrSouth) {
		this(degrees, 0, 0.0, westOrSouth);
	}

	public DMS(double degrees, boolean westOrSouth) {
		ArgChecker.checkNegative("degrees", degrees);

		this.degrees = (int) Math.floor(degrees);

		final double decimalMins = (degrees - this.degrees) * 60.0;

		this.minutes = (int) Math.floor(decimalMins);
		this.seconds = (decimalMins - this.minutes) * 60.0;

		this.westOrSouth = westOrSouth;
	}

	public int getDegrees() {
		return degrees;
	}

	public int getMinutes() {
		return minutes;
	}

	public double getSeconds() {
		return seconds;
	}

	public boolean isEastOrSouth() {
		return westOrSouth;
	}

	@Override
	public String toString() {
		return "DMS [degrees=" + degrees + ", minutes=" + minutes + ", seconds=" + seconds + ", westOrSouth=" + westOrSouth + "]";
	}

	public double decimalDegrees() {
		return (westOrSouth ? -1 : 1) * (degrees + minutes / 60.0 + seconds / 3600.0);
	}
}
