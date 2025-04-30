
**ALGORITHM:**

  **1.  Choose Public Parameters**
  -	Select a prime number P.
  -	Select a primitive root G of P.
    
  **2.  Generate Private Keys**
  -	Alice selects a private key a (random integer: 1<a<P).
  -	Bob selects a private key b (random integer: 1<b<P).
    
  **3.  Compute Public Keys**
  -	Alice computes her public key: A = G^a mod P.
  -	Bob computes his public key: B = G^b mod P.
    
  **4.  Exchange Public Keys**
  -	Alice sends A to Bob.
  -	Bob sends B to Alice.
    
  **5.  Compute Shared Secret Key**
  -	Alice computes: S = B^a mod P.
  -	Bob computes: S = A^b mod P.
    
  **6.  Establish Secure Communication**
  -	Since S is identical for both, it becomes the shared secret key.

**OUTPUT:**

Enter a prime number (P): 23

Enter a primitive root of P (G): 5

Alice's private key: 1

Bob's private key: 20

Alice's public key: 5

Bob's public key: 12

Shared secret for Alice: 12

Shared secret for Bob: 12

Key exchange successful! Shared secret established.
