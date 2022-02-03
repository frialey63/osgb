package org.pjp.osgb.projection;


public abstract class AbstractProjection implements Projection {

	@Override
	public double F0() {
		return scaleFactor();
	}

	@Override
	public double E0() {
		return originOffset().getEasting();
	}

	@Override
	public double N0() {
		return originOffset().getNorthing();
	}

	@Override
	public double lam0() {
		return trueOrigin().lam();
	}

	@Override
	public double phi0() {
		return trueOrigin().phi();
	}

}
