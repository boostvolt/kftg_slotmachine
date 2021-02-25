import java.io.Serializable;

public class SlotMachineModel implements Serializable {

	/* Increase the credit value by one and returns the new credit value */
	public double addCoin(double credits) {
		return credits + 1.00;
	}

	/* Decrease the credit value by one and returns the new credit value */
	public double removeOneCoin(double credits) {
		return credits - 1.00;
	}

	/*
	 * Cancels current bet and decreases the credits by 3 and returns the new
	 * credit value
	 */
	public double betMax(double credits, double existingBet) {
		return credits + existingBet - 3.00;
	}

	/* Increase the bet amount by one and returns the new bet value */
	public double betOne(double betAmount) {
		return betAmount + 1.00;
	}

	/*
	 * Adds winning credits to the existing credits and returns the new credit
	 * value
	 */
	public double addWinningCoins(double credits, double betAmnt, double symbolAmnt) {
		return (betAmnt * symbolAmnt) + credits + betAmnt;
	}
}
