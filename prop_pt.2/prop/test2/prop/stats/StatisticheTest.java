package prop.stats;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import prop.pers.NonPredicatore;
import prop.pers.Persona;
import prop.pers.Predicatore;
import prop.sim.Ambiente;
import prop.sim.Contatto;
import prop.sim.Coordinate;

public class StatisticheTest {

	@SuppressWarnings("unused")
	private Statistiche stats;
	private Ambiente ambiente;
	private Contatto c1;
	private Contatto c2;
	private Contatto c3;
	private Predicatore p1;
	private Predicatore p2;
	private NonPredicatore np1;

	@Before
	public void setUp() {
		this.stats = new Statistiche();
		this.ambiente = new Ambiente();
		this.p1 = new Predicatore(ambiente);
		this.p2 = new Predicatore(ambiente);
		this.np1 = new NonPredicatore(ambiente);
	}

	@Test
	public void testProduciStatistica() {

		Set<Persona> set1 = new HashSet<Persona>();
		set1.add(p1);
		set1.add(np1);
		this.c1 = new Contatto(2, set1, new Coordinate(8, 18));

		Set<Persona> set2 = new HashSet<Persona>();
		set2.add(p2);
		set2.add(np1);
		this.c2 = new Contatto(8, set2, new Coordinate(27, 98));

		Set<Persona> set3 = new HashSet<Persona>();
		set3.add(p1);
		set3.add(p2);
		set3.add(np1);
		this.c3 = new Contatto(8, set3, new Coordinate(27, 98));

		Map<Integer, SortedSet<Contatto>> expected = new HashMap<Integer, SortedSet<Contatto>>();

		SortedSet<Contatto> sortedSet1 = new TreeSet<Contatto>(new Comparator<Contatto>() {
			@Override
			public int compare(Contatto c1, Contatto c2) {
				int diff = c1.getCoinvolti().size() - c2.getCoinvolti().size();
				if (diff == 0) {
					return 0;
				}
				return diff;
			}
		});
		sortedSet1.add(c1);

		SortedSet<Contatto> sortedSet2 = new TreeSet<Contatto>(new Comparator<Contatto>() {
			@Override
			public int compare(Contatto c1, Contatto c2) {
				int diff = c1.getCoinvolti().size() - c2.getCoinvolti().size();
				if (diff == 0) {
					return 0;
				}
				return diff;
			}
		});
		sortedSet2.add(c3);
		sortedSet2.add(c2);

		expected.put(2, sortedSet1);
		expected.put(8, sortedSet2);

		List<Contatto> lista = new ArrayList<Contatto>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		assertEquals(expected, this.stats.produciStatistiche(lista));
	}

}
