package cms.monoalphabeticcipher;

import java.util.Scanner;

public class AffineCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter multiplicative key (coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter shift value: ");
        int b = scanner.nextInt();
        scanner.nextLine();

        if (gcd(a, 26) != 1) {
            System.out.println("Error: 'a' must be coprime with 26!");
            return;
        }

        String encryptedText = affineCipher(text, a, b, true);
        String decryptedText = affineCipher(encryptedText, a, b, false);

        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    static String affineCipher(String input, int a, int b, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                int offset = Character.isUpperCase(ch) ? 'A' : 'a';
                int letterIndex = ch - offset;
                int transformedIndex;

                if (isEncrypt) {
                    transformedIndex = (a * letterIndex + b) % 26;
                } else {
                    int inverseA = findMultiplicativeInverse(a, 26);
                    transformedIndex = (inverseA * (letterIndex - b + 26)) % 26;
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
