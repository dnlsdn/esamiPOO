package bici.tipo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bici.sim.Coordinate;
import bici.sim.Zona;

public class TestGialla {
	Gialla gialla;

	@Before
	public void setUp() {
		gialla = new Gialla(new Zona());
	}

	@Test
	public void testDestinazioneNonVuota() {
		Coordinate destinazione = gialla.decidiProssimaDestinazione();
		assertNotNull(destinazione);
	}

	@Test
	public void testDestinazioneContenutaElenco() {
		Coordinate destinazione = gialla.decidiProssimaDestinazione();
		assertTrue(gialla.getElencoDestinazioni().contains(destinazione));
	}
}
