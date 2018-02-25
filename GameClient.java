import java.util.*;

public class GameClient{
	public static void main(String[] args){
		GameBuilder gam;
		Scanner sc = new Scanner(System.in);
		while(true){
			boolean wonGame = false;
			int level = 0;
			while(!wonGame){
				boolean wonLevel = false;
				try{
					gam = new GameBuilder(level);
					System.out.println("LEVEL " + (level+1));
					while(!wonLevel){
						gam.printGame();
						System.out.print("Enter your command (type \"help\" to view list of commands):\n~ ");
						String command = sc.nextLine();
						try{
							if(command.substring(0,4).equalsIgnoreCase("exit")){
								System.out.println("Thanks for playing!");
								System.exit(0);
							}
							else if(command.substring(0,4).equalsIgnoreCase("turn")){
								try{
									System.out.println("Which direction do you want to turn? (left/right)");
									command = sc.nextLine();
									gam.turn(command);
								}
								catch(IllegalArgumentException e){
									System.out.println(e);
									continue;
								}
							}
							else if(command.substring(0,4).equalsIgnoreCase("move")){
								int moveStatus = gam.move();
								if(moveStatus == 1){
									wonLevel = true;
								}
							}
							else if(command.substring(0,4).equalsIgnoreCase("help")){
								System.out.println("Command\t\tDiscription");
								System.out.println("Turn\t\tTurn left/right.");
								System.out.println("Move\t\tMove forward one tile.");
								System.out.println("Exit\t\tExit game.");
								continue;
							}
							else{
								System.out.println("Invalid command.");
								continue;
							}
						}
						catch(StringIndexOutOfBoundsException e){
							System.out.println("Invalid command.");
							continue;
						}
					}
				}
				catch(ArrayIndexOutOfBoundsException e){
					wonGame = true;
					System.out.println("Congrats! You've won the game!");
				}
				level++;
			}
			System.out.println("Do you want to play again?(Y/N)");
			String again = sc.nextLine();
			if(again.equalsIgnoreCase("y")){
				System.out.println("Restarting game.");
				continue;
			}
			else{
				System.out.println("Thanks for playing!");
				break;
			}
		}
	}	
}
