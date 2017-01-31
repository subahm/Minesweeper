
// FIX LINE 55 AND 36


import java.util.Scanner;
//import static java.util.Arrays.*;

public class Minesweeper{
	
	//Global Variables
	int row = 0;
	int col = 0; 
	int boom = 0;
	boolean win = false;
	boolean loose = false;
	
	
	//A function asking user to input the details of the screen
	public void userInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
	}
	
	//A function to place the bombs randomly
	public void placeBombs(){
		char[][] bombs = new char[row][col+1];
		int i;						          
		for (i = 0; i < boom; i++){ 			//Loop to keep a count of bombs placed
            int ROW = (int)(Math.random() * ((row-1) - 0) + 0); //Getting a random row
            int COL = (int)(Math.random() * (col - 1) + 1); 	//Getting a random column
            if(bombs[ROW][COL] == null){ 			//Checking if the cells is empty
            	bombs[ROW][COL] = 'X'; 				//Then place a bomb
            }
            else{
            	i--; 						//Else look for another cell, without increasing the number of bombs placed
            }
        }
    }
	
	//A function to ask the user to select a cell
	public String cellInput(){
		Scanner input = new Scanner(System.in);
		System.out.println("Select a cell by entering row and column");
		int selectedRow = 0; 
		int selectedCol = 0;
		selectedRow = input.nextInt();
		selectedCol = input.nextInt();
		char a = (char)selectedRow;
		char b = (char)selectedCol;
		return (a+b);
	}
	
	
	//A function to print the cells 
	public void printCells(char screen[][]){
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
	
	
	public void main(String[] args){
		
		//Asking for user input 
		userInput();
		
		char[][] screen = new char[row][col+1];
		
		//Printing the first row of numbers starting from 0
		int a,b;
		System.out.print(" ");
		for(a=0; a<col; a++){
			System.out.print(" "+a);
		}
		System.out.println();
		
		//Filling the screen array with '.', where the first column is filled with numbers starting from 0
		int i,j;
		char c='0';
		for(i=0; i<row; i++){
			for(j=0; j<col+1; j++){
				if(j == 0){
					screen[i][j]=c;
					c++;
					j++;
				}
				screen[i][j] = '.';
			}
		}
		
		printCells(screen);
		placeBombs();

		

        
		
	}//end of main
}//end of class
