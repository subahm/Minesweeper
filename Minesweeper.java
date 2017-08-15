import java.util.Scanner;
import java.io.*;
import java.util.Random;
//import static java.util.Arrays.*;

public class Minesweeper{
	
	//Global Variables
	public static int row = 0;
	public static int col = 0; 
	public static int boom = 0;
	boolean win = false;
	boolean loose = false;
	public static String bombs[][];
	
	//A function asking user to input the details of the screen
	public static void userInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
	}
	
	//A function to place the bombs randomly
	/*public static void placeBombs(){
		String[][] bombs = new String[row][col+1];
		int i;						          
		for (i = 0; i < boom; i++){ 						    //Loop to keep a count of bombs placed
            int ROW = (int)(Math.random() * ((row) - 0) + 0); //Getting a random row
            int COL = (int)(Math.random() * (col) + 1); 	//Getting a random column
            if(bombs[ROW][COL] == null){ 						//Checking if the cells is empty
            	bombs[ROW][COL] = "X"; 						//Then place a bomb
            }
            else{
            	i--; 											//Else look for another cell, without increasing the number of bombs placed
            }
        }
        printCells(bombs);
    }*/
	
	//A function to ask the user to select a cell
	public static String cellInput(){
		Scanner in = new Scanner(System.in);
		System.out.println("Select a cell by entering row and column");
		int selectedRow = 0; 
		int selectedCol = 0;
		selectedRow = in.nextInt();
		selectedRow = selectedRow - 1;
		System.out.println("Printing row: "+selectedRow);
		String a = ""+selectedRow;
		if((selectedRow-1) > row){
			System.out.println("WRONG");
			return "";
		}
		selectedCol = in.nextInt();
 		String b = ""+selectedCol;
 		if(selectedCol > col){
			System.out.println("WRONG");
			return "";
		}
		String c = a + b;
		System.out.println(c);
		return c;
	}
	
	
	public static String checkMine(String screen[][], String bombs[][], String cell){
		int CELL = Integer.parseInt(cell);
		int r = CELL/10;
		int c = CELL%10;
		boolean flag = true;
		System.out.println("Printing cell: "+bombs[r][c]);
		if(bombs[r][c] != null){
			printCells(screen);
			return "You lose!";
		}
		for(int i=0; i<bombs.length-1; i++){
			for(int j=1; j<bombs[0].length-1; j++){
				if(bombs[i][j] == null){
					flag = false;
				}
			}
		}
		if(flag == true){
			printCells(screen);
			return "You win!";
		}
		else{
			System.out.println("Nice");
			System.out.println(r + " " + c);
			System.out.println(screen.length + " " + screen[0].length);
			screen[r][c] = minesNear(bombs, r, c);
			printCells(screen);
			checkMine(screen, bombs, cellInput());
		}
		return "";
	}
	
	public static String minesNear(String bombs[][], int r, int c){
		int mines = 0;
    	// check mines in all directions
    	if(r==0 && c!=1){
    	mines += mineAt(bombs, r + 1, c - 1);  // SW
    	mines += mineAt(bombs, r + 1, c);      // S
    	mines += mineAt(bombs, r + 1, c + 1);
    	mines += mineAt(bombs, r, c - 1);      // W
    	mines += mineAt(bombs, r, c + 1);
    	}
    	if(c==1 && r!=0){
    	mines += mineAt(bombs, r - 1, c);      // N
    	mines += mineAt(bombs, r - 1, c + 1);
    	mines += mineAt(bombs, r, c + 1);
    	mines += mineAt(bombs, r + 1, c);      // S
    	mines += mineAt(bombs, r + 1, c + 1);
    	}
    	if(r==bombs.length-1 && c!=bombs[0].length-1){
    	mines += mineAt(bombs, r - 1, c - 1);  // NW
    	mines += mineAt(bombs, r - 1, c);      // N
    	mines += mineAt(bombs, r - 1, c + 1);  // NE
    	mines += mineAt(bombs, r, c - 1);      // W
    	mines += mineAt(bombs, r, c + 1);      // E
    	}
    	if(c==bombs[0].length-1 && r!=bombs.length-1){
    	mines += mineAt(bombs, r - 1, c - 1);  // NW
    	mines += mineAt(bombs, r - 1, c);      // N
    	mines += mineAt(bombs, r, c - 1);      // W
    	mines += mineAt(bombs, r + 1, c - 1);  // SW
    	mines += mineAt(bombs, r + 1, c);      // S
    	}
    	if(r!=0 && c!=0 && r!=bombs.length-1 && c!=bombs[0].length-1){
    	mines += mineAt(bombs, r - 1, c - 1);  // NW
    	mines += mineAt(bombs, r - 1, c);      // N
    	mines += mineAt(bombs, r - 1, c + 1);  // NE
    	mines += mineAt(bombs, r, c - 1);      // W
    	mines += mineAt(bombs, r, c + 1);      // E
    	mines += mineAt(bombs, r + 1, c - 1);  // SW
    	mines += mineAt(bombs, r + 1, c);      // S
    	mines += mineAt(bombs, r + 1, c + 1);  // SE
    	}
    	if(mines > 0) {
		String mines2 = ""+(mines+48);
      	// we're changing an int to a char
      	// why?!
      	// http://www.asciitable.com/
      	// 48 is ASCII code for '0'
      	System.out.println("mines =  "+ mines2);
      	return mines2;
      	}
      	return " ";
	}
	
	
	public static int mineAt(String bombs[][], int r, int c) {
    // we need to check also that we're not out of array bounds as that would
    // be an error
    int a = 0;
	if(bombs[r][c]==null){
    	a = 0;
    }
    else if(bombs[r][c].equals('X')) {
      a = 1;
    }
    return a;
    }
	
	//A function to print the cells 
	public static void printCells(String screen[][]){
		int k,l;
		for(k=0; k<row; k++){
			for(l=0; l<col+1; l++){
				System.out.print(screen[k][l]+" ");
			}
			System.out.println();
		}
	}
	
	
	//function to check if any nearby block has a bomb
	/*public boolean nearbyBombs(int r, int c, int row, int col, char arr){
		 int i, j;
		 boolean rCount = false;
		 if(r != 0 || r != row-1 || c != 1 || c != col){
		 	if(arr[r][c+1]=='X' || arr[r][c-1]=='X' || arr[r+1][c]=='X' || arr[r-1][c]=='X'){
		 		rCount = true;*/

	//function return the number of nearby blocks having bombs
	
	
	public static void main(String[] args)throws IOException{
		
		//Asking for user input 
		//userInput();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
		
		String[][] screen = new String[row][col+1];
		
		//Printing the first row of numbers starting from 0
		int a,b;
		System.out.print(" ");
		for(a=1; a<=col; a++){
			System.out.print(" "+a);
		}
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
		for (ii = 1; ii <= boom; ii++){ 						    //Loop to keep a count of bombs placed
            //int ROW = (int)(Math.random() * ((row) - 0) + 0); //Getting a random row
            //int COL = (int)(Math.random() * ((col) - 0) + 0); 	//Getting a random column
            int ROW = rand.nextInt(row) + 0;
            int COL = rand.nextInt(col) + 1;
            if(bombs[ROW][COL] == null){ 						//Checking if the cells is empty
            	bombs[ROW][COL] = "X";						//Then place a bomb
            }
            else{
            	ii--; 											//Else look for another cell, without increasing the number of bombs placed
            }
        }
        
        
		printCells(screen);
		printCells(bombs);
		//placeBombs();
		//String yo = cellInput();
		//System.out.println(yo);
		System.out.println(checkMine(screen, bombs, cellInput()));

        
		
	}//end of main
}//end of class