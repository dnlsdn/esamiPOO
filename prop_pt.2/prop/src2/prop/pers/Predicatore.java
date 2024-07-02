package prop.pers;

import static prop.gui.CostantiGUI.RISORSA_IMMAGINE_ROSSO;
import static prop.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import prop.sim.Ambiente;
import prop.sim.Coordinate;

public class Predicatore extends Persona {

	static final private Image IMMAGINE_ROSSA = leggiImmagineOggetto(RISORSA_IMMAGINE_ROSSO);
	static private int progId;

	public Predicatore(Ambiente ambiente) {
		super(ambiente);
		this.id = progId++;
	}

	public Image getImmagine() {
		return IMMAGINE_ROSSA;
	}

	@Override
	public void mossa() {
		List<Persona> listaPersone = new ArrayList<Persona>();
		listaPersone.addAll(this.getAmbiente().getAllPersone());
		List<Persona> listaNonPredicatori = new ArrayList<Persona>();

		for (Persona persona : listaPersone) {
			if (persona instanceof NonPredicatore) {
				listaNonPredicatori.add(persona);
			}
		}

		Persona personaNP = Collections.max(listaNonPredicatori, new Comparator<Persona>() {
			@Override
			public int compare(Persona p1, Persona p2) {
				return (int) Coordinate.distanza(getPosizione(), p1.getPosizione())
						- (int) Coordinate.distanza(getPosizione(), p2.getPosizione());
			}
		});

		final Coordinate posObiettivo = personaNP.getPosizione();
		List<Coordinate> adiacenti = new ArrayList<Coordinate>();
		adiacenti.addAll(this.getAmbiente().adiacentiA(this.getPosizione()));
		Coordinate posMigliore = Collections.min(adiacenti, new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate c1, Coordinate c2) {
				return (int) Double.compare(Coordinate.distanza(posObiettivo, c1),
						Coordinate.distanza(posObiettivo, c2));
				// (int) Coordinate.distanza(posObiettivo, c1)
				// - (int) Coordinate.distanza(posObiettivo, c2);
			}
		});

		this.setPosizione(posMigliore);
	}
}
