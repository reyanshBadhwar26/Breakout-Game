
public class ShellAnimation implements Animation {

	private static int universeCount = 0;
	
	public static int getUniverseCount() {
		return universeCount;
	}

	public static void setUniverseCount(int count) {
		ShellAnimation.universeCount = count;
	}

	public Universe getNextUniverse() {

		universeCount++;
		
		if (universeCount == 1) {
			return new LevelOneUniverse();
		}
		else if (universeCount == 2){
			return new LevelTwoUniverse();
		}
		else if (universeCount == 3) {
			return new LevelThreeUniverse();
		}
		else {
			return null;
		}

	}
	
	public Universe restartUniverse(int universe) {
		
		if (universe == 1) {
			return new LevelOneUniverse();
		}
		else if (universe == 2) {
			return new LevelTwoUniverse();
		}
		else if (universe == 3) {
			return new LevelThreeUniverse();
		}
		else {
			return null;
		}
		
	}
	
}
