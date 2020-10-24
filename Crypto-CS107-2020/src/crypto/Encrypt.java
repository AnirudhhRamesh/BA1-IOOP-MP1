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
		
		while ((type < 0)||(type > 4)){
			System.out.println("Please enter a type that is within the range of 0-4: 0 = Caesar; 1 = Vigenere; 2 = XOR; 3 = One-time pad; 4 = CBC;");
		}
		if (type == CAESAR){
			//Caesar
		}
		else if (type == VIGENERE) {
			//Vigenere
		}
		else if (type == XOR) {
			//XOR
		}
		else if (type == ONETIME) {
			//One time pad
		}
		else if (type == CBC) {
			//CBC
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
		
		
		byte keyModulo = (byte)(key%256); //256 => Number of characters possible encoded
		// TODO: COMPLETE THIS METHOD
		
		//do KEY mod 256 = KEY?
		//Add KEY to each character =>
		
		if (!spaceEncoding) {
			//Modify such that space does not get encoded
		}
		System.out.println("Key:" + key);
		System.out.println("Key modulo:" + (key%256));
		System.out.println("Original Byte String: ");
		
		for (int val: plainText) {
			System.out.print(plainText[val] + " ");
			//plainText[val] = (byte)(plainText[val] + key);
			
			//TODO: Make "WRAPAROUND" into a method
			
			//plainText[val] = 67;

			/*if ((plainText[val] + keyModulo <= 127)||(plainText[val] + keyModulo >= -128)||(plainText[val]!= 32)){
				plainText[val] += keyModulo;
			}*/
		}
		System.out.println(" ");
		
		System.out.println("Encoded Byte String");
		for (int i = 0; i < plainText.length; ++i) {
			plainText[i] = (byte)(plainText[i] + key);
			System.out.print(plainText[i] + " ");
		}
		
		System.out.println("");

		//Range is from -128 to 127
				
		return plainText; // TODO: to be modified
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
		// TODO: COMPLETE THIS METHOD		
		return null; // TODO: to be modified
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
		// TODO: COMPLETE THIS METHOD
		return null; // TODO: to be modified
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
	 * Method used to wrap the number between the range -128 to 127 when shifting from left to right
	 * @param byteValue is the byte that must be shifted by the value of key
	 * @key is the key that determines the value of the shift
	 */
	
	public static byte wrapKeyShift(byte byteValue, byte key) {
		
		//Byte encoding range: -128 to 127
		
		//Does the bytes automatically wrap themselves or no?
		//If yes, then this function is unnecessary
		
		return (byte)3; //TODO: to be modified
	}
	
	
	
}
