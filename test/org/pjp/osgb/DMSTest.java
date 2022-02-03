package org.pjp.osgb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DMSTest {

	@Test
	public void testDMSDoubleBoolean() {

		DMS dms = new DMS(50.5, true);

		assertEquals(50, dms.getDegrees());
		assertEquals(30, dms.getMinutes());
		assertEquals(0.0, dms.getSeconds(), 0.001);
	}

	@Test
	public void testDMS() {

		DMS dms1 = new DMS(50, 15, 45.5, false);

		DMS dms2 = new DMS(dms1.decimalDegrees(), false);

		assertEquals(dms1.getDegrees(), dms2.getDegrees());
		assertEquals(dms1.getMinutes(), dms2.getMinutes());
		assertEquals(dms1.getSeconds(), dms2.getSeconds(), 0.001);
	}

}
