import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class will form the main operating class that will call upon all the methods and files.
 *
 */
public class Main {
    /**
     * main method that calls upon most of the methods.
     * 
     * @param args
     */
    public static void main(String[] args) {
        //declaring variables
        String TEXT_FILE_PATH = "WordList.txt";
        String wordToGuess = null;
        ArrayList<String> WordList = null;
        ArrayList<Character> playerGuesses = null;
        int i; //loop control variable
        int wrongCount = 0;
        Scanner keyboardScanner = new Scanner(System.in); //for user input
        char letterGuess;
        //calling upon the FileReader method for reading the file and returning String
        // ArrayList of all the words in the file
        WordList = FileReader(TEXT_FILE_PATH);
        //calling upon the getRandomWord method to pick a random word
        wordToGuess = getRandomWord(WordList);
        //TODO: Remove this line after the program is done
        System.out.println(wordToGuess);
        //Initializing a new Character ArrayList which will hold all of the player guesses
        playerGuesses = new ArrayList<Character>();
        //Using a for loop to print the word to be guessed in dashes, except the right
        // guesses which the player has already made
        while(true) {
            //printing the starting of the hangman

            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You Lose :(");
                break;
            }

            printWordState(wordToGuess, playerGuesses);
            if (!getPlayerGuess(wordToGuess, playerGuesses, keyboardScanner)) {
                wrongCount++;
            }


            if (printWordState(wordToGuess, playerGuesses)) {
                break;
            }

            System.out.println("Please enter your guess for the word: ");
            if (keyboardScanner.nextLine().equals(wordToGuess)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Nope! Wrong guess. ");
            }
        }
    }//main ends

    /**
     * The following method has a list of if statements. Depending on how many wrong guesses 
     * the user has made, the method prints the updates hangman
     * @param wrongCount
     */
    private static void printHangedMan(int wrongCount) {
        System.out.println();
        System.out.println("-----------");
        System.out.println(" |       |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    /**
     * The following method uses the Scanenr class to get keyboard input of the player guess.
     * If the guess matches a letter in the word which is the be guessed, a boolean true
     * value is returned, else false.
     *
     * @param wordToGuess
     * @param playerGuesses
     * @param keyboardScanner
     * @return (booleans) whether the letter guessed by the user is true or false
     */
    private static boolean getPlayerGuess(String wordToGuess,
                                          ArrayList<Character> playerGuesses, Scanner keyboardScanner) {
        String letterGuess;
        //Asking user for their first guess (the first character)
        System.out.println("Please enter a letter");
        letterGuess = keyboardScanner.nextLine();
        //Adding letter which has been guesses by the user to the ArrayList of guessed
        // characters
        playerGuesses.add(letterGuess.charAt(0));
        return (wordToGuess.contains(letterGuess));
    }

    /**
     * Prints the word to be guessed but encrypted in hyphens. As the user makes the right
     * choices, the users guesses are uncovered. The method checks which words have been
     * guessed from a Character Array List using a for loop and then prints the updated word.
     * @param wordToGuess
     * @param playerGuesses
     * @return (boolean) whether the whole word has been correctly guessed or not
     */
    private static boolean printWordState(String wordToGuess, ArrayList<Character> playerGuesses) {
        int i; //loop control variable
        int correctCount = 0;
        for (i = 0; i < wordToGuess.length(); i++) {
            //if condition to check if the letter at the current position of the word has
            // already been guessed
            if (playerGuesses.contains(wordToGuess.charAt(i))) {
                System.out.print(wordToGuess.charAt(i));
                correctCount++;
            } else {
                System.out.print("_");
            }
        }
        System.out.println();

        return (wordToGuess.length() == correctCount);
    }//printWordState end

    /**
     * The FileReader method takes A string variable holding the file path as input, uses the
     * File class to read the file and while loop to add each of the Strings into a
     * previously, locally declared String ArrayList. The ArrayList is then returned when the
     * while loop ends. The method may throw a FileNotFoundException which is handled in the
     * FileReader method itself using a try-catch block structure.
     *
     * @param FilePath (String) stores the FilePath from which the letters have to be read
     * @return
     */
    public static ArrayList<String> FileReader(String FilePath) {
        //declaring variables
        ArrayList<String> listOfWords = new ArrayList<>(); //List to hold all words read
        Scanner reader =  null; //Scanner to read file contents
        //Declaring Scanner variable to read all the words
        try {
            reader = new Scanner(new File(FilePath));
        } catch (FileNotFoundException e) {
            System.out.println("Error in FileReader method parameter. File not located/found.");
            e.printStackTrace();
        }
        //Creating a while loop to read all of the content in the files
        while (reader.hasNext()) {
            //Adding word to String ArrayList
            listOfWords.add(reader.next());
        }
        //TODO: Complete the method
        return listOfWords;
    }//FileReader ends

    /**
     * The getRandomWord method takes in a String ArrayList as a parameter input. It uses the
     * size of the ArrayList as the upper bound in the nexInt() method of the Random class to
     * get a random word. The String at that position of the ArrayList
     *
     * @param listOfWords (String ArrayList) to get the upper bound of the Random class
     * @return randomWord (String) Random word chosen from the ArrayList
     */
    public static String getRandomWord(ArrayList<String> listOfWords) {
        //declaring variables
        String randomizedWord = null;

        //Creating an object of class Random
        Random rand = new Random();
        //Finding a random number in the range of the size of the ArrayList and assigning the
        // String at the number tin the arrayList to the randomizedWord variables
        randomizedWord = listOfWords.get(rand.nextInt(listOfWords.size()));
        //returning the randomizedWord
        return randomizedWord;
    }//getRandomWord ends
}//Main class ends
