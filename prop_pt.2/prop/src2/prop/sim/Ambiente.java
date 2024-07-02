package prop.sim;

import static prop.sim.CostantiSimulazione.DIMENSIONE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import prop.pers.NonPredicatore;
import prop.pers.Persona;
import prop.pers.Predicatore;

public class Ambiente {

	final private int dimensione;

	final private Set<Coordinate> ostacoli;

	final private List<Persona> persone;

	/**
	 * Crea un ambiente (quadrato) delle dimensioni prefissate
	 */
	public Ambiente() {
		this.dimensione = DIMENSIONE;
		this.ostacoli = new HashSet<>();
		this.inizializzaBordi(this.dimensione);
		this.persone = new ArrayList<>();
	}

	private void inizializzaBordi(int dim) {
		for (int i = 0; i < dim; i++) {
			addOstacolo(0, i);
			addOstacolo(i, 0);
			addOstacolo(dim - 1, i);
			addOstacolo(i, dim - 1);
		}
	}

	public int getDimensione() {
		return this.dimensione;
	}

	public void addOstacolo(int x, int y) {
		this.ostacoli.add(new Coordinate(x, y));
	}

	public Set<Coordinate> getOstacoli() {
		return this.ostacoli;
	}

	public boolean collideConOstacolo(Coordinate pos) {
		return this.getOstacoli().contains(pos);
	}

	public boolean isFuoriDaBordi(Coordinate pos) {
		final int x = pos.getX();
		final int y = pos.getY();
		return x <= 0 || x >= this.dimensione - 1 || y <= 0 || y >= this.dimensione - 1;
	}

	public void add(NonPredicatore p) {
		this.persone.add(p);
	}

	public void add(Predicatore p) {
		this.persone.add(p);
	}

	public List<Persona> getAllPersone() {
		return this.persone;
	}

	public Set<Persona> getPersona(Coordinate posizione) {
		final Set<Persona> risultato = new HashSet<>();
		for (Persona persona : getAllPersone())
			if (persona.getPosizione().equals(posizione))
				risultato.add(persona);
		return risultato;
	}

	public void rimuoviAll(Set<NonPredicatore> persone) {
		this.persone.removeAll(persone);
	}

	public void rimuoviPersona(NonPredicatore persona) {
		this.persone.remove(persona);
	}

	public Set<Coordinate> adiacentiA(Coordinate corrente) {
		Set<Coordinate> adiacenti = new HashSet<>();
		final int x = corrente.getX();
		final int y = corrente.getY();
		adiacenti.add(new Coordinate(x + 1, y + 1));
		adiacenti.add(new Coordinate(x - 1, y - 1));
		adiacenti.add(new Coordinate(x + 1, y - 1));
		adiacenti.add(new Coordinate(x - 1, y + 1));
		Set<Coordinate> possibili = new HashSet<>();
		for (Coordinate c : adiacenti)
			if (!this.collideConOstacolo(c) && !this.isFuoriDaBordi(c))
				possibili.add(c);
		return possibili;
	}

}
