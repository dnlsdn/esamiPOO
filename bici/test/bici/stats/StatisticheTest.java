package bici.stats;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import bici.sim.Coordinate;
import bici.sim.Percorso;
import bici.sim.Zona;
import bici.tipo.Bianca;
import bici.tipo.Bici;
import bici.tipo.Gialla;

public class StatisticheTest {

	@SuppressWarnings("unused")
	private Statistiche stats;
	Bici b1;
	Bici b2;
	Percorso p1;
	Percorso p2;
	Percorso p3;

	@Before
	public void setUp() {
		this.stats = new Statistiche();
		b1 = new Bianca(new Zona());
		b2 = new Gialla(new Zona());
		p1 = new Percorso(b1, new Coordinate(0, 0), new Coordinate(18, 18));
		p2 = new Percorso(b2, new Coordinate(8, 0), new Coordinate(18, 27));
		p3 = new Percorso(b2, new Coordinate(8, 0), new Coordinate(18, 27));
	}

	@Test
	public void testPercorsoPerBici() {
		// DA COMPLETARE ( VEDI DOMANDA 3 )
		Map<Bici, List<Percorso>> expected = new HashMap<Bici, List<Percorso>>();
		List<Percorso> lista1 = new ArrayList<Percorso>();
		lista1.add(p1);
		List<Percorso> lista2 = new ArrayList<Percorso>();
		lista2.add(p2);
		lista2.add(p3);
		expected.put(b1, lista1);
		expected.put(b2, lista2);
		Set<Percorso> set = new HashSet<Percorso>();
		set.add(p1);
		set.add(p2);
		set.add(p3);
		assertEquals(expected, stats.percorsiPerBici(set));
	}

}
