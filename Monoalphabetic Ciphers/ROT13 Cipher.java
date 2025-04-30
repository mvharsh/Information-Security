package cms.monoalphabeticcipher;

import java.util.Scanner;

public class ROT13Cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        String encryptedText = rot13Cipher(text);
        String decryptedText = rot13Cipher(encryptedText);

        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
        
        scanner.close();
    }

    static String rot13Cipher(String text) {
        return caesarCipher(text, 13);
    }

    static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (base + (c - base + shift + 26) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

