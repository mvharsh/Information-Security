package cms.polyalphabeticcipher;

import java.util.Random;
import java.util.Scanner;

public class OneTimePadCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String key = generateRandomKey(plaintext.length());
        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Generated Key: " + key);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    static String generateRandomKey(int length) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();        
        for (int i = 0; i < length; i++) {
            key.append((char) ('A' + random.nextInt(26))); // Generates random uppercase letters
        }
        return key.toString();
    }

    static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textChar = Character.toUpperCase(text.charAt(i));
            char keyChar = key.charAt(i);

            if (Character.isLetter(textChar)) {
                int encryptedChar = ((textChar - 'A') + (keyChar - 'A')) % 26 + 'A';
                encryptedText.append((char) encryptedChar);
            } else {
                encryptedText.append(textChar);
            }
        }
        return encryptedText.toString();
    }

    static String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (Character.isLetter(textChar)) {
                int decryptedChar = ((textChar - 'A') - (keyChar - 'A') + 26) % 26 + 'A';
                decryptedText.append((char) decryptedChar);
            } else {
                decryptedText.append(textChar);
            }
        }
        return decryptedText.toString();
    }
}
