package prop.pers;

import static prop.gui.CostantiGUI.RISORSA_IMMAGINE_BIANCO;
import static prop.gui.CostantiGUI.RISORSA_IMMAGINE_GIALLO;
import static prop.gui.LettoreImmagini.leggiImmagineOggetto;

import java.awt.Image;

import prop.gui.CostantiGUI;
import prop.sim.Ambiente;
import prop.sim.Contatto;
import prop.sim.GeneratoreCasuale;

public class NonPredicatore extends Persona {

	private Image IMMAGINE = leggiImmagineOggetto(RISORSA_IMMAGINE_BIANCO);
	static private int progId;

	public NonPredicatore(Ambiente ambiente) {
		super(ambiente);
		this.id = progId++;
	}

	public Image getImmagine() {
		return IMMAGINE;
	}

	public void avvenuto(Contatto contatto) {
		if (GeneratoreCasuale.siVerificaEventoDiProbabilita(CostantiGUI.PROBABILITA_CONVERSIONE)) {
			this.IMMAGINE = leggiImmagineOggetto(RISORSA_IMMAGINE_GIALLO);
		}
	}

}
