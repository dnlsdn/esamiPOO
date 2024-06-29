package ama.mezzo;

import static ama.costanti.CostantiGUI.IMMAGINE_CAMION_ROSSO;

import java.awt.Image;

import ama.Citta;
import ama.Posizione;

public class Pendo extends Politica {

	static private int progId;
	private Citta citta;
	private int direzione;

	public Pendo(Citta citta) {
		super.id = progId++;
		this.citta = citta;
		this.direzione = 1;
	}

	public Citta getCitta() {
		return this.citta;
	}

	public Posizione decidiDirezione(Posizione p) {
		int spostamento = 1 * direzione;
		Posizione nuovaPosizione = p.traslazioneUnitaria(spostamento, 0);
		if (this.getCitta().sulBordo(nuovaPosizione)) {
			System.out.println("qui");
			direzione *= -1;
			nuovaPosizione = p.traslazioneUnitaria(spostamento * (-1), 0);
		}

		return nuovaPosizione;

	}

	public Image getImmagine() {
		return IMMAGINE_CAMION_ROSSO;
	}
}
