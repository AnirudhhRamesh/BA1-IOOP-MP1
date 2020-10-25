package crypto;

import java.util.Random;
import static crypto.Helper.*;

public class Encrypt {
	
	public static final int CAESAR = 0;
	public static final int VIGENERE = 1;
	public static final int XOR = 2;
	public static final int ONETIME = 3;
	public static final int CBC = 4; 
	
	public static final byte SPACE = 32;
	
	public static final int BYTE_RANGE = 256;
	final static Random rand = new Random();
	
	//-----------------------General-------------------------
	
	/**
	 * General method to encode a message using a key, you can choose the method you want to use to encode.
	 * @param message the message to encode already cleaned
	 * @param key the key used to encode
	 * @param type the method used to encode : 0 = Caesar, 1 = Vigenere, 2 = XOR, 3 = One time pad, 4 = CBC
	 * 
	 * @return an encoded String
	 * if the method is called with an unknown type of algorithm, it returns the original message
	 */
	public static String encrypt(String message, String key, int type) {
		// TODO: COMPLETE THIS METHOD
		byte[] plainText = Helper.stringToBytes(message);
		assert(plainText != null);
		
		byte[] keyBytes = Helper.stringToBytes(key);
		byte keyByte = keyBytes[0];
		
		while ((type < 0)||(type > 4)){
			System.out.println("False input. Please enter a type that is within the range of 0-4: 0 = Caesar; 1 = Vigenere; 2 = XOR; 3 = One-time pad; 4 = CBC;");
		}
		if (type == CAESAR){
			caesar(plainText, keyByte); //By default, space encoding is off for Caesar
		}
		else if (type == VIGENERE) {
			//By default, space encoding is OFF for Vigenere
		}
		else if (type == XOR) {
			//By default, space encoding is OFF for XOR
		}
		else if (type == ONETIME) {
			//By default, space encoding is ON for Onetimepad
		}
		else if (type == CBC) {
			//By default, space encoding is ON for CBC
		}
		return null; // TODO: to be modified
	}
	
	
	//-----------------------Caesar-------------------------
	
	/**
	 * Method to encode a byte array message using a single character key
	 * the key is simply added to each byte of the original message
	 * @param plainText The byte array representing the string to encode
	 * @param key the byte corresponding to the char we use to shift
	 * @param spaceEncoding if false, then spaces are not encoded
	 * @return an encoded byte array
	 */
	public static byte[] caesar(byte[] plainText, byte key, boolean spaceEncoding) {
		assert(plainText != null); //assert => if plaintext is null, then the method will stop running
		
		key = keyModulo(key);

		for (int i = 0; i < plainText.length; ++i) {
			//if spaces should not be encoded, checks for non space characters and shifts by key
			
			if (isSpace(spaceEncoding, plainText[i])){ 
				plainText[i] = (byte)(plainText[i] + key);
			}
			
			System.out.print(plainText[i] + " ");
		}
		System.out.println();

		return plainText;
	}
	
	/**
	 * Method to encode a byte array message  using a single character key
	 * the key is simply added  to each byte of the original message
	 * spaces are not encoded
	 * @param plainText The byte array representing the string to encode
	 * @param key the byte corresponding to the char we use to shift
	 * @return an encoded byte array
	 */
	public static byte[] caesar(byte[] plainText, byte key) {
		return caesar(plainText, key, false); //By default, space will not be encoded for caesar
	}
	
	//-----------------------XOR-------------------------
	
	/**
	 * Method to encode a byte array using a XOR with a single byte long key
	 * @param plaintext the byte array representing the string to encode
	 * @param key the byte we will use to XOR
	 * @param spaceEncoding if false, then spaces are not encoded
	 * @return an encoded byte array
	 */
	public static byte[] xor(byte[] plainText, byte key, boolean spaceEncoding) {
		// TODO: COMPLETE THIS METHOD
		return null; // TODO: to be modified
	}
	/**
	 * Method to encode a byte array using a XOR with a single byte long key
	 * spaces are not encoded
	 * @param key the byte we will use to XOR
	 * @return an encoded byte array
	 */
	public static byte[] xor(byte[] plainText, byte key) {
		// TODO: COMPLETE THIS METHOD
		return null; // TODO: to be modified
	}
	//-----------------------Vigenere-------------------------
	
