#!pip install pycryptodome

from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes
import base64

def pad(data):
    """Padding to ensure data is a multiple of 16 bytes (PKCS7)"""
    pad_length = 16 - (len(data) % 16)
    return data + bytes([pad_length] * pad_length)

def unpad(data):
    """Removing padding"""
    pad_length = data[-1]
    return data[:-pad_length]

def encrypt_aes(key, plaintext):
    """Encrypts plaintext using AES (CBC mode)"""
    plaintext = pad(plaintext.encode())
    iv = get_random_bytes(16)  # Initialization vector
    cipher = AES.new(key, AES.MODE_CBC, iv)
    ciphertext = cipher.encrypt(plaintext)
    return base64.b64encode(iv + ciphertext).decode()

def decrypt_aes(key, encrypted_text):
    """Decrypts AES-encrypted text"""
    encrypted_data = base64.b64decode(encrypted_text)
    iv = encrypted_data[:16]
    ciphertext = encrypted_data[16:]
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plaintext = unpad(cipher.decrypt(ciphertext))
    return plaintext.decode()

# Example Usage
key = get_random_bytes(16)  # AES-128 requires a 16-byte key
message = input("Enter message: ")

# Encrypting and Decrypting
encrypted_message = encrypt_aes(key, message)
decrypted_message = decrypt_aes(key, encrypted_message)

# Output Results
print("Original Message:", message)
print("Encrypted Message:", encrypted_message)
print("Decrypted Message:", decrypted_message)
