package crypto;
import java.util.Scanner;

import static crypto.Helper.cleanString;
import static crypto.Helper.stringToBytes;

import java.util.Scanner;

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
		
		Shell.shellInit();
	
		
		/*
		byte[] cipherCBC = stringToBytes("v\\ZA|<.pYZPo6z3SX'!|?RMM>4=9VQ_9#h2J	\\.#}3BDo;bd^ =)flQ	4){jG]	$)`pJZA");
		byte[] CBCKey = stringToBytes("234f");
		
		System.out.println("CBC decrypt" + bytesToString(Decrypt.decryptCBC(cipherCBC, CBCKey)));
		
		String inputMessage = Helper.readStringFromFile("text_three.txt");
		String messageClean = cleanString(inputMessage);
		byte[] messageBytes = stringToBytes(messageClean);
		
		String key = "23d4g"; //Value shift[0] is 50. KeyLength = 5		
		byte[] keyBytes = stringToBytes(key);//{(byte)70};//

		for (byte val: keyBytes)
			System.out.print(val + " ");
		
		System.out.println();
		System.out.println("Original input sanitized : " + messageClean);
		System.out.println();
		
		//Encrypt a vigenere message
		byte[] result = Encrypt.vigenere(messageBytes, keyBytes);
		String s = bytesToString(result);
		System.out.println("Encoded Vigenere: " + s);
		byte[] vigenereKey = Decrypt.vigenereWithFrequencies(result);
		
		System.out.println();
		System.out.println("Decoded Vigenere: " + Decrypt.breakCipher(s, 1));
		System.out.println();
		*/
		
		/*	
	
		String inputMessage = Helper.readStringFromFile("text_two.txt");
		String key = "2cF%5"; //Value shift[0] is 50. KeyLength = 5
		String customMessage = "bonne journée";
		
		
		String messageClean = cleanString(inputMessage);
		
		byte[] messageBytes = stringToBytes(messageClean);
		byte[] customMessageBytes = stringToBytes(customMessage);
		byte[] keyBytes = stringToBytes(key);//{(byte)70};//

		
		System.out.println("Original input sanitized : " + messageClean);
		System.out.println();
		
		//----------------------- Encryption Testing-------------------------

		System.out.println("------Caesar------");
		testCaesar(messageBytes, keyBytes[0]);
		//testCaesar(Helper.stringToBytes(customMessage), (byte)3);
		
		
		System.out.println("------Vigenere------");
		testVigenere(messageBytes, keyBytes);
		byte[] vigenereTempKeyTester = { (byte) 1, (byte) 2, (byte) 3 };
		testVigenere(Helper.stringToBytes(customMessage), vigenereTempKeyTester);


		System.out.println("------XOR------");
		//testXOR(messageBytes, keyBytes[0]);
		testXOR(Helper.stringToBytes(customMessage), (byte)7);
		
		
		System.out.println("------One Time Pad------");
	    testOneTimePad(messageBytes, Encrypt.generatePad(messageBytes.length));
		testOneTimePad(Helper.stringToBytes(customMessage), Encrypt.generatePad(customMessage.length()));
		
		
		System.out.println("------CBC------");
		testCBC(messageBytes, Encrypt.generatePad(8));
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
		
		

		System.out.println("Testing caesar frequencies calculation");
		//byte test = Decrypt.caesarWithFrequencies(messageBytes); //input a cipher text (instead of clean text
		
		*/
		
		//----------------------- Caesar Freq. Profilers-------------------------
		/*
		*/
		/*
		String challengeCaesarFreqDecoder = Helper.bytesToString(Encrypt.caesar(challenge, (byte)(Decrypt.caesarWithFrequencies(challenge))));
		
		System.out.println("Decoded using Caesar Freq. Profiler: " + challengeCaesarFreqDecoder);
		*/
		//----------------------- Vigenere Freq. Profiler -------------------------
		
		/*
		//Test 1:
		byte[] result = Encrypt.vigenere(messageBytes, keyBytes);
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);
		byte[] vigenereKey = Decrypt.vigenereWithFrequencies(result);
		
		byte[] vigenereKeyInverse = new byte[vigenereKey.length];
		for (int i = 0; i < vigenereKey.length; ++i) {
			vigenereKeyInverse[i] = (byte)(-(vigenereKey[i]));
		}
		
		System.out.println();
		String challengeDecoded = bytesToString(Encrypt.vigenere(result, vigenereKey));
		System.out.println("Decoded knowing the key : " + challengeDecoded);
		System.out.println();
		*/
		
		
		//Test 2: Challenge
		/*
		System.out.println("New challenge");
		System.out.print(Helper.bytesToString(Helper.readBytesFromFile("challenge-encrypted.bin")));
		byte[] challenge = Helper.readBytesFromFile("challenge-encrypted.bin");
		byte[] vigenereKey = Decrypt.vigenereWithFrequencies(challenge);
		
		byte[] vigenereKeyInverse = new byte[vigenereKey.length];
		for (int i = 0; i < vigenereKey.length; ++i) {
			vigenereKeyInverse[i] = (byte)(-(vigenereKey[i]));
		}
		
		System.out.println();
		String challengeDecoded = bytesToString(Encrypt.vigenere(challenge, vigenereKey));
		System.out.println("Decoded knowing the key : " + challengeDecoded);
		System.out.println();
		
		*/
		
		
	}
	
	//=======================PREVIOUS TESTING METHODS=======================
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
		
		//TODO: Uncomment the provided caesarbruteForce Testing
		//Decrypt.caesarBruteForce(resultCopy);
	
		//Decoding with key
		//String sD = bytesToString(Encrypt.caesar(result, (byte) (-key))); //running result changes the original array!!
		//System.out.println("Decoded knowing the key : " + sD);
		//By decoding result with array, you are changing the values of result
		//This changes the value that is decoded using the Frequency Profiler!!
		System.out.println();
		
		
		//Decoding using Frequency Profile
		//byte keyFP = Decrypt.caesarWithFrequencies(result); //returns the key of the shift
		String sDFP = bytesToString(Encrypt.caesar(resultCopy, (byte) (-(Decrypt.caesarWithFrequencies(resultCopy)))));
		System.out.println("Decoded using Frequency Profile: " + sDFP);
		
		System.out.println();
		
		byte[][] bruteForceResult = Decrypt.caesarBruteForce(resultCopy);
		String sDA = Decrypt.arrayToString(bruteForceResult);
	    Helper.writeStringToFile(sDA, "bruteForceCaesar.txt");
		
		
		//TODO: BruteForce decoding
		/*
		//Decoding without key
		
		
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
		
        byte[] resultCopy = new byte[result.length];
		
		for (int i = 0; i < result.length; ++i)
			resultCopy[i] = result[i];
		
		String s = bytesToString(result);
		System.out.println("Encoded : " + s);

		//Decoding with key
		String sD = bytesToString(Encrypt.xor(result, (byte) (key)));
		System.out.println("Decoded knowing the key : " + sD);
		
		byte[][] bruteForceResult = Decrypt.xorBruteForce(resultCopy);
		String sDA = Decrypt.arrayToString(bruteForceResult);
	    Helper.writeStringToFile(sDA, "bruteForceXor.txt");
		
		
		
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
		System.out.println("Encoded message:" + s);
		
		
		byte[] decrypted = Decrypt.decryptCBC(result, randomPad);
		String d = bytesToString(decrypted);
		System.out.println("Decrypted knowing the key: " + d);
		
		
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
	
}

//=======================SHELL CLASS=======================

class Shell {
	
	static Scanner scan = new Scanner(System.in);
	static String input;
	
	
	
	public static void shellInit() { //Displays the welcome text
		
		System.out.println("      Welcome to Shell! Please type a valid command or Help to display all commands.");
				shellMain();
}
	
public static void shellMain() { //Main method to start encrypting or decrypting 

	input = scan.nextLine();

	
		if (input.equals("Help")) //Check if the input corresponds to a valid command
			helper();

		else if (input.equals("Encrypt"))
			shellEncrypt();

		else if (input.equals("Decrypt"))
			shellDecrypt();
		else {
			System.out.println("      This is not a valid command, please type Help to display all valid commands");
			shellMain();
		}
	

	}
	
	public static void helper() { //Displays all available commands
		
		System.out.println("             Here are the available commands: ");
		System.out.println("      -Type 'Encrypt' if you would like to encrypt a message");
		System.out.println("      -Type 'Decrypt' if you would like to decrypt a message");
		System.out.println("      -Type 'Help' to display available commands");
		
		shellMain(); //Restarts Shell so the user can type their command
	}
	
	public static void shellEncrypt() { //This method guides the user to encrypt a message

		boolean pass = false;
		String message = " ";
		String key = " ";
		String method = null;
		String encrypted = null;
		
		while(message.isBlank()) { //Makes sure the user typed a non-empty string to encrypt
			System.out.println("      Please type the message you want to encrypt: ");
			message = scan.nextLine();
		}
		


			while(key.isBlank()) { //Makes sure the user typed a non-empty key to encrypt with
		System.out.println("      Please type the key with which you want to encrypt ");
		
		key = scan.nextLine();
			}
			System.out.println("      Please type your preferred encryption method [Caesar/Vigenere/Xor/Onetime/Cbc]");

			do { //Checks the user's input and compares it with existing methods to encrypt with
				
				method = scan.nextLine();

				if (method.equals("Caesar")) {
					encrypted = Encrypt.encrypt(message, key, 0);
					pass = true;
				} else if (method.equals("Vigenere")) {
					encrypted = Encrypt.encrypt(message, key, 1);
					pass = true;
				} else if (method.equals("Xor")) {
					encrypted = Encrypt.encrypt(message, key, 2);
					pass = true;
				} else if (method.equals("Onetime")) {
					encrypted = Encrypt.encrypt(message, key, 3);
					pass = true;
				} else if (method.equals("Cbc")) {
					encrypted = Encrypt.encrypt(message, key, 4);
					pass = true;
				}
				else if(!pass)
					System.out.println("      Incorrect method, please type one these methods [Caesar/Vigenere/Xor/Onetime/Cbc]");

			} while (!pass);

		System.out.println("      Your encrypted message is: " + encrypted);

		shellMain(); //Restarts Shell after the program is finished

	}

	public static void shellDecrypt() { //This method guides the user to decrypt a message
		 
		boolean pass = false;
		String message = " ";
		String method = null;
		String decrypted = null;

		while(message.isBlank()) {
		System.out.println("      Please type the message you want to decrypt: ");
		message = scan.nextLine();
		}
		
		System.out.println("      Please type your preferred decryption method [Caesar/Vigenere/Xor]");
		
		do { //Checks the user's input and compares it with existing methods to decrypt with
			
			method = scan.nextLine();

			if (method.equals("Caesar")) {
				decrypted = Decrypt.breakCipher(message, 0);
				pass = true;
			} else if (method.equals("Vigenere")) {
				decrypted = Decrypt.breakCipher(message, 1);
				pass = true;
			} else if (method.equals("Xor")) {
				decrypted = Decrypt.breakCipher(message, 2);
				pass = true;
			}
			else if(!pass)
				System.out.println("Incorrect method, please type one these methods [Caesar/Vigenere/Xor]");
		} while (!pass);

		System.out.println("      Your decrypted message is: " + decrypted);

		shellMain(); //Restarts Shell after the program is finished
		
		

	}
	
	
}















