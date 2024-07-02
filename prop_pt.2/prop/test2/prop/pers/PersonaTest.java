package prop.pers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import prop.sim.Ambiente;

/**
 * Suggerimento: Controllare che questi test abbiano successo sia prima che dopo
 * aver operato le modifiche suggerite<BR/>
 * RIVEDERE {@link #testIdProgressiviPerSani()}<BR/>
 * COMPLETARE {@link #testIdProgressiviPerSaniEInfetti()}<BR/>
 * <B>(VEDI DOMANDA 2)</B>
 */
public class PersonaTest {

	private Ambiente ambiente;

	@Before
	public void setUp() throws Exception {
		this.ambiente = new Ambiente();
	}

	@Test
	public void testIdProgressiviPerPersoneStessoTipo() {
		// DA RIVEDERE VEDI DOMANDA 2a
		assertEquals("Gli id sono progressivi base 0", 0, new NonPredicatore(this.ambiente).getId());
		assertEquals("Gli id sono progressivi base 0", 1, new NonPredicatore(this.ambiente).getId());
	}

	@Test
	public void testIdProgressiviPerPersoneTipoDiverso() {
		assertTrue(new Predicatore(this.ambiente).getId() == 0);
		assertFalse(new NonPredicatore(this.ambiente).getId() == 1);
	}

}
