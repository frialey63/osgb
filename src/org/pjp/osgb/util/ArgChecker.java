package org.pjp.osgb.util;

public final class ArgChecker {

	public static interface Predicate {
		boolean isTrue(double arg);
	}

	public static int checkNegative(String name, int num) {
		if (num < 0) {
			throw new IllegalArgumentException(name + " cannot be negative");
		}

		return num;
	}

	public static double checkNegative(String name, double num) {
		if (num < 0) {
			throw new IllegalArgumentException(name + " cannot be negative");
		}

		return num;
	}

	public static double checkPredicate(Predicate pred, String name, double num) {
		if (!pred.isTrue(num)) {
			throw new IllegalArgumentException(name + " violates the predicate");
		}

		return num;
	}

	private ArgChecker() {
		// prevent instantiation
	}
}
