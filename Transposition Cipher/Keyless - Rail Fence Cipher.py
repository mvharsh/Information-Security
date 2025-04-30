def rail_fence_encrypt(plain_text, rails=2):
    plain_text = plain_text.replace(" ", "").upper()
    if not plain_text.isalpha():
        raise ValueError("Plaintext should contain only alphabetic characters.")

    fence = ['' for _ in range(rails)]
    rail = 0  # Start at the first rail
    direction = 1  # Moving direction (1: down, -1: up)

    for char in plain_text:
        fence[rail] += char  # Place character in the correct rail
        rail += direction  # Move to the next rail
        if rail == 0 or rail == rails - 1:  # Change direction when hitting top or bottom
            direction *= -1

    return ''.join(fence)  # Join all rails together to form the ciphertext

def rail_fence_decrypt(cipher_text, rails=2):
    fence = [['' for _ in range(len(cipher_text))] for _ in range(rails)]
    rail = 0
    direction = 1

    for i in range(len(cipher_text)):  # Mark positions with '*'
        fence[rail][i] = '*'
        rail += direction
        if rail == 0 or rail == rails - 1:
            direction *= -1

    index = 0
    for r in range(rails):  # Fill the '*' positions with cipher text
        for c in range(len(cipher_text)):
            if fence[r][c] == '*' and index < len(cipher_text):
                fence[r][c] = cipher_text[index]
                index += 1

    result = []
    rail = 0
    direction = 1
    for i in range(len(cipher_text)):  # Read characters row-wise to reconstruct text
        result.append(fence[rail][i])
        rail += direction
        if rail == 0 or rail == rails - 1:
            direction *= -1

    return ''.join(result)

def frequency_analysis(cipher_text):
    freq = {}
    for char in cipher_text:
        if char in freq:
            freq[char] += 1
        else:
            freq[char] = 1
    return freq

def brute_force_attack(cipher_text, max_rails=5):
    possible_decryptions = {}
    for rails in range(2, max_rails + 1):  # Try different rail counts
        decrypted_text = rail_fence_decrypt(cipher_text, rails)
        possible_decryptions[rails] = decrypted_text
    return possible_decryptions

# User interaction
def main():
    try:
        user_input = input("Enter the plaintext: ").strip()
        if not user_input:
            raise ValueError("Input cannot be empty.")

        rails = int(input("Enter number of rails: "))
        encrypted_text = rail_fence_encrypt(user_input, rails)
        print(f"Encrypted Text: {encrypted_text}")

        decrypted_text = rail_fence_decrypt(encrypted_text, rails)
        print(f"Decrypted Text: {decrypted_text}")

        print("\nFrequency Analysis:")
        print(frequency_analysis(encrypted_text))

        print("\nBrute Force Attack Results:")
        brute_force_results = brute_force_attack(encrypted_text, max_rails=5)
        for rail_count, decrypted in brute_force_results.items():
            print(f"Rails={rail_count}: {decrypted}")
    except ValueError as e:
        print(f"Input Error: {e}")

if __name__ == "__main__":
    main()
