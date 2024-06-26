package bici.tipo;

import static bici.gui.LettoreImmagini.leggiImmagineBici;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bici.sim.Coordinate;
import bici.sim.GeneratoreCasuale;
import bici.sim.Zona;

/**
 * Modella le fasi del ciclo di vista di una bicicletta {@link Bianca}. <B>(VEDI
 * DOMANDA 2)</B>
 */
public class Gialla extends Bici {

	static final private Image IMMAGINE_BICI_GIALLA = leggiImmagineBici(java.awt.Color.YELLOW);
	static int id = 0;
	private List<Coordinate> elencoDestinazioni = new ArrayList<Coordinate>(
			GeneratoreCasuale.generaNposizioniCasuali(8));

	public Gialla(Zona zona) {
		super(zona, id++);
	}

	public Coordinate decidiProssimaDestinazione() {
		Random random = new Random();
		int randomValue = random.nextInt(8 - 1);
		System.out.println("ok" + randomValue);
		return elencoDestinazioni.get(randomValue);
	}

	public List<Coordinate> getElencoDestinazioni() {
		return elencoDestinazioni;
	}

	public Image getImmagine() {
		return IMMAGINE_BICI_GIALLA;
	}
}
