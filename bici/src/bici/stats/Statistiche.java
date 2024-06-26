package bici.stats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import bici.sim.Coordinate;
import bici.sim.Percorso;
import bici.sim.Zona;
import bici.tipo.Bici;

public class Statistiche {

	synchronized public void stampaFinale(Zona zona) {
		final Set<Percorso> percorsi = zona.getPercorsi();

		System.out.println(percorsi.size() + " percorsi collezionati.");
		System.out.println(zona.getPercorsi());
		System.out.println();

		// (VEDI DOMANDA 3)
		System.out.println("Percorsi di ciascuna bicicletta:");
		final Map<Bici, List<Percorso>> bici2percorsi = percorsiPerBici(percorsi);
		stampaPercorsiPerBici(bici2percorsi);
		System.out.println();

		// (VEDI DOMANDA 4)
		System.out.println("Classifica finale delle posizioni piu' battute:");
		final SortedMap<Coordinate, Integer> pos2utilizzi = utilizzi(bici2percorsi);
		stampaUtilizzi(pos2utilizzi);
		System.out.println();
	}

	/**
	 * <B>DA COMPLETARE (VEDI DOMANDA 3)</B>
	 * 
	 * @param percorsi - insieme dei percorsi collezionati durante la simulazione
	 * @return una mappa che riporti per ogni bici (di qualsiasi tipo) la lista dei
	 *         percorsi coperti
	 */
	public Map<Bici, List<Percorso>> percorsiPerBici(Set<Percorso> percorsi) {
		// DA COMPLETARE (VEDI DOMANDA 3)
		Map<Bici, List<Percorso>> mappa = new HashMap<Bici, List<Percorso>>();
		for (Percorso percorso : percorsi) {
			Bici bici = percorso.getBici();
			if (!mappa.containsKey(bici)) {
				List<Percorso> lista = new ArrayList<Percorso>();
				lista.add(percorso);
				mappa.put(bici, lista);
			} else {
				List<Percorso> lista = mappa.get(bici);
				lista.add(percorso);
			}
		}
		return mappa;
	}

	/**
	 * <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 3</EM>
	 * 
	 * @param bici2percorsi
	 */
	private void stampaPercorsiPerBici(final Map<Bici, List<Percorso>> bici2percorsi) {
		for (Bici bici : bici2percorsi.keySet()) {
			List<Percorso> percorsi = bici2percorsi.get(bici);
			System.out.println(
					"La bicicletta " + bici + " ha coperto " + (percorsi != null ? percorsi.size() : 0) + " corse");
		}
	}

	/**
	 * <B>DA COMPLETARE (VEDI DOMANDA 4)</B>
	 * 
	 * @param bici2percorsi - insiemi dei percorsi collezionati durante la
	 *                      simulazione e raggruppati per bici
	 * @return una mappa ordinata decrescente in cui figurano come chiavi le
	 *         posizioni piu' battute come origine o destinazione di un percorso,
	 *         come valori il numero di tali percorsi
	 */
	public SortedMap<Coordinate, Integer> utilizzi(Map<Bici, List<Percorso>> bici2percorsi) {
		// DA COMPLETARE (VEDI DOMANDA 4)
		final Map<Coordinate, Integer> mappa = new HashMap<Coordinate, Integer>();
		for (Map.Entry<Bici, List<Percorso>> entry : bici2percorsi.entrySet()) {
			List<Percorso> listaPercorsi = entry.getValue();
			for (Percorso percorso : listaPercorsi) {
				Coordinate origine = percorso.getOrigine();
				if (!mappa.containsKey(origine)) {
					mappa.put(origine, 1);
				} else {
					Integer valore = mappa.get(origine);
					valore++;
					mappa.put(origine, valore);
				}
				Coordinate destinazione = percorso.getDestinazione();
				if (!mappa.containsKey(destinazione)) {
					mappa.put(destinazione, 1);
				} else {
					Integer valore = mappa.get(destinazione);
					valore++;
					mappa.put(destinazione, valore);
				}
			}
		}

		SortedMap<Coordinate, Integer> res = new TreeMap<Coordinate, Integer>(new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate c1, Coordinate c2) {
				return mappa.get(c2) - mappa.get(c1);
			}
		});

		res.putAll(mappa);
		return res;
	}

	/**
	 * <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 4</EM>
	 * 
	 * @param classifica delle posizioni piu' usate
	 */
	private void stampaUtilizzi(SortedMap<Coordinate, Integer> classifica) {
		int i = 0;
		for (Map.Entry<Coordinate, Integer> entry : classifica.entrySet()) {
			final Coordinate posizione = entry.getKey();
			final Integer numeri = entry.getValue();
			System.out.println(i + ") " + posizione + " con " + numeri + " utilizzi");
			i++;
		}
	}
}
