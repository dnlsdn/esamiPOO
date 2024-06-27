package war;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import war.tank.Explorer;

public class CampoTest {

	private Campo campo;
	private Coordinate origine;
	private Coordinate arrivo;
	private Coordinate p1;
	private Direzione direzione;
	private Direzione direzione2;
	private Map<Coordinate, Integer> mappa;

	@Before
	public void setUp() throws Exception {
		this.campo = new Campo(8);
		this.origine = new Coordinate(1, 0);
		this.arrivo = new Coordinate(2, 1);

		this.p1 = new Coordinate(8, 2);
		this.direzione = new Direzione(1, 0);
		this.direzione2 = new Direzione(8, 0);
	}

	@Test
	public void test() {
		Explorer e1 = new Explorer(campo);

		e1.setDirezione(direzione);
		e1.getCampo().lasciaTraccia(e1);
		e1.setPosizione(e1.getPosizione().trasla(e1.getDirezione()));
		int expected = 2;
		int intensita = campo.rilevaTracciaVerso(e1.getPosizione(), e1.getDirezione().casuale());
		System.out.println(expected);
		System.out.println(intensita);
	}

}
