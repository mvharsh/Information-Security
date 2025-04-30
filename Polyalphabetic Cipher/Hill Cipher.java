package cms.polyalphabeticcipher;

import java.util.Scanner;

public class HillCipher {

    public String encrypt(String message, int[][] keyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        int matrixSize = keyMatrix.length;
        validateDeterminant(keyMatrix, matrixSize);

        StringBuilder cipherText = new StringBuilder();
        int[] messageVector = new int[matrixSize];
        int[] cipherVector = new int[matrixSize];
        int index = 0;

        while (index < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (index < message.length()) {
                    messageVector[i] = message.charAt(index++) - 'A';
                } else {
                    messageVector[i] = 'X' - 'A'; 
                }
            }

            for (int i = 0; i < matrixSize; i++) {
                cipherVector[i] = 0;
                for (int j = 0; j < matrixSize; j++) {
                    cipherVector[i] += keyMatrix[i][j] * messageVector[j];
                }
                cipherVector[i] = cipherVector[i] % 26;
                cipherText.append((char) (cipherVector[i] + 'A'));
            }
        }

        return cipherText.toString();
    }

    public String decrypt(String message, int[][] inverseKeyMatrix) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");
        int matrixSize = inverseKeyMatrix.length;
        validateDeterminant(inverseKeyMatrix, matrixSize);

        StringBuilder plainText = new StringBuilder();
        int[] messageVector = new int[matrixSize];
        int[] plainVector = new int[matrixSize];
        int index = 0;

        while (index < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (index < message.length()) {
                    messageVector[i] = message.charAt(index++) - 'A';
                } else {
                    messageVector[i] = 'X' - 'A'; 
                }
            }

            for (int i = 0; i < matrixSize; i++) {
                plainVector[i] = 0;
                for (int j = 0; j < matrixSize; j++) {
                    plainVector[i] += inverseKeyMatrix[i][j] * messageVector[j];
                }
                plainVector[i] = plainVector[i] % 26;
                plainText.append((char) (plainVector[i] + 'A'));
            }
        }

        return plainText.toString();
    }

    private void validateDeterminant(int[][] keyMatrix, int n) {
        int det = determinant(keyMatrix, n) % 26;
        if (det == 0) {
            throw new IllegalArgumentException("Invalid key matrix. Determinant is zero modulo 26.");
        }
    }

    private int determinant(int[][] matrix, int n) {
        int det = 0;
        if (n == 1) {
            return matrix[0][0];
        }
        int sign = 1;
        int[][] subMatrix = new int[n - 1][n - 1];
        for (int x = 0; x < n; x++) {
            int subI = 0;
            for (int i = 1; i < n; i++) {
                int subJ = 0;
                for (int j = 0; j < n; j++) {
                    if (j != x) {
                        subMatrix[subI][subJ++] = matrix[i][j];
                    }
                }
                subI++;
            }
            det += sign * matrix[0][x] * determinant(subMatrix, n - 1);
            sign = -sign;
        }
        return det; 
    }

    private int modInverse(int a, int mod) {
        a = a % mod;
        for (int x = 1; x < mod; x++) {
            if ((a * x) % mod == 1) {
                return x;
            }
        }
        return -1;  
    }

    public int[][] inverseMatrix(int[][] matrix) {
        int n = matrix.length;
        int det = determinant(matrix, n);
        int mod = 26;

        int detInverse = modInverse(det, mod);
        if (detInverse == -1) {
            throw new IllegalArgumentException("Matrix is not invertible.");
        }

        int[][] cofactorMatrix = new int[n][n];
        int[][] adjugateMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = getMinor(matrix, i, j);
                cofactorMatrix[i][j] = (int) (Math.pow(-1, i + j) * determinant(minor, n - 1)) % mod;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjugateMatrix[i][j] = cofactorMatrix[j][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjugateMatrix[i][j] = (adjugateMatrix[i][j] * detInverse) % mod;
                if (adjugateMatrix[i][j] < 0) {
                    adjugateMatrix[i][j] += mod;
                }
            }
        }

        return adjugateMatrix;
    }

    private int[][] getMinor(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];
        int mRow = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue; 
            int mCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[mRow][mCol++] = matrix[i][j];
            }
            mRow++;
        }
        return minor;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HillCipher cipher = new HillCipher();

        System.out.println("Enter the size of the key matrix (e.g., 2 for 2x2, 3 for 3x3): ");
        int matrixSize = scanner.nextInt();
        scanner.nextLine(); 

        int[][] keyMatrix = new int[matrixSize][matrixSize];
        System.out.println("Enter the " + matrixSize + "x" + matrixSize + " key matrix:");

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print("Enter value for keyMatrix[" + i + "][" + j + "]: ");
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine(); 
        System.out.println("Enter the message to encrypt (letters only): ");
        String message = scanner.nextLine();
        String encryptedMessage = cipher.encrypt(message, keyMatrix);
        System.out.println("Encrypted Message: " + encryptedMessage);

        int[][] inverseKeyMatrix = cipher.inverseMatrix(keyMatrix);

        String decryptedMessage = cipher.decrypt(encryptedMessage, inverseKeyMatrix);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
