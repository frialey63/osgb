package org.pjp.osgb;

import static org.junit.Assert.*;

import org.junit.Test;

public class NGRSTest {

	@Test
	public void testGetGridSquare() {
		NGRS ngrs = new NGRS(50000, 50000);
		assertEquals("SV", ngrs.gridSquare());

		ngrs = new NGRS(620000, 620000);
		assertEquals("OR", ngrs.gridSquare());
	}

}
