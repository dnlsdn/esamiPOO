package war.tank;

import java.util.Set;

import war.Campo;
import war.Coordinate;
import war.Direzione;

public class Shooter extends Tank {
	public Shooter(Campo campo) {
		super(campo);
	}

	@Override
	public boolean decideDiSparare(int passo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Direzione cambioDirezione(Set<Direzione> possibili) {
		Campo campo = new Campo();
		Coordinate posizione = super.getPosizione();

		Direzione direzioneMigliore = null;
		int maxIntensita = 0;

		for (Direzione direzione : possibili) {
			int intensita = campo.rilevaTracciaVerso(posizione, direzione);
			if (intensita > maxIntensita) {
				maxIntensita = intensita;
				direzioneMigliore = direzione;
			}
		}

		if (direzioneMigliore == null) {
			direzioneMigliore = Direzione.scegliAcasoTra(possibili);
		}

		return direzioneMigliore;
	}
}
