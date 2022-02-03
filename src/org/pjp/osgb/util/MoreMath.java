package org.pjp.osgb.util;

public final class MoreMath {

	public static double sqr(double x) {
		return x * x;
	}

	public static double sec(double x) {
		return 1 / Math.cos(x);
	}

	private MoreMath() {
		// cannot instantiate
	}
}
