import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class Minesweeper{
	
	// Global Variables
	public static int row = 0;
	public static int col = 0; 
	public static int boom = 0;
	public static String bombs[][];
	
	
	/*// A function asking user to input the details of the screen
	public static void userInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
	}*/
    
    
    // A function to print the result after loosing the game
    public static void showAll(String screen[][], String bombs[][]){
    	for(int i=0; i<bombs.length; i++){
    		for(int j=0; j<bombs[0].length; j++){
    			if(bombs[i][j]==null || bombs[i][j].equals("*")){
    				;
    			}
    			else{
    				screen[i][j] = "X";
    			}
    		}
    	}
    }
	
	
	// A function to ask the user to select a cell
	public static String cellInput(){
		Scanner in = new Scanner(System.in);
		System.out.println("Select a cell by entering row and column");
		int selectedRow = 0; 
		int selectedCol = 0;
		selectedRow = in.nextInt();
		selectedRow = selectedRow - 1;
		//System.out.println("Printing row: "+selectedRow);
		String a = ""+selectedRow;
		if((selectedRow-1) > row){
			System.out.println("Invalid input, check the screen");
			return "";
		}
		selectedCol = in.nextInt();
 		String b = ""+selectedCol;
 		if(selectedCol > col){
			System.out.println("Invalid Input, check the screen");
			return "";
		}
		String c = a + b;
		//System.out.println(c);
		return c;
	}
	
	
	// A function to check for mines at the input cell
	public static String checkMine(String screen[][], String bombs[][], String cell){
		int CELL = Integer.parseInt(cell);
		int r = CELL/10;
		int c = CELL%10;
		boolean flag = true;
		//System.out.println("Printing cell: "+bombs[r][c]);
		if(bombs[r][c] != null && !bombs[r][c].equals("*")){
			showAll(screen, bombs);
			System.out.println("\n");
			System.out.println("\n" + "Printing the screen" + "\n");
			printCells(screen);
			System.out.println("\n" + "You loose!" + "\n");
    		System.exit(0);
		}
		for(int i=0; i<bombs.length-1; i++){
			for(int j=1; j<bombs[0].length; j++){
				if(bombs[i][j] == null){
					flag = false;
				}
			}
		}
		if(flag == true){
			screen[r][c] = minesNear(bombs, r, c);
			showAll(screen, bombs);
			System.out.println("\n" + "Printing the screen" + "\n");
			printCells(screen);
			return "You win!";
		}
		//System.out.println("Nice");
		//System.out.println(r + " " + c);
		//System.out.println(screen.length + " " + screen[0].length);
		screen[r][c] = minesNear(bombs, r, c);
		System.out.println("\n" + "Printing the screen" + "\n");
		printCells(screen);
		//printCells(bombs);
		return checkMine(screen, bombs, cellInput());
	}
	
	
	// A function to check for nearby mines
	public static String minesNear(String bombs[][], int r, int c){
		int mines = 0;
    	// check mines in all directions
    	if(r==0 && c==1){
			mines += mineAt(bombs, r + 1, c);
			mines += mineAt(bombs, r + 1, c + 1);
			mines += mineAt(bombs, r, c + 1);
    	}
    	if(r==bombs.length-1 && c==bombs[0].length-1){
			mines += mineAt(bombs, r, c - 1);
			mines += mineAt(bombs, r - 1, c);
			mines += mineAt(bombs, r - 1, c - 1); 
    	}
    	if(r==0 && c!=1 && c!=bombs[0].length-1){
			mines += mineAt(bombs, r + 1, c - 1);  
			mines += mineAt(bombs, r + 1, c);      
			mines += mineAt(bombs, r + 1, c + 1);
			mines += mineAt(bombs, r, c - 1);      
			mines += mineAt(bombs, r, c + 1);
    	}
    	if(r==0 && c!=1 && c==bombs[0].length-1){
			mines += mineAt(bombs, r + 1, c - 1);  
			mines += mineAt(bombs, r + 1, c);      
			mines += mineAt(bombs, r, c - 1);      
    	}
    	if(c==1 && r!=0 && r!=bombs.length-1){
			mines += mineAt(bombs, r - 1, c);   
			mines += mineAt(bombs, r - 1, c + 1);
			mines += mineAt(bombs, r, c + 1);
			mines += mineAt(bombs, r + 1, c);      
			mines += mineAt(bombs, r + 1, c + 1);
    	}
    	if(c==1 && r!=0 && r==bombs.length-1){
			mines += mineAt(bombs, r - 1, c);   
			mines += mineAt(bombs, r - 1, c + 1);
			mines += mineAt(bombs, r, c + 1);
    	}
    	if(r==bombs.length-1 && c!=bombs[0].length-1 && c!=1){
			mines += mineAt(bombs, r - 1, c - 1);  
			mines += mineAt(bombs, r - 1, c);      
			mines += mineAt(bombs, r - 1, c + 1); 
			mines += mineAt(bombs, r, c - 1);      
			mines += mineAt(bombs, r, c + 1);      
    	}
    	if(c==bombs[0].length-1 && r!=bombs.length-1 && r!=0){
			mines += mineAt(bombs, r - 1, c - 1);  
			mines += mineAt(bombs, r - 1, c);      
			mines += mineAt(bombs, r, c - 1);     
			mines += mineAt(bombs, r + 1, c - 1);  
			mines += mineAt(bombs, r + 1, c);      
    	}
    	if(r!=0 && c!=1 && r!=bombs.length-1 && c!=bombs[0].length-1){
			mines += mineAt(bombs, r - 1, c - 1); 
			mines += mineAt(bombs, r - 1, c);      
			mines += mineAt(bombs, r - 1, c + 1);  
			mines += mineAt(bombs, r, c - 1);     
			mines += mineAt(bombs, r, c + 1);
			mines += mineAt(bombs, r + 1, c - 1);  
			mines += mineAt(bombs, r + 1, c);    
			mines += mineAt(bombs, r + 1, c + 1);  
    	}
    	if(mines > 0) {
			String mines2 = ""+(mines);
      		// changing an int to a char
      		//System.out.println("mines =  "+ mines2);
      		bombs[r][c] = "*";
      		return mines2;
      	}
      	bombs[r][c] = "*";
      	return " ";
	}
	
	
	// A function that helps in counting the number of nearby mines
	public static int mineAt(String bombs[][], int r, int c) {
		if(bombs[r][c]==null || bombs[r][c].equals("*")){
			//System.out.println("r= " + r + ", c= " + c);
			//System.out.println("0");
    		return 0;
    	}
		//System.out.println("r= " + r + ", c= " + c);
    	//System.out.println("1");
    	return 1;
    }
	
	
	// A function to print the cells 
	public static void printCells(String screen[][]){
		int k,l;
		int size = screen[0].length;
		for(int m=1; m<=size-1; m++){
			System.out.print(" ");
			System.out.print("\t" + m);
		}
		System.out.print("\n");
		for(k=0; k<row; k++){
			for(l=0; l<col+1; l++){
				System.out.print(screen[k][l]+"\t");
			}
			System.out.println();
		}
	}
	
	
	// Main function
	public static void main(String[] args)throws IOException{
		//Asking for user input 
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
		
		String[][] screen = new String[row][col+1];
		
		//Printing the first row of numbers starting from 0
		/*int a,b;
		System.out.print(" ");
		for(a=1; a<=col; a++){
			System.out.print(" "+a);
		}*/
		System.out.println();
		
		//Filling the screen array with '.', where the first column is filled with numbers starting from 0
		int i,j;
		char c = '1';
		for(i=0; i<row; i++){
			for(j=0; j<col+1; j++){
				if(j == 0){
					screen[i][j]=""+c;
					c++;
					j++;
				}
				screen[i][j] = ".";
			}
		}
		
		Random rand = new Random();
		bombs = new String[row][col+1];
		int ii;						          
		for (ii = 1; ii <= boom; ii++){ 
            int ROW = rand.nextInt(row) + 0;
            int COL = rand.nextInt(col) + 1;
            if(bombs[ROW][COL] == null){ 						
            	bombs[ROW][COL] = "X";						
            }
            else{
            	ii--; 											
            }
        }
		System.out.println("\n" + "Printing the screen" + "\n");
		printCells(screen);
		//printCells(bombs);
		System.out.println("\n" + checkMine(screen, bombs, cellInput()) + "\n");
		
	}//end of main
}//end of class