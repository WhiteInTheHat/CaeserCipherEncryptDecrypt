/**
 * This class contains methods used in the process of brute forcing a caesar cipher message.
 * It has 4 methods.
 * The first method is the main method, which the JDK runs first. It mostly contains necesarry checks to make sure it has correct inputs, and Caesar cipher algorithms to output a correct answer.
 * The second method is the count method, this will count the number of times a character appears in a string and stores it in a array.
 * The thrid method is the frequency method, this will calculate the frequency of letters appearing in the string and stores it in a array.
 * The last method is the Chi-Squared method, this will take two frequency arrays and calculate the Chi-Squared score using the Chi-Squared formula.
 */
public class Brutus {

    // This is the an array containing the frequency that letters appear in the english dictionary
    /**
     * This just contains the frequency of letters appearing in the english dictionary.
     */
    public static final double[] english = {
    0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
    0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
    0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };


    // The main method that the JDK compiler will read and run first (Everything works from the main method)
    /**
     * This section of code has two main uses. Firstly getting the correct outputs, secondly outputting the correct answer to the encrypted message.
     * With the use of Try and Catch I can assess the input and output a correct answer depending on the type of error that occurs
     * There is two Caesar ciphers within the main function under the Try else statement, the first Caesar cipher will carry out all possible shifts and the second will output the correct answer.
     * This is all done by testing all the Chi-Squared scores of every shift and then storing the smallest value. The smallest value means that is the correct shift so we use the shift to output the right answer.
     * @param args This will take in one argument which will be the encrypted message itself.
     */
    public static void main(String[] args) {
        
        
        // This try section is used to determine if too few parameters are entered or if the value entered is not a number if the value entered for key is a letter for example
        try {

            // Declaring variables needed
            String phrase = args[0]; int numberOfParameters = args.length;


            // This is used to stop the code from running if more than 2 parameters are entered
            if (numberOfParameters > 1) {
                System.out.println("Too many parameters! ");
                System.out.println("Usage: java Brutus \"cipher text\"");
            }

            // This is where the main code will occur
            else { 
                
                // All variables that have been initialised but not defined required in code bellow
                int i;
                char letter;
                int key;
                double[] tempFreq;
                double tempChi;
                char encryptedLetter;

                // All variables that have been initialised and definied
                double min = 100000000000.0; // This variable is set to an outrangously large number that will never be reached for simplicity purposes
                int lengthOfString = phrase.length();
                char[] wordArray = phrase.toCharArray();
                String encryptedText = "";
                int mainKey = 0;


                // This section of code where this for loop starts and ends is a single block of code that computes all the possible shifts from 0 to 26.
                // The shifts are used to calculate the frequency of every letter in that shift.
                // It will then calculate the Chi-Squared score and store the smallest score in the variable min and the key that was used to get the smallest score.
                
                // Increments shift by 1 each time starting with 0.
                for (key = 0; key < 26; key++) {
                    encryptedText = "";

                    // Converts each character in the string to a new character with the given shift
                    for (i = 0; i < lengthOfString; i++) {

                        // Fetches a letter from the wordArray to convert (Does it letter by letter)
                        letter = wordArray[i];

                        // Outer if statement checks if the character is a letter, if not just return the character
                        // Inner if statement checks what type of letter it is (Caps/Uncaps) and if the letter exceeds the ascii value then it will go back on itself and return the correct character.
                        if (Character.isLetter(letter) == true) {
                            
                            // Forces it to be a character (In java you can do "Letter" - "Number")
                            encryptedLetter = (char) (letter + key);

                            // This section here keeps the letters within the ascii range for lower and uppercase words
                            if (encryptedLetter > 'z' && Character.isLowerCase(letter) == true) {
                                encryptedLetter = (char) (encryptedLetter - 26);
                            }
                            else if (encryptedLetter > 'Z' && Character.isUpperCase(letter) == true) {
                                encryptedLetter = (char) (encryptedLetter - 26);
                            }
                            else if (encryptedLetter < 'a' && Character.isLowerCase(letter) == true) {
                                encryptedLetter = (char) (encryptedLetter + 26);
                            }
                            else if (encryptedLetter < 'A' && Character.isUpperCase(letter) == true) {
                                encryptedLetter = (char) (encryptedLetter + 26);
                            }

                        }

                        // If this character is not a letter than just store that character as it is
                        else {
                            encryptedLetter = letter;
                        }

                        // Stores all letters that have been encrypted in this string
                        encryptedText = encryptedText + encryptedLetter;
                    }

                    // Calculates the frequency for that specific shift
                    tempFreq = frequency(encryptedText);

                    // Calculates the Chi-Squared score for that specific shift
                    tempChi = chiSquared(english,tempFreq);

                    // Compares the Chi-Sqaured score to a previous score and stores the smallest one
                    if (tempChi < min) {
                        min = tempChi;
                        mainKey = key;
                    }


                }

                // Resets the encrypedText variable
                encryptedText = "";

                
                // This will complete the final Caesar Cipher with the correct shift
                // A lot of this is repeated from the above code
                for (i = 0; i < lengthOfString; i++) {
                    letter = wordArray[i];


                    if (Character.isLetter(letter) == true) {
                        
                        encryptedLetter = (char) (letter + mainKey);

                        if (encryptedLetter > 'z' && Character.isLowerCase(letter) == true) {
                            encryptedLetter = (char) (encryptedLetter - 26);
                        }

                        else if (encryptedLetter > 'Z' && Character.isUpperCase(letter) == true) {
                            encryptedLetter = (char) (encryptedLetter - 26);
                        }

                        else if (encryptedLetter < 'a' && Character.isLowerCase(letter) == true) {
                            encryptedLetter = (char) (encryptedLetter + 26);
                        }

                        else if (encryptedLetter < 'A' && Character.isUpperCase(letter) == true) {
                            encryptedLetter = (char) (encryptedLetter + 26);
                        }


                    }
                    else {
                        encryptedLetter = letter;
                    }


                    encryptedText = encryptedText + encryptedLetter;
               }


               System.out.println(encryptedText);


            }


        } catch(Exception few){ // Catches the error and returns valid outputs for certain errors

            int numberOfParameters = args.length;

            if (numberOfParameters < 1) {
                System.out.println("Too few parameters! ");
                System.out.println("Usage: java Brutus \"cipher text\"");
            }


        }   


    }


