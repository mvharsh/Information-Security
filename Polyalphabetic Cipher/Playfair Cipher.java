package cms.polyalphabeticcipher;

import java.util.*;

public class PlayFairCipher {
    private char[][] keySquare;
    private static final int SIZE = 5;
    
    public PlayFairCipher(String key) {
        keySquare = generateKeySquare(key);
    }
    
    private char[][] generateKeySquare(String key) {
        boolean[] seen = new boolean[26];
        char[][] square = new char[SIZE][SIZE];
        String keyString = (key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").replace("J", "I").toUpperCase();
        StringBuilder filteredKey = new StringBuilder();
        
        for (char c : keyString.toCharArray()) {
            if (Character.isLetter(c) && !seen[c - 'A']) {
                seen[c - 'A'] = true;
                filteredKey.append(c);
            }
        }
        
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                square[i][j] = filteredKey.charAt(index++);
            }
        }
        return square;
    }
    
    private String preprocessText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder processed = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            if (i > 0 && text.charAt(i) == text.charAt(i - 1) && i % 2 == 1) {
                processed.append('X');
            }
            processed.append(text.charAt(i));
        }
        if (processed.length() % 2 == 1) {
            processed.append('X');
        }
        return processed.toString();
    }
    
    private String processPairs(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        text = preprocessText(text);
        
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] posA = findPosition(a), posB = findPosition(b);
            
            if (posA[0] == posB[0]) {
                result.append(keySquare[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % SIZE]);
                result.append(keySquare[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % SIZE]);
            } else if (posA[1] == posB[1]) {
                result.append(keySquare[(posA[0] + (encrypt ? 1 : 4)) % SIZE][posA[1]]);
                result.append(keySquare[(posB[0] + (encrypt ? 1 : 4)) % SIZE][posB[1]]);
            } else {
                result.append(keySquare[posA[0]][posB[1]]);
                result.append(keySquare[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }
    
    private int[] findPosition(char c) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keySquare[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    public String encrypt(String text) {
        return processPairs(text, true);
    }
    
    public String decrypt(String text) {
        return processPairs(text, false);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the keyword: ");
        String key = scanner.nextLine();
        
        PlayFairCipher cipher = new PlayFairCipher(key);
        
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        
        String encryptedText = cipher.encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        
        String decryptedText = cipher.decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }
}
