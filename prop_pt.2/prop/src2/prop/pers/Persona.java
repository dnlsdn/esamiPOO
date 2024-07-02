package prop.pers;

import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import prop.sim.Ambiente;
import prop.sim.Coordinate;
import prop.sim.GeneratoreCasuale;

public abstract class Persona {
	protected int id;
	private Ambiente ambiente;
	private Coordinate posizione; // posizione corrente

	public Persona(Ambiente ambiente) {
		this.ambiente = ambiente;
		this.posizione = GeneratoreCasuale.posizioneCasuale();
		this.id = 0;
	}

	public Ambiente getAmbiente() {
		return this.ambiente;
	}

	public abstract Image getImmagine();

	public int getId() {
		return this.id;
	}

	public Coordinate getPosizione() {
		return this.posizione;
	}

	public void setPosizione(Coordinate nuova) {
		this.posizione = nuova;
	}

	public void mossa() {
		List<Coordinate> adiacenti = new LinkedList<>(this.getAmbiente().adiacentiA(this.getPosizione()));
		Collections.shuffle(adiacenti);
		this.setPosizione(adiacenti.get(0));
	}

	@Override
	public String toString() {
		return Persona.class.getSimpleName() + getId();
	}

}
