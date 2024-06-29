package ama.mezzo;

import java.awt.Image;
import java.util.Random;

import ama.Posizione;

public abstract class Politica {
	protected int id;

	final private Random rnd;

	public Politica() {
		this.id = 0;
		this.rnd = new Random();
	}

	public int getId() {
		return this.id;
	}

	public abstract Posizione decidiDirezione(Posizione corrente);

	/**
	 * 
	 * @return un numero intero casuale tra -1,0,+1
	 */
	protected int deltaCasuale() {
		return this.rnd.nextInt(3) - 1;
	}

	public abstract Image getImmagine();

	@Override
	public String toString() {
		return getClass().getSimpleName() + getId();
	}

}
