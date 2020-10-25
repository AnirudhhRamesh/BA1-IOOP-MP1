package crypto;

import static crypto.Helper.cleanString;
import static crypto.Helper.stringToBytes;
import static crypto.Helper.bytesToString;

/*
 * Part 1: Encode (with note that one can reuse the functions to decode)
 * Part 2: bruteForceDecode (caesar, xor) and CBCDecode
 * Part 3: frequency analysis and key-length search
 * Bonus: CBC with encryption, shell
 */
public class Main {
	
	
	//---------------------------MAIN---------------------------
	public static void main(String args[]) {
		
		
		String inputMessage = Helper.readStringFromFile("text_one.txt");
		String key = "2cF%5"; //Value shift is 50
		
		String messageClean = cleanString(inputMessage);
		
		
		byte[] messageBytes = stringToBytes(messageClean);
		byte[] keyBytes = stringToBytes(key);
		
		
		System.out.println("Original input sanitized : " + messageClean);
		System.out.println();
		
		System.out.println("------Caesar------");
		testCaesar(messageBytes, keyBytes[0]);
		testCaesar(Helper.stringToBytes("bonne journée"), (byte)3);
		
		System.out.println("------Vigenere------");
		testVigenere(messageBytes, keyBytes);
		
		byte[] vigenereTempKeyTester = {(byte) 1, (byte) 2, (byte) 3};
		testVigenere(Helper.stringToBytes("bonne journée"), vigenereTempKeyTester);

		// TODO: TO BE COMPLETED
	}
	
	
	//Run the Encoding and Decoding using the caesar pattern 
	public static void testCaesar(byte[] string , byte key) {		
		//Encoding
		byte[] result = Encrypt.caesar(string, key);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);
		System.out.println();

		//Decoding with key
		String sD = bytesToString(Encrypt.caesar(result, (byte) (-key)));
		System.out.println("Decoded knowing the key : " + sD);
		
		//TODO: BruteForce decoding
		/*
		//Decoding without key
		byte[][] bruteForceResult = Decrypt.caesarBruteForce(result);
		String sDA = Decrypt.arrayToString(bruteForceResult);
		Helper.writeStringToFile(sDA, "bruteForceCaesar.txt");
		
		byte decodingKey = Decrypt.caesarWithFrequencies(result);
		String sFD = bytesToString(Encrypt.caesar(result, decodingKey));
		System.out.println("Decoded without knowing the key : " + sFD);
		*/
		System.out.println();
		System.out.println();
	}
	
	public static void testVigenere(byte[] string, byte[] keyword) {
		//Encoding
		byte[] result = Encrypt.vigenere(string, keyword);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);
		System.out.println(" ");
		
		//Decoding with key
		byte[] keywordInverse = new byte[keyword.length];
		for (int i = 0; i < keyword.length; ++i) {
			keywordInverse[i] = (byte)(-(keyword[i]));
		}
		String sD = bytesToString(Encrypt.vigenere(result, keywordInverse));
		System.out.println("Decoded knowing the key : " + sD);
	}
	
//TODO : TO BE COMPLETED
	
}
