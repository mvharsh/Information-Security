package cms.polyalphabeticcipher;

import java.util.Scanner;

public class AutokeyCipher {
    
    public static String encrypt(String text, String key) {
        StringBuilder cipherText = new StringBuilder();
        key += text; // Append plaintext to key
        key = key.substring(0, text.length());
        
        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i);
            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                char baseKey = Character.isUpperCase(keyChar) ? 'A' : 'a';
                char encChar = (char) (((plainChar - base + (keyChar - baseKey)) % 26) + base);
                cipherText.append(encChar);
            } else {
                cipherText.append(plainChar); // Keep non-alphabetic characters unchanged
            }
        }
        return cipherText.toString();
    }
    
    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        
        for (int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar = key.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char base = Character.isUpperCase(cipherChar) ? 'A' : 'a';
                char baseKey = Character.isUpperCase(keyChar) ? 'A' : 'a';
                char decChar = (char) (((cipherChar - base - (keyChar - baseKey) + 26) % 26) + base);
                plainText.append(decChar);
                key += decChar; // Append decrypted character to key
            } else {
                plainText.append(cipherChar);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the plaintext: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter the keyword: ");
        String key = scanner.nextLine();
        
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);
        
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }
}
