package cms.polyalphabeticcipher;

import java.util.Scanner;

public class VigenereCipher {
    
    public static String encrypt(String text, String key) {
        StringBuilder cipherText = new StringBuilder();
        key = key.toUpperCase();
        text = text.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            if (Character.isLetter(plainChar)) {
                char keyChar = key.charAt(j % key.length());
                char encChar = (char) (((plainChar - 'A' + (keyChar - 'A')) % 26) + 'A');
                cipherText.append(encChar);
                j++;
            } else {
                cipherText.append(plainChar);
            }
        }
        return cipherText.toString();
    }
    
    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        key = key.toUpperCase();
        cipherText = cipherText.toUpperCase();
        
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char keyChar = key.charAt(j % key.length());
                char decChar = (char) (((cipherChar - 'A' - (keyChar - 'A') + 26) % 26) + 'A');
                plainText.append(decChar);
                j++;
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
