package prop.stats;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import prop.sim.Contatto;
import prop.sim.Simulatore;

/**
 * <B>DA COMPLETARE (VEDI DOMANDA 3)</B>
 */
public class Statistiche {

	synchronized public void stampaFinale(Simulatore simulatore) {
		final List<Contatto> contatti = simulatore.getContatti();

		System.out.println(contatti.size() + " contatti rilevati.");
		System.out.println(simulatore.getContatti());
		System.out.println();

		final Map<Integer, SortedSet<Contatto>> mappa = produciStatistiche(simulatore.getContatti());
		System.out.println("Contatti per persona:");
		stampaStatistiche(mappa);
		System.out.println();
	}

	public Map<Integer, SortedSet<Contatto>> produciStatistiche(List<Contatto> contatti) {
		Map<Integer, SortedSet<Contatto>> mappa = new HashMap<Integer, SortedSet<Contatto>>();
		for (Contatto contatto : contatti) {
			if (contatto != null) {
				Integer passo = contatto.getPassoSimulazione();
				if (!mappa.containsKey(passo)) {
					SortedSet<Contatto> sortedSet = new TreeSet<Contatto>(new Comparator<Contatto>() {
						@Override
						public int compare(Contatto c1, Contatto c2) {
							int diff = c1.getCoinvolti().size() - c2.getCoinvolti().size();
							if (diff == 0) {
								return 0;
							}
							return diff;
						}
					});
					sortedSet.add(contatto);
					mappa.put(passo, sortedSet);
				} else {
					SortedSet<Contatto> sortedSet = mappa.get(passo);
					sortedSet.add(contatto);
				}
			} else {
				System.out.println("qui");
			}
		}
		return mappa;
	}

	/**
	 * <EM>N.B. UTILE PER STAMPARE RISULTATI DOMANDA 3</EM>
	 */
	private void stampaStatistiche(final Map<Integer, SortedSet<Contatto>> mappa) {
		for (Integer key : mappa.keySet()) {
			final SortedSet<Contatto> l = mappa.get(key);
			System.out.print(key + " è stato coinvolto in :");
			for (Contatto c : l)
				System.out.print(c.toString() + " ");
			System.out.println();
		}
	}
}
