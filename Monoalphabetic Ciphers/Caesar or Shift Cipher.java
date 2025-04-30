package cms.monoalphabeticcipher;

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();
        scanner.nextLine();  

        String encryptedText = caesarCipher(text, shift);
        String decryptedText = caesarCipher(encryptedText, -shift);

        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);        
        scanner.close();
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
