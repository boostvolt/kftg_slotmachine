/*
 * CModel.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.io.Serializable;

public class CModel implements Serializable {

	/* Erhöht das Kapital um 0.50 CHF und gibt das neue Kapital zurück */
	public double addCapital(double capital) {
		return capital + 0.50;
	}

	/* Verinngert das Kapital um 0.50 CHF und gibt das neue Kapital zurück */
	public double removeCapital(double capital) {
		return capital - 0.50;
	}

	/* Erhöht den Einsatz um 0.50 CHF und gibt den neuen Einsatz zurück */
	public double addStake(double stake) {
		return stake + 0.50;
	}

	/* Addiert gewonnener Einsatz zum vorhandenen Kapital und gibt das neue Kapital zurück */
	public double addWinningStake(double capital, double stakeAmount, double symbolAmount) {
		return stakeAmount * symbolAmount + capital;
	}
}
