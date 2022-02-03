package org.pjp.osgb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pjp.osgb.ellipsoid.Airy1830;

public class CartesianTest {

	@Test
	public void testToLatLon() {
		Cartesian cart = new Cartesian(3874938.849, 116218.624, 5047168.208);

		LatLon ll = cart.toLatLon(Airy1830.INSTANCE);

		assertEquals(DMS.createEasting(52, 39, 27.2531).decimalDegrees(), ll.getLat(), 0.000001);
		assertEquals(DMS.createEasting(1, 43, 4.5177).decimalDegrees(), ll.getLon(), 0.000001);
		assertEquals(24.700, ll.getHeight(), 0.001);
	}

}
