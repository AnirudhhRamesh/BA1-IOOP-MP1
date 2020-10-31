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
		
		String s = "";
		
		for(int i = 0; i < bruteForceResult.length; ++i) {
			s += bytesToString(bruteForceResult[i]);
		    System.out.println(" ");;
		    }
		    
		
		
		//TODO : COMPLETE THIS METHOD
		
		return s; //TODO: to be modified
	}
	
	
	//-----------------------Caesar-------------------------
	
	/**
	 *  Method to decode a byte array  encoded using the Caesar scheme
	 * This is done by the brute force generation of all the possible options
	 * @param cipher the byte array representing the encoded text
	 * @return a 2D byte array containing all the possibilities
	 */
	public static byte[][] caesarBruteForce(byte[] cipher) {
		
		byte[][] cipherDecryptions = new byte[256][cipher.length];
		
		//TODO : COMPLETE THIS METHOD
		//Attempt breaking using every possible key (-128 to 127) and user manually reads through them all
		//TODO: Delete this code and rework, figure out how to use byte[][] to store byte values
		
		//Iterate through all values of keys (256 values)
		//Store these values inside a byte[][]
		//Convert to string and write string to bruteForceCaesar.txt
		//byte[] decoded = new byte[cipher.length];
		//	for(int i = 0; i < cipher.length; ++i) {
		//	decoded[i] = cipher[i]; 
	//}
		
		for(int i = 0; i < 256; ++i) {
			for(int j = 0; j < cipher.length; ++j) {
			cipherDecryptions[i][j] = Encrypt.caesar(cipher, (byte) (i - 128))[j];
			}
		}
			 
			
		
	//	String newLine = System.getProperty("line.separator");
   //  Helper.writeStringToFile("Brute Force Results" + newLine, "bruteForceCaesar.txt", true);

		
		
		
			
		//	System.out.println("Key: " + (i - 128) + " " + Helper.bytesToString(Encrypt.caesar(cipher, (byte)(i-128))));
		//	Helper.writeStringToFile("Key: " + (i - 128) + " " + Helper.bytesToString(Encrypt.caesar(cipher, (byte)(i-128))) + newLine, "bruteForceCaesar.txt");
			//Construct string
			//Helper.writeStringToFile(Helper.bytesToString(cipherDecryptions[i]) + lineBreak, "bruteForceCaesar.txt", true);
			
			return cipherDecryptions;
		}
		
		
		
	
	
	/**
	 * Method that finds the key to decode a Caesar encoding by comparing frequencies
	 * @param cipherText the byte array representing the encoded text
	 * @return the encoding key
	 */
	public static byte caesarWithFrequencies(byte[] cipherText) {				
		return caesarFindKey(computeFrequencies(cipherText)); //caesarFindKey using computeFrequencies
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
			//System.out.print((Helper.bytesToString(tempByteConverter)) + ": " + cipherFrequencies[i] + "   ");
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
		 * 	1. Create an iterator from 0 to 255 (inclusive => 256 values) /
		 * 	2. Multiply ENGLISHFREQUENCIES with A*0 (It. 0), A*1 (It. 1), ..., A*255 (It. 255) /
		 *  3. Create a wrap-around so if the charFreqIndex goes above 255, it wraps back to 0 /
		 *  4. Find the index of the biggest scalar product out of all scalar products => Error here: always returns 255
		 *  Distance between charFrequencies[i] and 97 is the key
		 */
		int iterationCounter = 0;
		int charFrequenciesIterator = 0;
		
		double[] scalarProducts = new double[charFrequencies.length];
		double maxScalarProduct = 0;
		int maxScalarProductIndex = 0;
		
		byte key = (byte)0;
		
		//System.out.println();
		
		for (int i = 0; i < charFrequencies.length; ++i) { //Iterates through all the values of charFrequencies (= 256 values) => Iterates 256 times.
			double scalarProduct = 0;
			
			for (int j = 0; j < ENGLISHFREQUENCIES.length; ++j) { //Iterates 26 times (through ENGLISH Frequencies.length
				
				scalarProduct += ENGLISHFREQUENCIES[j]*charFrequencies[charFrequenciesIterator]; //a*0 + b*1 + ... + z*25; a*1 + b*2 + ... + z*26; ... ; a*255 + b*0 + ... + z*24;
				if (charFrequenciesIterator == 255) { //Wrap-around function for the iterator
					charFrequenciesIterator = -1;
				}
				
				++charFrequenciesIterator;	
			}
			
			scalarProducts[i] = scalarProduct;
			++iterationCounter;
			charFrequenciesIterator = iterationCounter;
		}
		
		//System.out.println();
		//System.out.println();
		for (int i = 0; i < scalarProducts.length; ++i) { //Retrieves index of largest scalar product from the 256 computed scalar products.
			if (scalarProducts[i] > maxScalarProduct) {
				maxScalarProduct = scalarProducts[i];
				maxScalarProductIndex = i;
				//System.out.println(i + " : " + maxScalarProduct);  //TODO: Fix, this always returns i = 225 Why is this?
			}
		}
		int trueByte = maxScalarProductIndex - 128; //Since we stored the charFrequencies as (byte) cipherText[j] + 128, we must subtract the 128 to retrieve the original key
		int tempKey = (trueByte - 97); 
		
		if (tempKey < 0) {
			tempKey *= -1;
		}
		
		key = (byte)(tempKey);	//Calculates the absolute value (= distance between 'a'(97) and index)
		
		//System.out.println();
		//System.out.println("Max Scalar Product Index: " + maxScalarProductIndex);
		//System.out.println("keyValue = " + trueByte + " - " + "97" + " = " + key);
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
		//TODO: Arnie: Task 4
		//TODO : COMPLETE THIS METHOD
		/* Method used:
		 * 	1. Create an iterator from 0 to 255 (inclusive => 256 values) /
		 * 	2. Multiply ENGLISHFREQUENCIES with A*0 (It. 0), A*1 (It. 1), ..., A*255 (It. 255) /
		 *  3. Create a wrap-around so if the charFreqIndex goes above 255, it wraps back to 0 /
		 *  4. Find the index of the biggest scalar product out of all scalar products => Error here: always returns 255
		 *  Distance between charFrequencies[i] and 97 is the key
		 */
		
		List<Byte> cleanCipher = removeSpaces(cipher);
		int keyLength = vigenereFindKeyLength(cleanCipher);
		
		byte[] vigenereKey = Decrypt.vigenereFindKey(cleanCipher, keyLength);
		
		return vigenereKey; //TODO: to be modified
	}
	
	
	
	/**
	 * Helper Method used to remove the space character in a byte array for the clever Vigenere decoding
	 * @param array the array to clean
	 * @return a List of bytes without spaces
	 */
	public static List<Byte> removeSpaces(byte[] array){
		//TODO : COMPLETE THIS METHOD
		//Remove all spaces
		List<Byte> list = new ArrayList<Byte>();
		
		for (int i = 0; i < array.length; ++i) {
			//If array[i] is a space, will not append to the list
			if(Encrypt.spaceEncoder(false, array[i])) {
				list.add((byte)(array[i]));
			}
		}
		
		return list;
	}
	
	
	/**
	 * Method that computes the key length for a Vigenere cipher text.
	 * @param cipher the byte array representing the encoded text without space
	 * @return the length of the key
	 */
	public static int vigenereFindKeyLength(List<Byte> cipher) {
		assert (cipher.size() >= 6);
		//TODO : Arnie: Task 2
		/*
		 * Module 1:
		 *      -> Iterate through two strings, one that is cipher and cipherShifted(by i), while i < cipher.length -1
		 *      -> 
		 *      
		 * Module 2:
		 *      -> Identify local maxima of first half of list (use Math.ciel() for odd number list.lengths)
		 *      -> Store list of INDEXES in a ArrayList that is ORDERED
		 *      
		 * Module 3:
		 *      -> Use an associative table to find the length of the key
		 *      -> Calculate the distance between consecutive local indices of loc.maximas (biggerIndex - smallerIndex -> [i+1] - [i]
		 *      -> The distance that appears the most if that of the key length
		 */
		
		//----------MODULE 1----------     Calculating coincidences: WORKS
		List<Byte> cipherShifted = new ArrayList<Byte>();
		int[] coincidences = new int[cipher.size()];
		
		for (int i = 0; i < cipher.size(); ++i) {
			cipherShifted.add(cipher.get(i)); //duplicates the cipher
		}
		
		for (int i = 1; i < cipher.size(); ++i) {
			for (int j = 1; j < cipher.size() - i; ++j) { //Suffices to check for overlapping sequence areas, not for empty spaces
				if(cipher.get(j).equals(cipher.get(j+i))) {
					coincidences[i-1] += 1; //i - 1 = 0 (for initial loop)
					//System.out.println(coincidences[i-1]); WORKS
				} 
			}
			cipherShifted.add(0, (byte)(45)); //45 is a dash: -
		}
		
		//----------MODULE 2----------     Calculating possible key sizes: WORKS
		//TODO: Change maxIndex to int?
		int maxIndex = (int)Math.ceil(cipher.size()/2); //If cipher.size/2 = 7.5 => Math.ciel returns 8
		List<Integer> localMaximas = new ArrayList<Integer>(); //stores the indices of local Maximas
		
		for (int i = 0; i <= maxIndex; ++i) {
			//Local maxima of two values to the left and two values to the right
			
			//For 0: index 0 is loc.max if 0 > 1 & 0> 2
			if (i == 0) {
				//LocalMaxima module
				if (coincidences[i] > coincidences[i + 1] && coincidences[i] > coincidences[i + 2]) {
					localMaximas.add(i);
				}
			}
			//For 1: index 1 is loc.max if 1 > 0, 1 > 2 & 1 > 3
			else if (i == 1) {
				if (coincidences[i] > coincidences[i-1] && coincidences[i] > coincidences[i + 1] && 
				coincidences[i] > coincidences[i + 2]) {
					localMaximas.add(i);
				}
			}
			//For 2: index 2 is loc.max if 2 > 0, 2 > 1 | 2 > 3 & 2 > 4
			//-> Repeat the loop from here onwards
			else {
				if (coincidences[i] > coincidences[i-1] && coincidences[i] > coincidences[i-2] && 
						coincidences[i] > coincidences[i + 1] && coincidences[i] > coincidences[i + 2]) {
							localMaximas.add(i);
						}
			}	
		}
		
		//This returns an average index distance of 5 (CORRESPONDING CORRECTLY)
		/*for (int k = 0; k < localMaximas.size(); ++k) {
			System.out.print(localMaximas.get(k) + " ");
		}*/
		
		//----------MODULE 3----------     Calculating the keyArray.length: ERROR HERE?
		//TODO: Use a java associative table: To structure calculations. 
		
		//KEY = distance, VALUE = frequency
		Map<Integer, Integer> keyLengths = new HashMap<>();
		
		for (int i = 0; i < localMaximas.size() - 1; ++i) {
			if((i + 1) <= localMaximas.size()) {
				int distance = localMaximas.get(i + 1) - localMaximas.get(i); //TODO: IndexOutOfBounds
				//System.out.print("Index: " + distance);
				
				if (keyLengths.containsKey(distance)) { //TODO: NullPointerException here!
					keyLengths.replace(distance, keyLengths.get(distance) + 1); //Does +1 append an additional 1 to the function?
				}
				else {
					keyLengths.put(distance, 1);
				}
			}
			if (i == localMaximas.size() - 1) {
				int distance = localMaximas.get(i) - localMaximas.get(i - 1);
				keyLengths.put(distance, +1);
				
				//if (keyLengths.get(distance) != null || keyLengths.get(distance) != 0) {
				if(keyLengths.containsKey(distance)) {
					keyLengths.replace(distance, keyLengths.get(distance) + 1); //Does +1 append an additional 1 to the function?
				}
				else {
					keyLengths.put(distance, 1);
				}
			}
		}
		
		int tempMax = 0;
		int keyLength = 0;
		for (Map.Entry<Integer, Integer> pair : keyLengths.entrySet()) {
			//Select the most frequent integer
			if (tempMax < pair.getValue()) {
				tempMax = pair.getValue();
				keyLength = pair.getKey();
				//System.out.print("Distance: " + pair.getKey() + " => Frequency: " + pair.getValue()); //ERROR
			}			
		}
		
		System.out.println();
		System.out.println("Vigenere Freq. Profile Key Length: " + keyLength);
		
		return keyLength; //TODO: to be modified
	}

	
	
	/**
	 * Takes the cipher without space, and the key length, and uses the dot product with the English language frequencies 
	 * to compute the shifting for each letter of the key
	 * @param cipher the byte array representing the encoded text without space
	 * @param keyLength the length of the key we want to find
	 * @return the inverse key to decode the Vigenere cipher text
	 */
	public static byte[] vigenereFindKey(List<Byte> cipher, int keyLength) {
		/* TODO : Arnie: Task 3
		 * Recover the key values:
		 *      -> Divide the cipher by keyLength, into keyLength strings
		 *      -> Use caesarFindFrequency on each of these new strings created
		 *      -> Store the final keyarray into an array and return
		 */
		byte[] vigenereKeyArray = new byte[keyLength];
		
		for (int i = 0; i < keyLength; ++i) {
			List<Byte> subCipherArray = new ArrayList<Byte>();

			for (int j = i; j < cipher.size(); j += keyLength) {				
				subCipherArray.add(cipher.get(j));
			}
						
			byte[] subCipherText = new byte[subCipherArray.size()];//This will most likely produce an error
				
			for (int k = 0; k < subCipherArray.size(); ++k) {
				subCipherText[k] = subCipherArray.get(k);
			}
			vigenereKeyArray[i] = caesarWithFrequencies(subCipherText);
			
			System.out.print(vigenereKeyArray[i] + " ");
		}
		
		return vigenereKeyArray;
	}
	
	
	//-----------------------Basic CBC-------------------------
	
	/**
	 * Method used to decode a String encoded following the CBC pattern
	 * @param cipher the byte array representing the encoded text
	 * @param iv the pad of size BLOCKSIZE we use to start the chain encoding
	 * @return the clear text
	 */
	public static byte[] decryptCBC(byte[] cipher, byte[] iv) {
		
		int keyLength = iv.length;
		byte[] decoded = new byte[cipher.length];
		
		for(int i = 0; i < cipher.length; ++i) {
			decoded[i] = cipher[i]; 
			}
		
		for(int i = 0; i < keyLength; ++i) {
			decoded[i] = (byte) (decoded[i] ^ iv[i]); //TODO: is it not possible to directly use cipher[i] here?
			}
		
		for(int i = keyLength; i < decoded.length; ++i) {
			
			decoded[i] = (byte) (cipher[i] ^ cipher[i - keyLength]);
			
			
		} 
		
		
			
		return decoded;
			
		
		
		
		
		
		
		//TODO : COMPLETE THIS METHOD	
		//return null; //TODO: to be modified
	}
	
	
	

		
		
		
		
		
}
