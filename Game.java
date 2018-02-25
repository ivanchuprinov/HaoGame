public class Game{

	private char[][] map;
	private int[] playerPos;	// 0 - x index (int), 1 - y index (int)
	private char direction;		// can be ^, <, >, V
	private int[] exit;	// 0 - x index, 1 - y index

	
	//Constructors:
	public Game(){
	}
	
	public Game(char[][] map, int[] playerPos, char direction, int[] exit){
		this.map = map;
		this.playerPos = playerPos;
		this.direction = direction;
		this.exit = exit;
		plant();
	}

	//Setters:
	public void setMap(char[][] map){this.map = map;}
	
	public void setPP(int[] playerPos){this.playerPos = playerPos;}
	
	public void setDir(char direction){this.direction = direction;}
	
	public void setExit(int[] exit){this.exit = exit;}
	
	
	//Getters:
	public char[][] getMap(){return map;}
	
	public int[] getPP(){return playerPos;}
	
	public char getDir(){return direction;}
	
	public int[] getExit(){return exit;}
	
	//Plant player and exit on the map
	public void plant(){
		if(map[playerPos[0]][playerPos[1]] != '*' && map[exit[0]][exit[1]] != '*'){
			map[playerPos[0]][playerPos[1]] = direction;
			map[exit[0]][exit[1]] = 'E';
		}
		else
			throw new IllegalArgumentException("Can only plant into empty spot");
	}
	
	
	//Print game
	public void printGame(){
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
	
	public void turn(String dir){
		if(dir.equalsIgnoreCase("left")){
			switch(direction){
				case '>':
					direction = '^';
					plant();
					break;
				case '^':
					direction = '<';
					plant();
					break;
				case '<':
					direction = 'V';
					plant();
					break;
				case 'V':
					direction = '>';
					plant();
					break;
				default:
					throw new IllegalArgumentException("Something's wrong with your direction: " + direction);
			}
		}
		else if(dir.equalsIgnoreCase("right")){
			switch(direction){
				case '>':
					direction = 'V';
					plant();
					break;
				case 'V':
					direction = '<';
					plant();
					break;
				case '<':
					direction = '^';
					plant();
					break;
				case '^':
					direction = '>';
					plant();
					break;
				default:
					throw new IllegalArgumentException("Something's wrong with your direction: " + direction);
			}
		}
		else
			throw new IllegalArgumentException("Invalid command: turn " + dir);
		System.out.println("Turn " + dir + " complete:");
	}
	
	public char tileInFront(){
		try{
			switch(direction){
				case '>':
					return map[playerPos[0]][playerPos[1]+1];
				case 'V':
					return map[playerPos[0]+1][playerPos[1]];
				case '<':
					return map[playerPos[0]][playerPos[1]-1];
				case '^':
					return map[playerPos[0]-1][playerPos[1]];
				default:
					throw new IllegalArgumentException("Something's wrong with your direction: " + direction);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			return '*';
		}
	}

	public int move(){
		if(tileInFront() == ' '){
			switch(direction){
				case '>':
					map[playerPos[0]][playerPos[1]] = ' ';
					playerPos[1]++;
					plant();
					break;
				case 'V':
					map[playerPos[0]][playerPos[1]] = ' ';
					playerPos[0]++;
					plant();
					break;
				case '<':
					map[playerPos[0]][playerPos[1]] = ' ';
					playerPos[1]--;
					plant();
					break;
				case '^':
					map[playerPos[0]][playerPos[1]] = ' ';
					playerPos[0]--;
					plant();
					break;
				default:
					throw new IllegalArgumentException("Something's wrong with your direction: " + direction);
			}
			System.out.println("Move forward successful.");
			return 0;
		}
		else if(tileInFront() == 'E'){
			System.out.println("Level complete!");
			return 1;
		}
		else{
			System.out.println("Cannot move forward.");
			return -1;
		}
	}
}
