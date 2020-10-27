package crypto;

import static crypto.Helper.bytesToString;
import static crypto.Helper.stringToBytes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decrypt {
	
	
	public static final int ALPHABETSIZE = Byte.MAX_VALUE - Byte.MIN_VALUE + 1 ; //256
	public static final int APOSITION = 97 + ALPHABETSIZE/2; 
	
	//source : https://en.wikipedia.org/wiki/Letter_frequency
	public static final double[] ENGLISHFREQUENCIES = {0.08497,0.01492,0.02202,0.04253,0.11162,0.02228,0.02015,0.06094,0.07546,0.00153,0.01292,0.04025,0.02406,0.06749,0.07507,0.01929,0.00095,0.07587,0.06327,0.09356,0.02758,0.00978,0.0256,0.0015,0.01994,0.00077};
	
	/**
	 * Method to break a string encoded with different types of cryptosystems
	 * @param type the integer representing the method to break : 0 = Caesar, 1 = Vigenere, 2 = XOR
	 * @return the decoded string or the original encoded message if type is not in the list above.
	 */
	public static String breakCipher(String cipher, int type) {
		//TODO : COMPLETE THIS METHOD
		
		//The method should be such that if you call it with cipher and type, it will decrypt the message
		return null; //TODO: to be modified
	}
	
	
	/**
	 * Converts a 2D byte array to a String
	 * @param bruteForceResult a 2D byte array containing the result of a brute force method
	 */
	public static String arrayToString(byte[][] bruteForceResult) {
		//TODO : COMPLETE THIS METHOD
		
		return null; //TODO: to be modified
	}
	
	
	//-----------------------Caesar-------------------------
	
	/**
	 *  Method to decode a byte array  encoded using the Caesar scheme
	 * This is done by the brute force generation of all the possible options
	 * @param cipher the byte array representing the encoded text
	 * @return a 2D byte array containing all the possibilities
	 */
	public static byte[][] caesarBruteForce(byte[] cipher) {
		//TODO : COMPLETE THIS METHOD

		//Attempt breaking using every possible key (-128 to 127) and user manually reads through them all
		
		return null; //TODO: to be modified
	}	
	
	
	/**
	 * Method that finds the key to decode a Caesar encoding by comparing frequencies
	 * @param cipherText the byte array representing the encoded text
	 * @return the encoding key
	 */
	public static byte caesarWithFrequencies(byte[] cipherText) {				
		return caesarFindKey(computeFrequencies(cipherText));
	}
	
	/**
	 * Method that computes the frequencies of letters inside a byte array corresponding to a String
	 * @param cipherText the byte array 
	 * @return the character frequencies as an array of float
	 */
	public static float[] computeFrequencies(byte[] cipherText) {
		float[] cipherFrequencies = new float[256]; //ignoring spaces, but range remains unchanged
		int spaceCounter = 0;
		
		for (int i = 0; i < cipherFrequencies.length; ++i) { //Collect frequencies of characters based on byte value index
			for (int j = 0; j < cipherText.length; ++j) { //Iterate through all characters of the cipherTable
				if ((cipherText[j] + 128) == i) {
					if (cipherText[j] != 32) {
						++cipherFrequencies[i];
					}
					else {
						++spaceCounter;
					}
				}
			}
		}

		//For loop to print out the character frequencies into console
		for (int i = 0; i < cipherFrequencies.length; ++i) {
			cipherFrequencies[i] /= (cipherText.length - spaceCounter); //Calculating ratio of frequency (excluding spaces in calculation
			byte[] tempByteConverter = {((byte)(i - 128))};
			System.out.print((Helper.bytesToString(tempByteConverter)) + ": " + cipherFrequencies[i] + "   ");
		}

		return cipherFrequencies;
	}
	
	
	/**
	 * Method that finds the key used by a  Caesar encoding from an array of character frequencies
	 * @param charFrequencies the array of character frequencies
	 * @return the key
	 */
	public static byte caesarFindKey(float[] charFrequencies) {
		/* Method used:
		 * 	1. Create an iterator from 0 to 255 (inclusive => 256 values)
		 * 	2. Multiply ENGLISHFREQUENCIES with A*0 (It. 0), A*1 (It. 1), ..., A*255 (It. 255)
		 *  3. Create a wrap-around so if the charFreqIndex goes above 255, it wraps back to 0
		 *  4. Find the index of the biggest scalar product out of all scalar products
		 *  Distance between charFrequencies[i] and 97 is the key
		 */
		int iterationCounter = 0;
		int charFrequenciesIterator = 0;
		
		double[] scalarProducts = new double[charFrequencies.length];
		double maxScalarProduct = 0;
		int maxScalarProductIndex = 0;
		
		byte key = (byte)0;
		
		System.out.println();
		
		for (int i = 0; i < charFrequencies.length; ++i) { //Iterates through all the values of charFrequencies (= 256 values) => Iterates 256 times.
			double scalarProduct = 0;
			
			for (int j = 0; j < ENGLISHFREQUENCIES.length; ++j) { //Iterates 26 times (through ENGLISH Frequencies.length
				
				//System.out.println(ENGLISHFREQUENCIES.length);
				//System.out.print(ENGLISHFREQUENCIES[j] + " * " + charFrequencies[charFrequenciesIterator] + " + "); //26 iterations here
				//System.out.println("Scalar Product: " + ENGLISHFREQUENCIES[j] + " * " + charFrequencies[charFrequenciesIterator] + " = " + (ENGLISHFREQUENCIES[j]*charFrequencies[charFrequenciesIterator]));

				scalarProduct += ENGLISHFREQUENCIES[j]*charFrequencies[charFrequenciesIterator]; //a*0 + b*1 + ... + z*25; a*1 + b*2 + ... + z*26; ... ; a*255 + b*0 + ... + z*24;
				if (charFrequenciesIterator == 255) { //Wrap-around function for the iterator
					charFrequenciesIterator = -1;
				}
				
				++charFrequenciesIterator;	
				//System.out.println();
			}
			
			//System.out.println();
			//System.out.print(charFrequenciesIterator);
			
			scalarProducts[i] = scalarProduct;
			//System.out.println();
			//System.out.println("Scalar Products Array length: " + scalarProducts.length); //scalarProducts length is 256
			//System.out.print("Scalar Product: index " + i + " : " + scalarProducts[i]);

			++iterationCounter;
			charFrequenciesIterator = iterationCounter;
		}
		
		System.out.println();
		System.out.println();
		for (int i = 0; i < scalarProducts.length; ++i) { //Retrieves index of largest scalar product from the 256 computed scalar products.
			if (scalarProducts[i] > maxScalarProduct) {
				maxScalarProduct = scalarProducts[i];
				maxScalarProductIndex = i;
				System.out.println(i + " : " + maxScalarProduct);  //TODO: Fix, this always returns i = 225 Why is this?
			}
		}
		int tempKey = (97 - maxScalarProductIndex); //TODO Remove the - as it is just used to check absolute value method
		
		if (tempKey < 0) {
			tempKey *= -1;
		}
		
		key = (byte)(tempKey);	//Calculates the absolute value (= distance between 'a'(97) and index)
		
		System.out.println();
		System.out.println("Max Scalar Product Index: " + maxScalarProductIndex);
		System.out.println("keyValue = 97 - " + maxScalarProductIndex + " = " + key);
		return key;
	}
	
	
	
	//-----------------------XOR-------------------------
	
	/**
	 * Method to decode a byte array encoded using a XOR 
	 * This is done by the brute force generation of all the possible options
	 * @param cipher the byte array representing the encoded text
	 * @return the array of possibilities for the clear text
	 */
	public static byte[][] xorBruteForce(byte[] cipher) {
		//TODO : COMPLETE THIS METHOD
		//Attempt breaking using every possible key (-128 to 127) and user manually reads through them all

		return null; //TODO: to be modified
	}
	
	
	
	//-----------------------Vigenere-------------------------
	// Algorithm : see  https://www.youtube.com/watch?v=LaWp_Kq0cKs	
	/**
	 * Method to decode a byte array encoded following the Vigenere pattern, but in a clever way, 
	 * saving up on large amounts of computations
	 * @param cipher the byte array representing the encoded text
	 * @return the byte encoding of the clear text
	 */
	public static byte[] vigenereWithFrequencies(byte[] cipher) {
		//TODO : COMPLETE THIS METHOD
		
		//Arnie
		return null; //TODO: to be modified
	}
	
	
	
	/**
	 * Helper Method used to remove the space character in a byte array for the clever Vigenere decoding
	 * @param array the array to clean
	 * @return a List of bytes without spaces
	 */
	public static List<Byte> removeSpaces(byte[] array){
		//TODO : COMPLETE THIS METHOD
		return null;
	}
	
	
	/**
	 * Method that computes the key length for a Vigenere cipher text.
	 * @param cipher the byte array representing the encoded text without space
	 * @return the length of the key
	 */
	public static int vigenereFindKeyLength(List<Byte> cipher) {
		//TODO : Arnie
		
		
		return -1; //TODO: to be modified
	}

	
	
	/**
	 * Takes the cipher without space, and the key length, and uses the dot product with the English language frequencies 
	 * to compute the shifting for each letter of the key
	 * @param cipher the byte array representing the encoded text without space
	 * @param keyLength the length of the key we want to find
	 * @return the inverse key to decode the Vigenere cipher text
	 */
	public static byte[] vigenereFindKey(List<Byte> cipher, int keyLength) {
		//TODO : Arnie
		
		
		return null; //TODO: to be modified
	}
	
	
	//-----------------------Basic CBC-------------------------
	
	/**
	 * Method used to decode a String encoded following the CBC pattern
	 * @param cipher the byte array representing the encoded text
	 * @param iv the pad of size BLOCKSIZE we use to start the chain encoding
	 * @return the clear text
	 */
	public static byte[] decryptCBC(byte[] cipher, byte[] iv) {
		//TODO : COMPLETE THIS METHOD	
		return null; //TODO: to be modified
	}
	
	
	

		
		
		
		
		
}
