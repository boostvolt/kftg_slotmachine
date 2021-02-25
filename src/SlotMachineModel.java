import java.io.Serializable;

public class SlotMachineModel implements Serializable {

	/* Increase the credit value by one and returns the new credit value */
	public double addCoin(double credits) {
		return credits + 0.50;
	}

	/* Decrease the credit value by one and returns the new credit value */
	public double removeOneCoin(double credits) {
		return credits - 0.50;
	}

	/* Increase the bet amount by one and returns the new bet value */
	public double betOne(double betAmount) {
		return betAmount + 0.50;
	}

	 /* Adds winning credits to the existing credits and returns the new credit value */
	public double addWinningCoins(double credits, double betAmnt, double symbolAmnt) {
		return betAmnt * symbolAmnt + credits;
	}
}
