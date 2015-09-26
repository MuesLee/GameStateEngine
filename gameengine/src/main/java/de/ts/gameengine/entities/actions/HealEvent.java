package de.ts.gameengine.entities.actions;

import de.ts.gameengine.entities.StaticGameEntity;

public class HealEvent extends EntityEvent implements EntityModifier {

	private static double HEAL_AMOUNT_PERCENT = 25.0;
	
	@Override
	public void modifyEntity(StaticGameEntity entity) {
		int healthPoints = entity.getHealthPoints();
		int maxHealthPoints = entity.getMaxHealthPoints();
		int toHeal = (int) (maxHealthPoints*HEAL_AMOUNT_PERCENT);
		healthPoints = Math.min(maxHealthPoints, healthPoints+toHeal);
	}

}
