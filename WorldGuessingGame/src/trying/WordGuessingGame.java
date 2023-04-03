package trying;
import javax.swing.*;
import java.util.Scanner;


public class WordGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set the words
        String[] words = {"apple", "banana", "cherry", "orange", "pineapple", "watermelon", "grapefruit", "strawberry", "raspberry", "blueberry", "blackberry", "pomegranate", "peach", "pear", "apricot", "kiwi", "plum", "lemon", "lime", "coconut"};

        // Select a random word
        int randomIndex = (int) (Math.random() * words.length);
        String word = words[randomIndex];

        // Set up the word to guess
        String guessedWord = "";
        for (int i = 0; i < word.length(); i++) {
            guessedWord += "-";
        }

        // Set up the game
        int remainingGuesses = word.length();
        boolean wordGuessed = false;

        System.out.println("Welcome to the Word Guessing Game!");
        System.out.println("The word has " + word.length() + " letters. You have " + remainingGuesses + " guesses to get it right.");

        // Start the game
        while (!wordGuessed && remainingGuesses > 0) {
            System.out.println("Guess the word: " + guessedWord);
            System.out.print("Enter a letter or the full word: ");
            String guess = scanner.nextLine();

            if (guess.length() == word.length()) {
                if (guess.equals(word)) {
                    wordGuessed = true;
                } else {
                    remainingGuesses--;
                    System.out.println("Incorrect guess. You have " + remainingGuesses + " guesses remaining.");
                }
            } else if (guess.length() == 1) {
                boolean letterFound = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0)) {
                        guessedWord = guessedWord.substring(0, i) + guess.charAt(0) + guessedWord.substring(i + 1);
                        letterFound = true;
                    }
                }

                if (!letterFound) {
                    remainingGuesses--;
                    System.out.println("Incorrect guess. You have " + remainingGuesses + " guesses remaining.");
                }
            } else {
                System.out.println("Invalid input. Please enter a letter or the full word.");
            }

            if (word.equals(guessedWord)) {
                wordGuessed = true;
            }
        }

        // End the game
        if (wordGuessed) {
            System.out.println("Congratulations, you guessed the word!");
        } else {
            System.out.println("Sorry, you ran out of guesses. The word was: " + word);
        }

        scanner.close();
    }
}
