package prop.sim;

import static prop.sim.CostantiSimulazione.DIMENSIONE;

import java.util.Random;

public class GeneratoreCasuale {

	static final private Random random = new Random();
	
	/**
	 * @return le coordinate di una posizione scelta a caso tra quelle
	 *         all'interno della zona 
	 */
	static public Coordinate posizioneCasuale() {
		final int x = 1 + random.nextInt(DIMENSIONE-2);
		final int y = 1 + random.nextInt(DIMENSIONE-2);
		return new Coordinate(x,y);
	}

	static final public int generaNumeroSinoA(int n) {
		return random.nextInt(n);
	}
	
	static final public boolean siVerificaEventoDiProbabilita(float p) {
		return ( random.nextFloat() <= p ) ;
	}
	
}
