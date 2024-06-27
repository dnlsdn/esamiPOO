package war.tank;

import static war.costanti.CostantiSimulazione.PROBABILITA_SPARO;

import java.util.Set;

import war.Campo;
import war.Direzione;
import war.simulatore.GeneratoreCasuale;

public class Explorer extends Tank {

	public Explorer(Campo campo) {
		super(campo);
	}

	public boolean decideDiSparare(int passo) {
		return GeneratoreCasuale.siVerificaEventoDiProbabilita(PROBABILITA_SPARO);
	}

	public Direzione cambioDirezione(Set<Direzione> possibili) {
		return Direzione.scegliAcasoTra(possibili);
	}
}
