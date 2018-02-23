import java.util.regex.*;

public class Game{

	private char[][] map;
	private char[] playerPos;	// 0 - x index, 1 - y index, 2 - direction
	private char[] destination;	// 0 - x index, 1 - y index
	private boolean victory;	// win condition

	public Game(){
		map = new char[9][9];
		playerPos = new char[3];
		destination = new char[2];

		playerPos[0] = '0';
		playerPos[1] = '0';
		playerPos[2] = '>';
		int curX = Character.getNumericValue(playerPos[0]);
		int curY = Character.getNumericValue(playerPos[1]);
		int tarX = Character.getNumericValue(destination[0]);
		int tarY = Character.getNumericValue(destination[1]);

		destination[0] = '0';
		destination[1] = '8';
		
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++){
				if(i == curX && j == curY){
					map[i][j] = playerPos[2];
					System.out.println("Player created.");
				}
				else if(i == tarX && j == )
					map[i][j] = 'd';			
				else
					map[i][j] = '*';
			}
		}
		createPath(
	}

	public void printGame(){
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}



	public boolean checkWin(){
		if(playerPos[0] == destination[0] && playerPos[1] == destination[1]){
			victory = true;
			System.out.println("You win!");
			System.exit(0);
		}
		return victory;
	}

	public void createPath(String pathType){
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher(pathType);

		if(pathType.equalsIgnoreCase("random")){
			// Random path
		}
		else if(pathType.equalsIgnoreCase("shortest")){
			// Shortest path
		}
		else if(m.find()){
			// Path of specified length
		}
		else
			throw new IllegalArgumentException();
		
	}

	public void findShortPath(char[][] matrix, int curX, int curY, int tarX, int tarY, String path){
		// use backtracking to find the shortest path b/w player position and destination
		int deltaX = tarX - curX;
		int deltaY = tarY - curY;
		if(deltaX>deltaY){
			int j = curX+1;
			for(int i = curY; i < deltaY; i++){
				for(; j < deltaX/deltaY; j++){
					matrix[i][j] = ' ';
					deltaX--;
				}
			}	
		}
		else{
			int j = curY+1;
			for(int i = curX; i < deltaX; i++){
				for(; j < deltaY/deltaX; j++){
					matrix[i][j] = ' ';
					deltaY--;
				}
			}
		}
	}

}
