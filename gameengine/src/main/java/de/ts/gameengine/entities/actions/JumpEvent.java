package de.ts.gameengine.entities.actions;

import de.ts.gameengine.entities.DynamicGameEntity;
import de.ts.gameengine.entities.StaticGameEntity;

public class JumpEvent extends EntityEvent implements EntityModifier, LongLastingModifier {

	private static final int JUMP_INCREASE_RATE = 5;
	private static final int JUMP_MAX_SPEED = 15;
	private static final int JUMP_START_SPEED = 5;
	
	private int maxDuration = 3;
	private int duration = maxDuration;
	
	@Override
	public void modifyEntity(StaticGameEntity entity) {
		if(entity instanceof DynamicGameEntity)
		{
			DynamicGameEntity dynamicGameEntity = (DynamicGameEntity) entity;
			if(dynamicGameEntity.isStandsOnSolidGround())
			{
				int y = dynamicGameEntity.getY();
				
				if(duration==maxDuration)
				{
					y-=JUMP_START_SPEED;
				}
				y-=(Math.min(JUMP_MAX_SPEED, JUMP_INCREASE_RATE*(maxDuration-duration)));
				
				dynamicGameEntity.setY(y);
			}
		}
	}

	@Override
	public int getMaxDuration() {
		return maxDuration;
	}

	@Override
	public void decreaseDuration() {
		if(duration >= 1)
			duration--;
	}

	@Override
	public int getDuration() {
		return duration;
	}



}
