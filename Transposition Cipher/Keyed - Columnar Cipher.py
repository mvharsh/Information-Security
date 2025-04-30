import math
import itertools

def get_key_order(keyword):
    """Returns the order of characters in the keyword."""
    return sorted(range(len(keyword)), key=lambda x: keyword[x])

def encrypt(plaintext, keyword):
    """Encrypts plaintext using a Keyed-Columnar Transposition Cipher."""
    keyword_order = get_key_order(keyword)
    num_cols = len(keyword)
    num_rows = math.ceil(len(plaintext) / num_cols)

    # Create matrix and fill it row-wise
    matrix = [['X'] * num_cols for _ in range(num_rows)]  # 'X' is padding
    idx = 0
    for r in range(num_rows):
        for c in range(num_cols):
            if idx < len(plaintext):
                matrix[r][c] = plaintext[idx]
                idx += 1

    # Read column-wise in order of keyword
    sorted_indices = get_key_order(keyword)
    ciphertext = ''.join(matrix[r][c] for c in sorted_indices for r in range(num_rows))

    return ciphertext

def decrypt(ciphertext, keyword):
    """Decrypts ciphertext using a Keyed-Columnar Transposition Cipher."""
    num_cols = len(keyword)
    num_rows = math.ceil(len(ciphertext) / num_cols)

    # Create an empty matrix
    matrix = [[''] * num_cols for _ in range(num_rows)]
    sorted_indices = get_key_order(keyword)

    # Fill column-wise according to the keyword order
    idx = 0
    for c in sorted_indices:
        for r in range(num_rows):
            if idx < len(ciphertext):
                matrix[r][c] = ciphertext[idx]
                idx += 1

    # Read row-wise to reconstruct plaintext
    plaintext = ''.join(matrix[r][c] for r in range(num_rows) for c in range(num_cols))

    return plaintext.rstrip('X')  # Remove padding

def brute_force_attack(ciphertext, keyword_length):
    """Simulates a brute force attack by testing all possible keyword permutations."""
    possible_keywords = [''.join(p) for p in itertools.permutations("ABCDEFGH"[:keyword_length], keyword_length)]
    for key in possible_keywords:
        decrypted_text = decrypt(ciphertext, key)
        print(f"Trying key '{key}': {decrypted_text}")

def frequency_analysis(ciphertext):
    """Performs frequency analysis to detect character distributions."""
    freq = {}
    for char in ciphertext:
        if char in freq:
            freq[char] += 1
        else:
            freq[char] = 1

    sorted_freq = sorted(freq.items(), key=lambda x: x[1], reverse=True)
    print("Character Frequency Analysis:")
    for char, count in sorted_freq:
        print(f"{char}: {count}")

if __name__ == "__main__":

    plaintext = input("Enter plaintext: ")
    keyword = input("Enter keyword: ")

    encrypted_text = encrypt(plaintext, keyword)
    print("Encrypted Text:", encrypted_text)

    decrypted_text = decrypt(encrypted_text, keyword)
    print("Decrypted Text:", decrypted_text)

    frequency_analysis(encrypted_text)

    brute_force_attack(encrypted_text, len(keyword))
