**AIM:**

To implement RSA encryption and decryption using asymmetric cryptography with the cryptography library in Python

**ALGORITHM:**

**1. Key Generation:**
-	Choose two large prime numbers, p and q.
-	Compute n=p×q , which is used as the modulus.
-	Compute Euler’s totient function: φ(n)=(p−1)(q−1)
-	Choose an integer ee such that 1<e<φ(n) and gcd⁡(e,φ(n))=1
-	Compute the private key exponent d as the modular inverse of e modulo φ(n) i.e., d≡e−1mod φ(n)
-	The public key is (e,n) and the private key is (d,n).

**2. Encryption:**
-	Convert the plaintext message M into an integer m, where 0≤m<n
-	Compute the cipher text c using the formula: c=me mod n
-	Transmit c to the recipient.

**3. Decryption:**
-	Compute the original message m using the formula: m=cd mod n
-	Convert m back to the original plaintext message.
