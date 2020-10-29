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
		String customMessage = "bonne journee";//"bonne journée";//"b:1 / , o:2 / , n:3 /, e:3 /, j:1 /, u:1/, r:1/ => 12 characters
		
		String messageClean = cleanString(inputMessage);
		
		byte[] messageBytes = stringToBytes(messageClean);
		byte[] customMessageBytes = stringToBytes(customMessage);
		byte[] keyBytes = stringToBytes(key);
		
		System.out.println("Original input sanitized : " + messageClean);
		System.out.println();
		
		System.out.println("------Caesar------");
		testCaesar(messageBytes, keyBytes[0]);
		//testCaesar(Helper.stringToBytes(customMessage), (byte)3);
		/*
		System.out.println("------Vigenere------");
		testVigenere(messageBytes, keyBytes);
		byte[] vigenereTempKeyTester = {(byte) 1, (byte) 2, (byte) 3};
		testVigenere(Helper.stringToBytes(customMessage), vigenereTempKeyTester);

		System.out.println("------XOR------");
		testXOR(messageBytes, keyBytes[0]);
		testXOR(Helper.stringToBytes(customMessage), (byte)7);
		
		System.out.println("------One Time Pad------");
	    testOneTimePad(messageBytes, Encrypt.generatePad(messageBytes.length));
		testOneTimePad(Helper.stringToBytes(customMessage), Encrypt.generatePad(customMessage.length()));
		
		System.out.println("------CBC------");
		//testCBC(messageBytes, Encrypt.generatePad(8));
		testCBC(Helper.stringToBytes(customMessage), Encrypt.generatePad(4));
		
		//----------------------- Decryption Testing-------------------------
		System.out.println("------Compute Frequencies------");
		
		System.out.println("Frequencies table:");
		//Decrypt.computeFrequencies(messageBytes);
		System.out.println();
		System.out.println();

		System.out.println("Frequencies Table bonne journee: ");
		Decrypt.computeFrequencies(Helper.stringToBytes(customMessage));
		System.out.println();

		System.out.println("Testing caesar frequencies calculation");*/
		//byte test = Decrypt.caesarWithFrequencies(messageBytes); //input a cipher text (instead of clean text
		
		// TODO: TO BE COMPLETED
		
	}
	
	
	//Run the Encoding and Decoding using the caesar pattern 
	public static void testCaesar(byte[] string , byte key) {		
		//Encoding
		byte[] result = Encrypt.caesar(string, key); //encrypted using caesar, key=50
		byte[] resultCopy = new byte[result.length];
		

		for (int i = 0; i < result.length; ++i)
			resultCopy[i] = result[i];
		
		String s = bytesToString(result);
		System.out.println("Caesar encoding key: " + key);
		System.out.println("Encoded : " + s);


		
		//Decoding with key
		String sD = bytesToString(Encrypt.caesar(result, (byte) (-key))); //running result changes the original array!!
		System.out.println("Decoded knowing the key : " + sD);
		//By decoding result with array, you are changing the values of result
		//This changes the value that is decoded using the Frequency Profiler!!
		System.out.println();
		
		
		//Decoding using Frequency Profile
		//byte keyFP = Decrypt.caesarWithFrequencies(result); //returns the key of the shift
		String sDFP = bytesToString(Encrypt.caesar(resultCopy, (byte) (Decrypt.caesarWithFrequencies(resultCopy))));
		System.out.println("Decoded using Frequency Profile: " + sDFP);
		
		System.out.println();
		
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
		
		//Decoding with key
		byte[] keywordInverse = new byte[keyword.length];
		for (int i = 0; i < keyword.length; ++i) {
			keywordInverse[i] = (byte)(-(keyword[i]));
		}
		String sD = bytesToString(Encrypt.vigenere(result, keywordInverse));
		System.out.println("Decoded knowing the key : " + sD);
		System.out.println();

	}
	
	public static void testXOR(byte[] string , byte key) {		
		//Encoding
		byte[] result = Encrypt.xor(string, key);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);

		//Decoding with key
		String sD = bytesToString(Encrypt.xor(result, (byte) (key)));
		System.out.println("Decoded knowing the key : " + sD);
		
		System.out.println();
	}
	
	public static void testOneTimePad(byte[] string , byte[] randomPad) {		
		//Encoding
		byte[] result = Encrypt.oneTimePad(string, randomPad);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);

		//Decoding with key
		String sD = bytesToString(Encrypt.oneTimePad(result, randomPad));
		System.out.println("Decoded knowing the key : " + sD);
		
		System.out.println();
	}
	
	public static void testCBC(byte[] string , byte[] randomPad) {		
		//Encoding
		byte[] result = Encrypt.cbc(string, randomPad);
		String s = bytesToString(result);
		for(int i = 0; i < s.length(); ++i)
		System.out.println(s.charAt(i));
		//System.out.println("Encoded : " + s);

		/* Decoding with key
		String sD = bytesToString(Encrypt.cbc(result, randomPad));
		System.out.println("Decoded knowing the key : " + sD);
		
		System.out.println();
		*/
		
		System.out.println();
	}
	
	//-----------------------Decryption Testing Methods-------------------------
	public static void testComputeFrequencies(byte[] string , byte[] randomPad) {		
		//Encoding
		byte[] result = Encrypt.cbc(string, randomPad);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);

		//Decoding with key
		String sD = bytesToString(Encrypt.cbc(result, randomPad));
		System.out.println("Decoded knowing the key : " + sD);
		
		System.out.println();
	}
	
//TODO : TO BE COMPLETED
	
}
