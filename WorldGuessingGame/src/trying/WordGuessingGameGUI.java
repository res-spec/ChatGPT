package trying;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordGuessingGameGUI implements ActionListener {
    private String[] words = {"apple", "banana", "cherry", "orange", "pineapple", "watermelon", "grapefruit", "strawberry", "raspberry", "blueberry", "blackberry", "pomegranate", "peach", "pear", "apricot", "kiwi", "plum", "lemon", "lime", "coconut"};
    private String word;
    private int remainingGuesses;
    private boolean wordGuessed;
    private JTextField guessField;
    private JLabel guessedWordLabel;
    private JLabel guessesRemainingLabel;
    private JFrame frame;
    private String guessedWord;


    public WordGuessingGameGUI() {
        // Select a random word
        int randomIndex = (int) (Math.random() * words.length);
        word = words[randomIndex];

        // Set up the word to guess
        guessedWord = "";
        for (int i = 0; i < word.length(); i++) {
            guessedWord += "-";
        }

        // Set up the game
        remainingGuesses = word.length();
        wordGuessed = false;

        // Display the frame
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // Set up the frame
        frame = new JFrame("Word Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));
        frame.setSize(400, 150);

        guessedWordLabel = new JLabel(guessedWord, JLabel.CENTER);
        guessesRemainingLabel = new JLabel("You have " + remainingGuesses + " guesses remaining.", JLabel.CENTER);
        guessField = new JTextField();
        guessField.addActionListener(this);

        // Add the components to the frame
        frame.add(guessedWordLabel);
        frame.add(guessesRemainingLabel);
        frame.add(guessField);

        // Display the frame
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String guess = guessField.getText();
        guessField.setText("");

        if (guess.length() == word.length()) {
            if (guess.equals(word)) {
                wordGuessed = true;
            } else {
                remainingGuesses--;
                guessesRemainingLabel.setText("Incorrect guess. You have " + remainingGuesses + " guesses remaining.");
            }
        } else if (guess.length() == 1) {
            boolean letterFound = false;
            StringBuilder newGuessedWord = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    newGuessedWord.append(guess.charAt(0));
                    letterFound = true;
                } else {
                    newGuessedWord.append(guessedWord.charAt(i));
                }
            }

            guessedWordLabel.setText(newGuessedWord.toString());

            if (!letterFound) {
                remainingGuesses--;
                guessesRemainingLabel.setText("Incorrect guess. You have " + remainingGuesses + " guesses remaining.");
            }
        } else {
            guessesRemainingLabel.setText("Invalid input. Please enter a word or a single letter.");
        }

        if (guessedWordLabel.getText().equals(word)) {
            wordGuessed = true;
        }

        if (wordGuessed) {
            guessesRemainingLabel.setText("Congratulations! You guessed the word.");
            guessField.setEnabled(false);
        }
    }
}
