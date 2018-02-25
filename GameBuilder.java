public class GameBuilder{
	private int level;
	private Game game;
	private static String[] mapSeedDB = {"*******************  ****** *****   ****************************",
	"*******************  ****   ******* *****   ********************",
	"**********  ******* ***** * * ***   *******  **** **************"};
	//Constructors
	public GameBuilder(){
		level = 0;
		game = gameBuilder(level);
	}
	
	public GameBuilder(int lvl){
		level = lvl;
		game = gameBuilder(level);
	}
	
	public GameBuilder(Game gam){
		level = -1;
		game = gam;
	}
	
	//Setters
	public void setLevel(int lvl){
		level = lvl;
		game = gameBuilder(level);
	}
	
	public void setGame(Game gam){
		level = -1;
		game = gam;
	}
	
	//Getters
	public int getLevel(){return level;}
	
	public Game getGame(){return game;}
	
	//Builds a game
	private static Game gameBuilder(int curLevel){
		char[][] map;
		int[] playerPos = new int[2];
		char direction;
		int[] exit = new int[2];
		if(curLevel == 0){
			map = seedToMap(mapSeedDB[curLevel]);
			playerPos[0] = 4;
			playerPos[1] = 1;
			direction = '>';
			exit[0] = 2;
			exit[1] = 4;
		}else if(curLevel == 1){
			map = seedToMap(mapSeedDB[curLevel]);
			playerPos[0] = 5;
			playerPos[1] = 1;
			direction = 'V';
			exit[0] = 2;
			exit[1] = 4;
		}else if(curLevel == 2){
			map = seedToMap(mapSeedDB[curLevel]);
			playerPos[0] = 4;
			playerPos[1] = 3;
			direction = '>';
			exit[0] = 1;
			exit[1] = 2;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Invalid level number.");
		
		return new Game(map, playerPos, direction, exit);
	}
	
	public void printGame(){
		game.printGame();
	}
	
	private static char[][] seedToMap(String seed){
		
		if(seed.length() != 64)
			throw new IllegalArgumentException("All maps are 8x8, seed is " + seed.length() + " long");
		else{
			int counter = 0;
			char[][] result = new char[8][8];
			for(int i = 0; i<result.length; i++){
				for(int j = 0; j<result[i].length; j++){
					result[i][j] = seed.charAt(counter);
					counter++;
				}
			}
			return result;
		}
	}
	
	public int move(){
		return game.move();
	}
	
	public void turn(String dir){
		game.turn(dir);
	}
}