package de.ts.gameengine.gamestates;

public enum GameState {

	MENU_STATE(0), LEVEL_1(1);

	private int stateNumber;

	private GameState(int stateNumber)
	{
		this.setStateNumber(stateNumber);
	}

	public int getStateNumber()
	{
		return stateNumber;
	}

	public void setStateNumber(int stateNumber)
	{
		this.stateNumber = stateNumber;
	}

	public static GameState getStateByNumber(int number)
	{
		GameState result = null;

		GameState[] values = GameState.values();
		for (int i = 0; i < values.length; i++)
		{
			GameState t = values[i];

			if (number == t.getStateNumber())
			{
				result = t;
				break;
			}
		}

		return result;
	}

}
