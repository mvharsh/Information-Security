package cms.monoalphabeticcipher;

import java.util.Scanner;

public class AtbashCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);      
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
      
        String encryptedText = atbashCipher(text);
        String decryptedText = atbashCipher(encryptedText);

        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    static String atbashCipher(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (base + ('Z' - c + base - 'A')));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

