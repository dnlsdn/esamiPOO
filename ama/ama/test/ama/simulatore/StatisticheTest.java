package ama.simulatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ama.Posizione;
import ama.mezzo.Brown;
import ama.mezzo.Chase;
import ama.mezzo.Mezzo;
import ama.mezzo.Pendo;
import ama.rifiuto.Rifiuto;
import ama.rifiuto.Vetro;

public class StatisticheTest {

	private Simulatore simulatore;

	private Statistiche stats;

	final static private Posizione ORIGINE = new Posizione(0, 0);

	@Before
	public void setUp() throws Exception {
		this.stats = new Statistiche();
		this.simulatore = new Simulatore();
	}

	/*
	 * N.B. E' POSSIBILE USARE I METODI CHE SEGUONO (E CREARNE DI SIMILARI) PER
	 * VELOCIZZARE IL TESTING RELATIVO ALLE DOMANDE 3 E SUCCESSIVE
	 */
	private Vetro creaVetroRaccoltoDaBrowniano() {
		final Vetro rifiuto = new Vetro(ORIGINE);
		rifiuto.setRaccoltoDa(this.simulatore.creaBrowniano());
		return rifiuto;
	}

	private Vetro creaVetroRaccoltoDaChaser() {
		final Vetro rifiuto = new Vetro(ORIGINE);
		rifiuto.setRaccoltoDa(this.simulatore.creaChaser());
		return rifiuto;
	}

	/*
	 * N.B. E' POSSIBILE USARE I METODI SOPRA (E CREARNE DI SIMILARI) PER
	 * VELOCIZZARE IL TESTING RELATIVO ALLE DOMANDE 3 E SUCCESSIVE
	 */

	@Test
	public void testRaccoltoPerMezzo() {
		Vetro v1 = creaVetroRaccoltoDaBrowniano();
		Vetro v2 = creaVetroRaccoltoDaChaser();
		Set<Rifiuto> set = new HashSet<Rifiuto>();
		set.add(v1);
		set.add(v2);
		Map<Mezzo, Integer> expected = new HashMap<Mezzo, Integer>();
		expected.put(v1.getRaccoglitore(), 1);
		expected.put(v2.getRaccoglitore(), 1);
		System.out.println(expected);
		assertEquals(expected, this.stats.raccoltoPerMezzo(set));
	}

	@Test
	public void testRaccoltoPerPolitica() {
		Vetro v1 = creaVetroRaccoltoDaBrowniano();
		Vetro v2 = creaVetroRaccoltoDaChaser();
		Vetro v3 = creaVetroRaccoltoDaChaser();
		Set<Rifiuto> set = new HashSet<Rifiuto>();
		set.add(v1);
		set.add(v2);
		set.add(v3);
		Map<Class<?>, Integer> expected = new HashMap<Class<?>, Integer>();
		expected.put(v1.getRaccoglitore().getPolitica().getClass(), 1);
		expected.put(v2.getRaccoglitore().getPolitica().getClass(), 2);
		System.out.println(expected);
		assertEquals(expected, this.stats.raccoltoPerPolitica(set));
	}

	/*                              */
	/* DA COMPLETARE VEDI DOMANDA 6 */
	/*                              */

	@Test
	public void testOrdinaPoliticaPerRaccoltaListaVuota() {
		Map<Class<?>, Integer> parametro = new HashMap<Class<?>, Integer>();

		List<Class<?>> expected = new ArrayList<Class<?>>();

		expected = this.stats.ordinaPolitichePerRaccolta(parametro);

		assertTrue(expected.isEmpty());
	}

	@Test
	public void testOrdinaPoliticaPerRaccoltaBrownPrimo() {
		Map<Class<?>, Integer> parametro = new HashMap<Class<?>, Integer>();
		parametro.put(Brown.class, 18);
		parametro.put(Chase.class, 8);
		parametro.put(Pendo.class, 15);

		List<Class<?>> expected = new ArrayList<Class<?>>();

		expected.add(Brown.class);
		expected.add(Pendo.class);
		expected.add(Chase.class);

		assertEquals(expected, this.stats.ordinaPolitichePerRaccolta(parametro));
	}

	@Test
	public void testOrdinaPoliticaPerRaccoltaPendoPrimo() {
		Map<Class<?>, Integer> parametro = new HashMap<Class<?>, Integer>();
		parametro.put(Brown.class, 18);
		parametro.put(Chase.class, 8);
		parametro.put(Pendo.class, 27);

		List<Class<?>> expected = new ArrayList<Class<?>>();

		expected.add(Pendo.class);
		expected.add(Brown.class);
		expected.add(Chase.class);

		assertTrue(this.stats.ordinaPolitichePerRaccolta(parametro).get(0) == Pendo.class);
		assertFalse(this.stats.ordinaPolitichePerRaccolta(parametro).get(2) == Brown.class);
	}
}
