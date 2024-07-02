package prop.gui;


import static java.awt.Color.LIGHT_GRAY;

import java.awt.Color;

public interface CostantiGUI {

	// dimensione in px di una cella in cui si svolge la simulazione
	static final public int DIM_CELLE = 24;
	
	static final public Color COLORE_BORDO  = LIGHT_GRAY;

	static final public String RISORSA_IMMAGINE_BIANCO = "white.png";

	static final public String RISORSA_IMMAGINE_ROSSO = "red.png";

	static final public String RISORSA_IMMAGINE_GIALLO = "yellow.png";

	static final public String RISORSA_IMMAGINE_MATTONE = "brick.png";

	static final public float IMMAGINE_SCALA = 1.2f;
	
	static final public float PROBABILITA_CONVERSIONE  = 0.2f;

}

