package ca.concordia.game.common;

public  class common {
	
	public enum CityAreas{
		DOLLYSISTERS(1), UNREALESTATE(2), DRAGONSLANDING(3), SMALLGODS(4),
		THESCOURS(5), THEHIPPO(6), THESHADES(7), DIMWELL(8), LONGWALL(9), ISLEOFGODS(10),
		SEVENSLEEPERS(11), NAPHILL(12);
		
		private final int intValue;
		
		private CityAreas(int value) { 
			intValue = value;
		}
		
		//return integer value which represents the area.
		public int intValue()
		{
			return intValue();
		}
	}
	
	

}