    // This method is used to count each character in the word given. It is all stored into an array in alphabetical order
    /**
     * This is the count method which is used to count the number of times a character occurs in a string.
     * This is done by breaking down the string into a char array and then comparing the alphabet to each character in the string.
     * @param word This is the encrypted message in question.
     * @return This will return a array containg ints which is just the number of times the letter appears in alphabetical order.
     */
    public static int[] count(String word) {

        // All variables that have been initialised and definied
        String newWord = word.toLowerCase(); // This makes the counting process much easier. This not affect overall message
        int lengthOfWord = word.length();
        int[] letterCounts = new int[26];
        char[] wordArray = newWord.toCharArray();
        
        // All variables that have been initialised but not defined required in code bellow
        char letter;
        int counter;


        // This loop is used for comparing every character in the string with the specific character currently being held in letter
        for (letter = 'a' , counter = 0; letter <= 'z'; letter++ , counter++) { 
            int number = 0;
            int index = 0;


            // Compares letter to each character in array
            while (index < lengthOfWord) {
                if (letter == wordArray[index]) {
                    number += 1;
                }
                index += 1;
            }

            // This will then store the number of times that letter has appeared in that array in alphabetical order
            letterCounts[counter] = number;


        }

        return(letterCounts);
    }


    // This method is used to calculate the frequency of each character in the word
    /**
     * This is the frequency method. It calculates the frequency of the characters that appear in the string. This is done by dividing the number of times it appears in the string by the length of the string.
     * The method fetches the number of times the letter appears from the count function, and then divides it by the length of the message itself.
     * @param word This is the encrypted message in question.
     * @return It will return an array of doubles which are the frequencies of the letters in the encrypted message.
     */ 
    public static double[] frequency(String word) {
        
        // All variables that have been initialised but not defined required in code bellow
        int i;

        // All variables that have been initialised and definied
        int[] letterCounts = count(word);
        double lengthOfString = word.length();
        double[] letterFreq = new double[26];
        int lengthOfArray = 26;
        double freq = 0;
        double currentNumber = 0;
        
        // This loop will calculate the frequency value of each character by dividing by the length of the word given and store it in an array in alphabetical order.
        for (i = 0; i < lengthOfArray; i++) {

            currentNumber = letterCounts[i];
            // Make sure before any mathematical equation is done, every value is in the same form as the output value. You can't do double/int.
            freq = (currentNumber/lengthOfString);
            letterFreq[i] = freq;

        }

        return(letterFreq);
    }


    // This method is used to calculate the chi-squared score of between two different frequencies
    /** 
     * This is the chiSquared method. This will calculate the chiSquared score of between two frequencies.
     * It recieves two arrays and compares them to see how similar they are.
     * @param english This will take in the frequency of the letters that appear in the english dictionary.
     * @param encrypted This will take in the frequency of letters that appear in the encrypted string.
     * @return It will return a double which is the Chi-Squared score.
     */
    public static double chiSquared(double[] english, double[] encrypted) {
        
        // encrypted is the frequency list of letters for cipherText
        // english is the frequency list of letters for normal english

        // All variables that have been initialised but not defined required in code bellow
        int i;

        // All variables that have been initialised and definied
        double chiSquared1 = 0;

        // Calculates Chi-Squared
        for (i = 0; i < 26; i++) {
            // All variables that have been initialised and definied
            double alphabetFreq = english[i];
            double encryptedFreq = encrypted[i];

            // All variables that have been initialised but not defined required in code bellow
            double topHalf;
            double topHalfSQ;
            double finalPT;

            // This splits the Chi-squared equation into more human readable chunks
            topHalf = (encryptedFreq - alphabetFreq);
            topHalfSQ = Math.pow(topHalf,2.0); 
            finalPT = (topHalfSQ/alphabetFreq);

            // Stores the sum of all values of the alphabet
            chiSquared1 += finalPT;


        }


        return(chiSquared1);

    }


}