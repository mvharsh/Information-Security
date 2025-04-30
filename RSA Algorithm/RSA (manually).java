import java.util.*;

public class RSAEncryption {

    public static void main(String[] args) {
        int p = generatePrime();
        int q = generatePrime();

        while (q == p) {
            q = generatePrime();
        }

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = findE(phi);
        int d = findModInverse(e, phi);

        System.out.println("Primes: p = " + p + ", q = " + q);
        System.out.println("Public Key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private Key: (d = " + d + ", n = " + n + ")");
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = in.nextLine();
        System.out.println("\nOriginal message: " + message);

        int[] encrypted = encrypt(message, e, n);
        System.out.print("Encrypted: ");
        for (int c : encrypted) {
            System.out.print(c + " ");
        }

        String decrypted = decrypt(encrypted, d, n);
        System.out.println("\nDecrypted: " + decrypted);
    }

    static int generatePrime() {
        Random rand = new Random();
        int num;
        do {
            num = rand.nextInt(50); 
        } while (!isPrime(num));
        return num;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    static int findE(int phi) {
        for (int e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) return e;
        }
        return -1;
    }

    static int findModInverse(int e, int phi) {
        for (int d = 1; d < phi; d++) {
            if ((e * d) % phi == 1) return d;
        }
        return -1;
    }

    static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    static int[] encrypt(String msg, int e, int n) {
        int[] encrypted = new int[msg.length()];
        for (int i = 0; i < msg.length(); i++) {
            int m = (int) msg.charAt(i);
            encrypted[i] = modPow(m, e, n);
        }
        return encrypted;
    }

    static String decrypt(int[] encrypted, int d, int n) {
        StringBuilder sb = new StringBuilder();
        for (int num : encrypted) {
            int m = modPow(num, d, n);
            sb.append((char) m);
        }
        return sb.toString();
    }
}
