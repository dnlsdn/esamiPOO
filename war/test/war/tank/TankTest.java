package war.tank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import war.Campo;

/**
 * Controllare che questi test abbiano successo sia prima che dopo aver operato
 * le modifiche suggerite<BR/>
 */
public class TankTest {

	private Campo campo;
	private Explorer e1;
	private Explorer e2;
	private Shooter s1;
	private Shooter s2;

	@Before
	public void setUp() throws Exception {
		this.campo = new Campo();
		Factory.reset();
		e1 = new Explorer(campo);
		e2 = new Explorer(campo);
		s1 = new Shooter(campo);
		s2 = new Shooter(campo);
	}

	@After
	public void tearDown() throws Exception {
		Factory.reset();
	}

	@Test
	public void testIdProgressiviPerIlPrimoTipoDinamico() {
		assertFalse(e1.getId() == 1);
		assertEquals(1, e2.getId());
	}

	@Test
	public void testIdProgressiviPerAltroTipoDinamico() {
		assertTrue(e1.getId() == 0);
		assertNotEquals(2, e2.getId());
	}

}
