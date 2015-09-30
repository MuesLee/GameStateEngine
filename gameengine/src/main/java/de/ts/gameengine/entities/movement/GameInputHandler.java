package de.ts.gameengine.entities.movement;

public class GameInputHandler {
	
	private AnalogMoveActionHandler analogMoveActionHandler;
	
	private SpecialMoveActionHandler specialMoveActionHandler;

	public GameInputHandler(AnalogMoveActionHandler analogMoveActionHandler,
			SpecialMoveActionHandler specialMoveActionHandler) {
		super();
		this.setAnalogMoveActionHandler(analogMoveActionHandler);
		this.setSpecialMoveActionHandler(specialMoveActionHandler);
	}

	public SpecialMoveActionHandler getSpecialMoveActionHandler() {
		return specialMoveActionHandler;
	}

	public void setSpecialMoveActionHandler(SpecialMoveActionHandler specialMoveActionHandler) {
		this.specialMoveActionHandler = specialMoveActionHandler;
	}

	public AnalogMoveActionHandler getAnalogMoveActionHandler() {
		return analogMoveActionHandler;
	}

	public void setAnalogMoveActionHandler(AnalogMoveActionHandler analogMoveActionHandler) {
		this.analogMoveActionHandler = analogMoveActionHandler;
	}
	
	
	

}
