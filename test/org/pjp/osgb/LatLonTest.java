package org.pjp.osgb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pjp.osgb.ellipsoid.Airy1830;

public class LatLonTest {

	@Test
	public void testToCartesian() {
		LatLon ll = new LatLon(DMS.createNorthing(52, 39, 27.2531), DMS.createEasting(1, 43, 4.5177), 24.700);

		Cartesian cart = ll.toCartesian(Airy1830.INSTANCE);

		assertEquals(3874938.849, cart.getX(), 0.001);
		assertEquals(116218.624, cart.getY(), 0.001);
		assertEquals(5047168.208, cart.getZ(), 0.001);
	}

}
