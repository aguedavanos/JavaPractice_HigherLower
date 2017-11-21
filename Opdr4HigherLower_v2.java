/*Opdracht: 
1. Guess the number

Write a program that generates a random number between 1 and 50 and allows the users to guess this number as depicted above.

2. Finite Guesses

Allow the user only ten guesses per number. The user has lost if the number of guesses is exceeded.
*/
import java.util.*;
import java.io.*;

public class Opdr4HigherLower_v2
{
	public static void main(String[] args)
	{
		HigherLowerGame game = new HigherLowerGame();
		game.playGame();
	}
}

class HigherLowerGame
{
	final int MAX_NUM_GUESSES = 10;
	final int MIN_NUM = 1;
	final int MAX_NUM = 50;

	public void playGame()
	{
		Console c = initConsole();
		
		//program loop
		do
		{
			//init new game
			int currNumGuesses = 0;
			int numToBeGuessed = getNewNumber();

			//current game loop
			while(currNumGuesses < MAX_NUM_GUESSES)
				{
					int currGuess = getGuess(c);
					currNumGuesses++;

					if(checkGuess(currGuess, numToBeGuessed, (currNumGuesses == MAX_NUM_GUESSES)))
					{
						break;
					}
					else if (currNumGuesses == MAX_NUM_GUESSES)
					{
						System.out.println("Game over! The number to be guessed was " + numToBeGuessed);
					}
					else
					{
						displayGuessesLeft(currNumGuesses);
					}					
				} //end current game loop				 
		}while(!checkIfQuitting(c));//end program loop (do/while)
	}


	private int getNewNumber()
	{
		Random r = new Random();
		return r.nextInt(MAX_NUM) + MIN_NUM;
	}

	private Console initConsole()
	{
		Console c = System.console();

		if (c == null)
			{
				System.err.println("Error: No console");
				System.exit(1);
			}		

		return c;
	}


	private int getGuess(Console c)
	{	
		boolean isAskedBefore = false;
		String input = "";

		while(!input.matches("(.*)\\d(.*)"))
		{	
			if(isAskedBefore)
			{
				System.out.print("I don't understand... ");
			}

			input = c.readLine("Guess a number between " + MIN_NUM + " and " + MAX_NUM + ": ");

			isAskedBefore = true;
		}
		return Integer.parseInt(input.replaceAll("[\\D]", ""));		
	}


	private boolean checkGuess(int guess, int toBeGuessed, boolean lastGuess)
	{
		if(guess == toBeGuessed)
		{
			System.out.println("You guessed it! The number to be guessed was " + toBeGuessed);
			return true;
		}
		else if(!lastGuess)
		{
			if(guess < toBeGuessed)
			{
				System.out.println("Higher");
			}
			else
			{
				System.out.println("Lower");
			}			
		}

		return false;
	}

	private void displayGuessesLeft(int guesses)
	{
		System.out.print("You've got " + (MAX_NUM_GUESSES - guesses));

		if(MAX_NUM_GUESSES - guesses == 1)
		{
			System.out.println(" guess left");
		} 
		else
		{
			System.out.println(" guesses left");
		}
	}


	private boolean checkIfQuitting(Console c)
	{
		boolean isAnswered = false;
		boolean isQuitting = false;
		String input = "";

		while (!isAnswered)
			{
				input = c.readLine("Play again? Y/N: ");

				isAnswered = true; 

				if(input.matches("(.*)Y(.*)") || input.matches("(.*)y(.*)") )
				{
					isQuitting = false;
				}
				else if(input.matches("(.*)N(.*)") || input.matches("(.*)n(.*)"))
				{
					isQuitting = true;
				}
				else
				{
					isAnswered = false;
					System.out.print("I don't understand... ");
				}
			}
		return isQuitting;
	}
}