package cms.monoalphabeticcipher;

import java.util.Scanner;

public class MultiplicativeCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter multiplication key (coprime with 26): ");
        int key = scanner.nextInt();
        scanner.nextLine();

        if (gcd(key, 26) != 1) {
            System.out.println("Error: Key must be coprime with 26!");
            return;
        }

        String encryptedText = multiplicativeCipher(text, key, true);
        String decryptedText = multiplicativeCipher(encryptedText, key, false);

        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    static String multiplicativeCipher(String input, int key, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                int offset = Character.isUpperCase(ch) ? 'A' : 'a';
                int letterIndex = ch - offset;
                int transformedIndex;

                if (isEncrypt) {
                    transformedIndex = (key * letterIndex) % 26;
                } else {
                    int inverseKey = findMultiplicativeInverse(key, 26);
                    transformedIndex = (inverseKey * letterIndex) % 26;
                }

                result.append((char) (transformedIndex + offset));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    static int findMultiplicativeInverse(int key, int mod) {
        for (int i = 1; i < mod; i++) {
            if ((key * i) % mod == 1) {
                return i;
            }
        }
        return 1;
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
