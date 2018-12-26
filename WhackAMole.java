/**
 1. Homework for PennX MOOC, Software development fundamentals od EdX platform.

   Homework assignment 1: Whack a mole game

   User enters the x and y coordinates of where they would like to take a whack.
   They have a maximum of X attempts to get all the moles.
   At any point in the game, they can input coordinates of -1, -1 in order to indicate
   that they are giving up. If the user gives up they get to see the entire grid.
   The game ends if the user is able to uncover all the moles or if the user runs out of attempts.

   Created by Marko
*/
import java.util.Random;
import java.util.Scanner;

public class WhackAMole
{
   int score; // game score
   int molesLeft; // moles left to whack
   int attemptsLeft;
   char [][] moleGrid; // 2 - d array of game grid

   public WhackAMole(int numAttempts, int gridDimension)
   {

      score = 0;
      molesLeft = 0;
      attemptsLeft = numAttempts;
      moleGrid = new char[gridDimension][gridDimension];
      buildGrid();
   }

   // builds grid with '*' char for every location
   public void buildGrid()
   {
      for (int i = 0; i < moleGrid.length; i++)
      {
         for (int j = 0; j < moleGrid.length; j++)
         {
            moleGrid[i][j] = '*';
         }
      }
   }

   /**
    * method for mole placement on grid
    */
   public boolean place(int x, int y)
   {
      if (moleGrid[x][y] == '*')
      {
         moleGrid[x][y] = 'M';
         molesLeft = molesLeft + 1;
         return true;
      }
      return false;
   }

   // method for changing value on grid if whack was successful
   public void whack(int x, int y)
   {
      if (moleGrid[x][y] == 'M')
      {
         moleGrid[x][y] = 'W'; // updating value in array if whack was successful
         molesLeft = molesLeft -1;
         attemptsLeft = attemptsLeft -1;
         score += 1;
      }
      else
         attemptsLeft -= 1;
   }

   /**
    * prints grit to user to see where were already whacked moles
    */
   public void printGridToUser()
   {
      System.out.println("Printing grid to User ...");

      for (int i = 0; i < moleGrid.length; i++)
      {
         for(int location : moleGrid[i])
         {
            System.out.print(location);
         }
         System.out.println();
      }

   }

   /**
    * prints the whole grid when user quits the game
   */
   public void printGrid()
   {
      System.out.println("Printing grid ...");

      for (int i = 0; i < moleGrid.length; i++)
      {
         for(int location : moleGrid[i])
         {
            System.out.print(location);
         }
         System.out.println();
      }
   }

   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      Random rand = new Random();

      System.out.print("Enter grid dimensions (only one integer from 1 - 100): ");
      int dimension = scanner.nextInt();
      WhackAMole game = new WhackAMole(dimension * 5, dimension);
      int succPlace = 0;

      while (dimension >= succPlace) // place successful moles
      {
         int x = rand.nextInt(dimension);
         int y = rand.nextInt(dimension);
         if (game.place(x, y))
            succPlace +=1 ;
      }
      System.out.println("Creating grid ...");
      System.out.printf("You have %d attempts.%n", dimension * 5);

      int xCoordinate;
      int yCoordinate;
      while (true)
      {
         System.out.printf("Enter x coordinate of grid (0 - %d) or -1 to quit: ", dimension -1);
         xCoordinate = scanner.nextInt();
         System.out.printf("Enter y coordinate of grid (0 - %d) or -1 to quit: ", dimension -1);
         yCoordinate = scanner.nextInt();
         if (xCoordinate == -1 && yCoordinate == -1)
         {
            break;
         }

         game.whack(xCoordinate, yCoordinate);
         if (game.moleGrid[xCoordinate][yCoordinate] == -1)
         {
            System.out.println("Whack was successful.");
            System.out.printf("You have %d attempts left", game.attemptsLeft);
            game.printGridToUser();
         }
      }

      System.out.println("Printing the whole grid ...");
      game.printGrid();

   }
} // end class



