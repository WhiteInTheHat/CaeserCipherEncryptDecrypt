/**
 * This Class is used to carry out Caesar Cipher.
 * It has 3 methods.
 * The first method is the main method where the JDK runs first.
 * The second method is the rotating of a single character to encrypt it.
 * The last method is the rotating of a string to encrypt it.
 */
public class Caesar {



    // The main method that the JDK compiler will read and run first (Everything works from the main method)
    /**
     * This is the main section of the code where all functions necessary for proper encryption occurs.
     * It also checks for if a valid number of arguments are entered and if incorrect arguments or lack of arguements are entered it will return valid errors.
     * This is done in the catch section.
     * Where as the main piece of code is in the try section under the else statement.
     * @param args The first argument will take in the amount of shifts that will occur to the alphabet. The second argument will take in the string itself that is to be shifted.
     */
	public static void main(String[] args) {
		
		
		// This try section is used to determine if too few parameters are entered or if the value entered is not a number if the value entered for key is a letter for example
		try {

			// Declaring variables
			int key = Integer.parseInt(args[0]);
			String phrase = args[1];
			int numberOfParameters = args.length;


			// This helps achieve a key that is within the -26 <= 0 <= 26
			// If key is already within the range it will remain unchanged
			if (key > 26) {
				key = key % 26;
				System.out.println(key);
			}
			else if (key < -26) {
				key = key % 26;
				System.out.println(key);
			}

			// This is used to stop the code from running if more than 2 parameters are entered
			if (numberOfParameters > 2) {
				System.out.println("Too many parameters! ");
				System.out.println("Usage: java Caesar n \"cipher text\"");
			}

			// This is where the main function occurs
			else {

				//Write main code
				System.out.println(rotate(key,phrase));

			}
	

		} catch(Exception few){ // Catches the error and returns valid outputs for certain errors

			int numberOfParameters = args.length;

			if (numberOfParameters < 2) {
				System.out.println("Too few parameters! ");
				System.out.println("Usage: java Caesar n \"cipher text\"");
			}

			else {
				System.out.println("Key is not a number! ");
				System.out.println("Usage: java Caesar n \"cipher text\"");
			}

		}	

	}


	/**
	 * This setion of code gets a single character and then converts it into an encrypted character if it fits the right criteria.
	 * First we check if the character itself is a letter, if it is not a letter just return the character.
	 * If the character is a letter we then check if it is a lowercase letter or an uppercase letter and return the encrypted letter either in lowercase or uppercase.
	 * The section of code uses ASCII to get the encrypted characters, so there is precaution to make sure that the character stays within the range of 'a' to 'z' after the shift.
	 * @param shift this by how much the character is to be shifted by in the new alphabet.
	 * @param letter this is the character that is being put into the function to be worked on.
	 * @return This function will return the encrypted letter.
	 */
	public static char rotate(int shift, char letter) {


		// All required definitions
		char encryptedLetter;

		// Outer if statement checks if the character is a letter, if not just return the character
		// Inner if statement checks what type of letter it is (Caps/Uncaps) and if the letter exceeds the ascii value then it will go back on itself and return the correct character.
		if (Character.isLetter(letter) == true) {
			
			// Forces it to be a character
			encryptedLetter = (char) (letter + shift);

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
		// Returns value of encrypted letter
		return(encryptedLetter);
	}




	// This function loops the string into the other rotate function and then returns the encrypted string itself.
	/**
	 * This section of code gets a string and loops through that string where every loop a single character is put into the above rotate function to return an encrypted character.
	 * In short this uses looping and another function to encrypt the entire string.
	 * The returned value from the function is then stored in encryptedText where it is later on returned by the function.
	 * @param shift This is the number of times the alphabet is shifted.
	 * @param word This is the string that is to be encrypted.
	 * @return This will return the whole encrypted string.
	 */
	public static String rotate(int shift, String word) {

		// All required definitions
		int lengthOfString = word.length();
		char[] wordArray = word.toCharArray();   // Array is required to submit each character into the function
		char character;
		String encryptedText = "";

		// Loop putting all values into the first rotate function
		for (int i = 0; i < lengthOfString; i++) {
			character = wordArray[i];
			// Stores all letters that have been encrypted in this string
			encryptedText = encryptedText + rotate(shift,character);


		}


		return(encryptedText);
	}


}