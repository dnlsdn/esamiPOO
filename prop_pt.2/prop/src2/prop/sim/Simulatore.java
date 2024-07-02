package prop.sim;

import static prop.sim.CostantiSimulazione.DURATA_SIMULAZIONE;
import static prop.sim.CostantiSimulazione.NUMERO_INIZIALE_PER_TIPOLOGIA;
import static prop.sim.CostantiSimulazione.RITMO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import prop.gui.GUI;
import prop.pers.NonPredicatore;
import prop.pers.Persona;
import prop.pers.Predicatore;
import prop.stats.Statistiche;

public class Simulatore {

	final private Ambiente ambiente;
	final private List<Contatto> contatti;
	private int passo;
	private GUI gui;
	volatile boolean fineTerminazioneRichiesta;

	public Simulatore() {
		this.ambiente = new Ambiente();
		this.passo = 0;
		creaPopolazione();
		this.contatti = new LinkedList<>();
		this.fineTerminazioneRichiesta = false;
	}

	/** DA AGGIORNARE (VEDI DOMANDA 2) **/
	private void creaPopolazione() {
		for (int i = 0; i < NUMERO_INIZIALE_PER_TIPOLOGIA; i++) {
			this.ambiente.add(this.creaNonPredicatore());
			this.ambiente.add(this.creaPredicatore());
			this.ambiente.add(this.creaPredicatore());
			this.ambiente.add(this.creaPredicatore());
			this.ambiente.add(this.creaPredicatore());
		}
	}

	public NonPredicatore creaNonPredicatore() {
		return new NonPredicatore(this.ambiente);
	}

	public Predicatore creaPredicatore() {
		return new Predicatore(this.ambiente);
	}

	public List<Persona> getPersona() {
		return this.ambiente.getAllPersone();
	}

	public void setGUI(GUI gui) {
		this.gui = gui;
	}

	public Ambiente getAmbiente() {
		return this.ambiente;
	}

	public int getPasso() {
		return this.passo;
	}

	public void simula() {

		for (this.passo = 0; this.passo < DURATA_SIMULAZIONE; this.passo++) {
			eseguiPassoDellaSimulazione();
			aggiornaStatistiche();
			pausa();
			if (fineSimulazioneRichiesta()) {
				System.out.println("Simulazione interrotta al passo " + passo);
				System.out.println();
				break;
			}
		}
		System.out.println("Simulazione terminata.");

		/* Termina la simulazione corrente stampando le statistiche finali */
		new Statistiche().stampaFinale(this);

		terminaSimulazioneBrutalmente();
	}

	private boolean fineSimulazioneRichiesta() {
		return this.fineTerminazioneRichiesta;
	}

	private void eseguiPassoDellaSimulazione() {
		final List<Persona> persone = new ArrayList<>(this.ambiente.getAllPersone());
		for (Persona p : persone) // prima muovo tutti
			p.mossa();
		for (Persona persona : persone) { // poi calcolo le statistiche
			final Set<Persona> stessaPosizione = this.getAmbiente().getPersona(persona.getPosizione());
			if (stessaPosizione.size() > 1) { // se ci sono meno di 2 pesone non è un contatto
				final Contatto contatto = new Contatto(this.passo, stessaPosizione, persona.getPosizione());
				this.add(contatto);
				if (persona instanceof NonPredicatore) {
					((NonPredicatore) persona).avvenuto(contatto); /* notifica la persona dell'avvenuto contatto */
				}
			}
		}
	}

	public void add(Contatto s) {
		this.contatti.add(s);
	}

	public List<Contatto> getContatti() {
		return this.contatti;
	}

	private void aggiornaStatistiche() {
		/* stampa passo simulazione */
		this.gui.riportaNelTitolo(this.passo, this);
	}

	private void pausa() {
		try {
			this.updateGui();
			Thread.sleep(RITMO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void updateGui() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Simulatore.this.gui.repaint();
			}
		});
	}

	/**
	 * Termina la simulazione corrente arrestando l'intera JVM!!!
	 */
	public void terminaSimulazioneBrutalmente() {
		System.exit(-1);
	}

	public void richiediTerminazione() {
		this.fineTerminazioneRichiesta = true;
	}

}
