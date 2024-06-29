package ama.simulatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ama.CentroDiRaccolta;
import ama.Citta;
import ama.mezzo.Mezzo;
import ama.mezzo.Politica;
import ama.rifiuto.Rifiuto;

public class Statistiche {

	public void stampaStatisticheFinali(Citta citta) {
		final CentroDiRaccolta centro = citta.getCentroDiRaccolta();

		final Set<Rifiuto> smaltiti = centro.getRifiutiSmaltiti();
		System.out.println("Rifiuti smaltiti in totale: " + smaltiti.size());
		System.out.println();

		// (VEDI DOMANDA 3 - metodo da completare a seguire)
		System.out.println("Quantita' raccolta da ciascun mezzo impegnato:");
		final Map<Mezzo, Integer> mezzo2quantita = raccoltoPerMezzo(smaltiti);
		stampaRaccoltoPerMezzo(mezzo2quantita);
		System.out.println();

		// (VEDI DOMANDA 4 - metodo da completare a seguire)
		System.out.println("Quantita' raccolta per ogni politica:");
		final Map<Class<?>, Integer> politica2quantita = raccoltoPerPolitica(smaltiti);
		stampaRaccoltoPerPolitica(politica2quantita);
		System.out.println();

		// (VEDI DOMANDA 5 - metodo da completare a seguire)
		System.out.println("Classifica finale delle politiche raccolta:");
		final List<Class<?>> classificaTipo = ordinaPolitichePerRaccolta(politica2quantita);
		stampaClassificaPolitiche(classificaTipo);
		System.out.println();

		// (VEDI DOMANDA 7 - metodo da completare a seguire)
		System.out.println("Classifica finale dei mezzi per raccolta:");
		final SortedSet<Mezzo> classificaMezzi = ordinaMezziPerRaccolta(mezzo2quantita);
		stampaClassificaMezzi(classificaMezzi);
		System.out.println();

	}

	public Map<Mezzo, Integer> raccoltoPerMezzo(Set<Rifiuto> smaltiti) {
		final Map<Mezzo, Integer> mezzo2quantita = new HashMap<>();
		for (Rifiuto rifiuto : smaltiti) {

			Mezzo mezzo = rifiuto.getRaccoglitore();
			if (!mezzo2quantita.containsKey(mezzo)) {

				mezzo2quantita.put(mezzo, 1);
			} else {
				Integer valore = mezzo2quantita.get(mezzo);
				valore++;
			}
		}
		System.out.println(mezzo2quantita);
		return mezzo2quantita;
	}

	// UTILE PER STAMPARE RISULTATI DOMANDA 3
	private void stampaRaccoltoPerMezzo(final Map<Mezzo, Integer> mezzo2quantita) {
		for (Mezzo mezzo : mezzo2quantita.keySet()) {
			Integer quantita = mezzo2quantita.get(mezzo);
			if (quantita == null)
				quantita = 0;
			System.out.println("Il mezzo " + mezzo + " ha raccolto " + quantita);
		}
	}

	public Map<Class<?>, Integer> raccoltoPerPolitica(Set<Rifiuto> smaltiti) {
		final Map<Class<?>, Integer> politica2quantita = new HashMap<>();
		for (Rifiuto rifiuto : smaltiti) {
			Politica politica = rifiuto.getRaccoglitore().getPolitica();
			Class<?> classe = politica.getClass();
			if (!politica2quantita.containsKey(classe)) {
				politica2quantita.put(classe, 1);
			} else {
				Integer valore = politica2quantita.get(classe);
				valore++;
				politica2quantita.put(classe, valore);
			}
		}
		return politica2quantita;
	}

	// UTILE PER STAMPARE RISULTATI DOMANDA 4
	private void stampaRaccoltoPerPolitica(final Map<Class<?>, Integer> tipo2quantita) {
		for (Class<?> tipo : tipo2quantita.keySet()) {
			Integer q = tipo2quantita.get(tipo);
			if (q == null)
				q = 0;
			System.out.println("La politica " + tipo.getSimpleName() + " ha raccolto " + q);
		}
	}

	public List<Class<?>> ordinaPolitichePerRaccolta(final Map<Class<?>, Integer> politica2quantita) {
		List<Class<?>> lista = new ArrayList<Class<?>>();
		for (Map.Entry<Class<?>, Integer> entry : politica2quantita.entrySet()) {
			Class<?> classe = entry.getKey();
			if (!lista.contains(classe)) {
				lista.add(classe);
			}
		}
		Collections.sort(lista, new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> c1, Class<?> c2) {
				return politica2quantita.get(c2) - politica2quantita.get(c1);
			}
		});
		return lista;
	}

	// UTILE PER STAMPARE RISULTATI DOMANDA 5
	private void stampaClassificaPolitiche(List<Class<?>> classifica) {
		for (int i = 1; i < classifica.size() + 1; i++)
			System.out.println(i + ") " + classifica.get(i - 1).getSimpleName());
	}

	public SortedSet<Mezzo> ordinaMezziPerRaccolta(final Map<Mezzo, Integer> mezzo2quantita) {
		SortedSet<Mezzo> sortedSet = new TreeSet<Mezzo>(new Comparator<Mezzo>() {
			@Override
			public int compare(Mezzo m1, Mezzo m2) {
				int diff = mezzo2quantita.get(m2) - mezzo2quantita.get(m1);
				if (diff == 0) {
					return -1;
				} else {
					return diff;
				}
			}
		});
		for (Map.Entry<Mezzo, Integer> entry : mezzo2quantita.entrySet()) {
			Mezzo mezzo = entry.getKey();
			// System.out.println(mezzo);
			if (!sortedSet.contains(mezzo)) {
				// System.out.println("qui");
				sortedSet.add(mezzo);
			}
		}
		return sortedSet;

	}

	// UTILE PER STAMPARE RISULTATI DOMANDA 7
	private void stampaClassificaMezzi(SortedSet<Mezzo> classifica) {
		int posto = 1;
		for (Mezzo mezzo : classifica) {
			System.out.println(posto + ") " + mezzo);
			posto++;
		}
	}
}
