/*
 * CModel.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.io.Serializable;

public class CModel implements Serializable {

	/* Increase the credit value by one and returns the new credit value */
	public double addCapital(double capital) {
		return capital + 0.50;
	}

	/* Decrease the credit value by one and returns the new credit value */
	public double removeCapital(double capital) {
		return capital - 0.50;
	}

	/* Increase the bet amount by one and returns the new bet value */
	public double addStake(double stakeAmount) {
		return stakeAmount + 0.50;
	}

	 /* Adds winning credits to the existing credits and returns the new credit value */
	public double addWinningStake(double capital, double stakeAmount, double symbolAmount) {
		return stakeAmount * symbolAmount + capital;
	}
}
