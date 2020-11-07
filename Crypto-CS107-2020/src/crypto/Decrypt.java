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
		
		//TODO: COMPLETE THIS METHOD
		
		//The method should be such that if you call it with cipher and type, it will decrypt the message
		return null; //TODO: to be modified
	}
	
	
	/**
	 * Converts a 2D byte array to a String
	 * @param bruteForceResult a 2D byte array containing the result of a brute force method
	 */
	public static String arrayToString(byte[][] bruteForceResult) {
		String s = null;
		for(int i = 0; i < 256; ++i) {
			s += bytesToString(bruteForceResult[i]) + System.lineSeparator();
		}
			
		return s;
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
		//TODO: Delete this code and rework, figure out how to use byte[][] to store byte values
		
		//Iterate through all values of keys (256 values)
		//Store these values inside a byte[][]
		//Convert to string and write string to bruteForceCaesar.txt
		
		//String newLine = System.getProperty("line.separator");
		//Helper.writeStringToFile("Brute Force Results" + newLine, "bruteForceCaesar.txt", true);

		byte[][] cipherDecryptions = new byte[256][cipher.length];
		
		for (int i = 0; i < 256; ++i) {
				cipherDecryptions[i] = Encrypt.caesar(cipher, (byte) (i-128));
		
			//System.out.println("Key: " + (i - 128) + " " + Helper.bytesToString(Encrypt.caesar(cipher, (byte)(i-128))));
		//	Helper.writeStringToFile("Key: " + (i - 128) + " " + Helper.bytesToString(Encrypt.caesar(cipher, (byte)(i-128))) + newLine, "bruteForceCaesar.txt");
			//Construct string
			//Helper.writeStringToFile(Helper.bytesToString(cipherDecryptions[i]) + lineBreak, "bruteForceCaesar.txt", true);
			
			//TODO: Use arrayToString method to convert 2D byte array into String, then save to txt file
		}
		
		
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

		/*For loop to print out the character frequencies into console
		for (int i = 0; i < cipherFrequencies.length; ++i) {
			cipherFrequencies[i] /= (cipherText.length - spaceCounter); //Calculating ratio of frequency (excluding spaces in calculation
			byte[] tempByteConverter = {((byte)(i - 128))};
			System.out.print((Helper.bytesToString(tempByteConverter)) + ": " + cipherFrequencies[i] + "   ");
		}
		 */
		
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
		
		for (int i = 0; i < scalarProducts.length; ++i) { //Retrieves index of largest scalar product from the 256 computed scalar products.
			if (scalarProducts[i] > maxScalarProduct) {
				maxScalarProduct = scalarProducts[i];
				maxScalarProductIndex = i;
			}
		}
		int trueByte = maxScalarProductIndex - 128; //Since we stored the charFrequencies as (byte) cipherText[j] + 128, we must subtract the 128 to retrieve the original key
		int tempKey = (trueByte - 97); 
		
		if (tempKey < 0) {
			tempKey *= -1;
		}
		
		key = (byte)(tempKey);	//Calculates the absolute value (= distance between 'a'(97) and index)
		
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
		
        byte[][] cipherDecryptions = new byte[256][cipher.length];
		
		for (int i = 0; i < 256; ++i) {
				cipherDecryptions[i] = Encrypt.xor(cipher, (byte) (i-128));
		}
		
		//TODO : COMPLETE THIS METHOD
		//Attempt breaking using every possible key (-128 to 127) and user manually reads through them all

		return cipherDecryptions; //TODO: to be modified
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
		
		return vigenereKey;
	}
	
	
	
	/**
	 * Helper Method used to remove the space character in a byte array for the clever Vigenere decoding
	 * @param array the array to clean
	 * @return a List of bytes without spaces
	 */
	public static List<Byte> removeSpaces(byte[] array){

		List<Byte> list = new ArrayList<Byte>();
		
		for (int i = 0; i < array.length; ++i) {
			if(Encrypt.spaceEncoder(false, array[i])) { //If array[i] is a space, will not append to the list
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
		/* Method used:
		 * Module 1:
		 *      -> Iterate through two strings, one that is cipher and cipherShifted(by i), while i < cipher.length -1
		 *      -> Count the number of character coincidences between the cipher and the shifted cipher, store in int[] array
		 *      
		 * Module 2:
		 *      -> Identify local maxima of first half of list (use Math.ciel() for odd number list.lengths)
		 *      -> Store list of INDICES in a ArrayList that is ORDERED
		 *      
		 * Module 3:
		 *      -> Use an associative table to find the length of the key:
		 *      => Calculate the distance between consecutive local indices of loc.maximas (biggerIndex - smallerIndex -> [i+1] - [i]
		 *      => The distance that appears the most if that of the key length
		 */
		
		int[] coincidences = calculateCoincidences(cipher); //Module 1
		List<Integer> localMaximas = calculateMaximas(cipher, coincidences); //Module 2
		int keyLength = calculateKeyLength(localMaximas); //Module 3
		
		return keyLength;
	}

	
	
	/**
	 * Takes the cipher without space, and the key length, and uses the dot product with the English language frequencies 
	 * to compute the shifting for each letter of the key
	 * @param cipher the byte array representing the encoded text without space
	 * @param keyLength the length of the key we want to find
	 * @return the inverse key to decode the Vigenere cipher text
	 */
	public static byte[] vigenereFindKey(List<Byte> cipher, int keyLength) {
		/* Method used:
		 * Recover the key values:
		 *      -> Divide the cipher by keyLength, into keyLength strings
		 *      -> Use caesarFindFrequency on each of these new strings created
		 *      -> Store the final keyarray into an array and return
		 */
		byte[] vigenereKeyArray = new byte[keyLength];
		
		for (int i = 0; i < keyLength; ++i) {
			List<Byte> subCipherArray = new ArrayList<Byte>();

			//Divide the cipher array into keyLength # of sub arrays
			for (int j = i; j < cipher.size(); j += keyLength) {				
				subCipherArray.add(cipher.get(j));
			}
			
			//Convert the dynamic subCipherArray into a static byte[] subCipherText array			
			byte[] subCipherText = new byte[subCipherArray.size()];
			
			for (int k = 0; k < subCipherArray.size(); ++k) {
				subCipherText[k] = subCipherArray.get(k);
			}
			
			//Calculate the byte key value for each of the sub arrays and store inside the vigenere key Array
			vigenereKeyArray[i] = caesarWithFrequencies(subCipherText);
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
	}
	
	
	

	//-----------------------Additional Methods-------------------------

	/**
	 * Method used to calculate the # of coincidences between the original cipher & cipher shifted by from i to (cipher.length - i)
	 * @param cipher the byte array representing the encoded text without space
	 * @return the int[] array which contains the # of coincidences, indexed with the # of spots that cipherShifted is shifted
	 */
	public static int[]	calculateCoincidences (List<Byte> cipher) {
		
		List<Byte> cipherShifted = new ArrayList<Byte>();
		int[] coincidences = new int[cipher.size()];
		
		for (int i = 0; i < cipher.size(); ++i) {
			cipherShifted.add(cipher.get(i)); //duplicates the cipher
		}
		
		for (int i = 1; i < cipher.size(); ++i) {
			for (int j = 1; j < cipher.size() - i; ++j) { //Suffices to check for overlapping sequence areas, not for empty spaces
				if(cipher.get(j).equals(cipher.get(j+i))) {
					coincidences[i-1] += 1; //i - 1 = 0 (for initial loop)
				} 
			}
			cipherShifted.add(0, (byte)(45)); //45 is a dash: -
		}
		
		return coincidences;
	}
		
	/**
	 * Method used to calculate the index of the local maximas from an int array of coincidences
	 * @param cipher the byte array representing the encoded text without space
	 * @param coincidences the int array containing the number of character coincidences, with index = cipherShifted sequence iteration
	 * @return the Integer dynamic array containing the indices of all the localMaximas (in ascending index order)
	 */
	public static List<Integer> calculateMaximas(List<Byte> cipher, int[] coincidences){
		int maxIndex = (int)Math.ceil(cipher.size()/2); //If cipher.size/2 = 7.5 => Math.ciel returns 8
		List<Integer> localMaximas = new ArrayList<Integer>(); //stores the indices of local Maximas
		
		for (int i = 0; i <= maxIndex; ++i) {
			
			//For i = 0: index 0 is loc.max if 0 > 1 & 0 > 2
			if (i == 0) {
				if (coincidences[i] > coincidences[i + 1] && coincidences[i] > coincidences[i + 2]) {
					localMaximas.add(i);
				}
			}
			
			//For i = 1: index 1 is loc.max if 1 > 0, 1 > 2 & 1 > 3
			else if (i == 1) {
				if (coincidences[i] > coincidences[i-1] && coincidences[i] > coincidences[i + 1] && 
				coincidences[i] > coincidences[i + 2]) {
					localMaximas.add(i);
				}
			}
			
			//For i >= 2: index 2 is loc.max if 2 > 0, 2 > 1 | 2 > 3 & 2 > 4
			//-> Repeat the loop from here onwards
			else {
				if (coincidences[i] > coincidences[i-1] && coincidences[i] > coincidences[i-2] && 
						coincidences[i] > coincidences[i + 1] && coincidences[i] > coincidences[i + 2]) {
					localMaximas.add(i);
				}
			}	
		}
		return localMaximas;
	}
	
	/**
	 * Method used to calculate the vigenere key array length used to encode a cipher text, given the dynamic integer array of local maximas
	 * @param localMaximas the dynamic Integer array containing the indices of all the local maximas of the character coincidences
	 * @return the key length of the vigenere key array
	 */
	public static int calculateKeyLength(List<Integer> localMaximas) {
		Map<Integer, Integer> keyLengths = new HashMap<>();
		
		for (int i = 0; i < localMaximas.size() - 1; ++i) {
			if((i + 1) <= localMaximas.size()) {
				int distance = localMaximas.get(i + 1) - localMaximas.get(i);
				
				if (keyLengths.containsKey(distance)) {
					keyLengths.replace(distance, keyLengths.get(distance) + 1);
				}
				else {
					keyLengths.put(distance, 1);
				}
			}
			if (i == localMaximas.size() - 1) {
				int distance = localMaximas.get(i) - localMaximas.get(i - 1);
				keyLengths.put(distance, +1);
				
				if(keyLengths.containsKey(distance)) {
					keyLengths.replace(distance, keyLengths.get(distance) + 1);
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
			}			
		}
		
		return keyLength;
	}
		
		
		
}
