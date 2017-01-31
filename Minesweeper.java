import java.util.Scanner;
//import static java.util.Arrays.*;

public class Minesweeper{
	
	
	//function to check if any nearby block has a bomb
	public boolean nearbyBombs(int r, int c, int row, int col, char arr){
		 int i, j;
		 boolean rCount = false;
		 if(r != 0 || r != row-1 || c != 1 || c != col){
		 	if(arr[r][c+1]=='X' || arr[r][c-1]=='X' || arr[r+1][c]=='X' || arr[r-1][c]=='X'){
		 		rCount = true;

	//function return the number of nearby blocks having bombs
	
	
	public static void main(String[] args){
		
		//Asking for user input 
		Scanner input = new Scanner(System.in);
		int row, col, boom;
		System.out.println("Enter the number of rows");
		row = input.nextInt();
		System.out.println("Enter the number of columns");
		col = input.nextInt();
		System.out.println("Set the number of bombs");
		boom = input.nextInt();
		
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
		
		//Printing the screen array
		int k,l;
		for(k=0; k<row; k++){
			for(l=0; l<col+1; l++){
				System.out.print(screen[k][l]+" ");
			}
			System.out.println();
		}
		
		System.out.println((int)(Math.random() * (5 - 0)) + 0);// for row   1 for col
		
		/*char[][] bombs = new char[row][col+1];
		for (int ii = 0; ii < row; ii++){
            for (int jj = 1; jj < col+1; jj++){
                bombs[ii][jj] = ((int) Math.random() * 5 + 3);
            }
        }*/
        
		
	}//end of main
}//end of class
