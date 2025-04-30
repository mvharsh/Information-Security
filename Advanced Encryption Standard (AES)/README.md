**AIM:**

To implement encryption and decryption using the AES (Advanced Encryption Standard) algorithm in CBC (Cipher Block Chaining) mode with PKCS7 padding.

**ALGORITHM:**

**Encryption:**

  1. Convert plaintext into bytes.
  3. Generate a random 16-byte Initialization Vector (IV).
  4. Create an AES cipher object in CBC mode.
  5. Pad plaintext to be a multiple of 16 bytes.
  6. Encrypt the plaintext and concatenate it with the IV.
  7. Encode the result using Base64 for safe transmission.

**Decryption**

1. Decode the Base64-encoded ciphertext.
2. Extract the IV from the first 16 bytes.
3. Create an AES cipher object with the same key.
4. Decrypt and remove padding to recover the original plaintext.

**OUTPUT:**

Enter message: "Hello, AES Encryption!"

Original Message: "Hello, AES Encryption!"

Encrypted Message: 4PS7ttnxqrVyTXcWNZjY9DOtmJrJp224oKAcc0nMFbLF5VhocCSTsli03WU/SFKF

Decrypted Message: "Hello, AES Encryption!"
