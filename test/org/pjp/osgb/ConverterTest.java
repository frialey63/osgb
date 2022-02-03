package org.pjp.osgb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pjp.osgb.projection.NationalGrid;

public class ConverterTest {

	@Test
	public void testToCoord() {

		LatLon ll = new LatLon(DMS.createNorthing(52, 39, 27.2531), DMS.createEasting(1, 43, 4.5177));

		Coord coord = Converter.toCoord(NationalGrid.INSTANCE, ll);

		assertEquals(651409.903, coord.getEasting(), 0.001);
		assertEquals(313177.270, coord.getNorthing(), 0.001);
	}

	@Test
	public void testToLatLon() {

		Coord coord = new Coord(651409.903, 313177.270);

		LatLon ll = Converter.toLatLon(NationalGrid.INSTANCE, coord);

		DMS lattitude = ll.latitude();
		DMS longitude = ll.longitude();

		assertEquals(52, lattitude.getDegrees());
		assertEquals(39, lattitude.getMinutes());
		assertEquals(27.2531, lattitude.getSeconds(), 0.0001);

		assertEquals(1, longitude.getDegrees());
		assertEquals(43, longitude.getMinutes());
		assertEquals(4.5177, longitude.getSeconds(), 0.0001);
	}

}