	/**
	 * Method to encode a byte array using a byte array keyword
	 * The keyword is repeated along the message to encode
	 * The bytes of the keyword are added to those of the message to encode
	 * @param plainText the byte array representing the message to encode
	 * @param keyword the byte array representing the key used to perform the shift
	 * @param spaceEncoding if false, then spaces are not encoded
	 * @return an encoded byte array 
	 */
	public static byte[] vigenere(byte[] plainText, byte[] keyword, boolean spaceEncoding) {		
		int iteratorIndex = 0;
		
		for (int i = 0; i < plainText.length; ++i) {
			byte[] plainTextContainer = {plainText[i]};			
			plainText[i] = caesar(plainTextContainer, keyword[iteratorIndex], spaceEncoding)[0];

			if (isSpace(spaceEncoding, plainText[i])) {
				++iteratorIndex;
			}
			
			if (iteratorIndex == keyword.length){
				iteratorIndex = 0;
			}
		}
		
		return plainText;
	}
	
	/**
	 * Method to encode a byte array using a byte array keyword
	 * The keyword is repeated along the message to encode
	 * spaces are not encoded
	 * The bytes of the keyword are added to those of the message to encode
	 * @param plainText the byte array representing the message to encode
	 * @param keyword the byte array representing the key used to perform the shift
	 * @return an encoded byte array 
	 */
	public static byte[] vigenere(byte[] plainText, byte[] keyword) {
		return vigenere(plainText, keyword, false); //By default, space will not be encoded for caesar
	}
	
	
	
	//-----------------------One Time Pad-------------------------
	
	/**
	 * Method to encode a byte array using a one time pad of the same length.
	 *  The method  XOR them together.
	 * @param plainText the byte array representing the string to encode
	 * @param pad the one time pad
	 * @return an encoded byte array
	 */
	public static byte[] oneTimePad(byte[] plainText, byte[] pad) {
		// TODO: COMPLETE THIS METHOD
		return null; // TODO: to be modified
	}
	
	
	
	
	//-----------------------Basic CBC-------------------------
	
	/**
	 * Method applying a basic chain block counter of XOR without encryption method. Encodes spaces.
	 * @param plainText the byte array representing the string to encode
	 * @param iv the pad of size BLOCKSIZE we use to start the chain encoding
	 * @return an encoded byte array
	 */
	public static byte[] cbc(byte[] plainText, byte[] iv) {
		// TODO: COMPLETE THIS METHOD
		
		return null; // TODO: to be modified
	}
	
	
	/**
	 * Generate a random pad/IV of bytes to be used for encoding
	 * @param size the size of the pad
	 * @return random bytes in an array
	 */
	public static byte[] generatePad(int size) {
		// TODO: COMPLETE THIS METHOD

		return null; // TODO: to be modified

	}
	
	//-----------------------Additional Methods-------------------------

	
	/**
	 * Method used to avoid space encoding where necessary. If encoding spaces, then returns true. Else if not encoding spaces, returns false if character is a space.
	 * @param spaceEncoding if false, then spaces are not encoded
	 * @param byteValue is the byte that is checked to see whether it represents a space (byte = 32)
	 */
	public static boolean isSpace(boolean spaceEncoding, byte byteValue) {
		if (!spaceEncoding) {
			return !(byteValue == (byte)(32));
		}
		return true;
	}
	
	/**
	 * Method used to modulo (%) the key by the BYTE_RANGE which is 256
	 * @param key is the value that the character(s) will be shifted
	 */
	public static byte keyModulo(byte key) {
		return (byte)(key%BYTE_RANGE);
	}
		
}
