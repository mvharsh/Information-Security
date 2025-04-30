import hashlib

# Function to hash a string using a specified SHA algorithm
def generate_hash(text, algorithm='sha256'):
    hash_function = getattr(hashlib, algorithm)  # Get hash function dynamically
    return hash_function(text.encode()).hexdigest()

# Compare output lengths of different SHA variants
def compare_hash_lengths(text):
    sha1_hash = generate_hash(text, 'sha1')
    sha256_hash = generate_hash(text, 'sha256')
    sha512_hash = generate_hash(text, 'sha512')

    print(f"\nSHA-1 ({len(sha1_hash)} characters): {sha1_hash}")
    print(f"SHA-256 ({len(sha256_hash)} characters): {sha256_hash}")
    print(f"SHA-512 ({len(sha512_hash)} characters): {sha512_hash}")

# Collision test: Check if two different inputs produce the same hash
def collision_test(text1, text2, algorithm='sha256'):
    hash1 = generate_hash(text1, algorithm)
    hash2 = generate_hash(text2, algorithm)

    print(f"\nHash 1: {hash1}")
    print(f"Hash 2: {hash2}")

    if hash1 == hash2:
        print("Collision detected! The two inputs have the same hash.")
    else:
        print("No collision detected. The hashes are different.")

# Main interaction
if __name__ == "__main__":
    # Get input for hash comparison
    input_text = input("Enter a string to compare SHA hash lengths: ")
    compare_hash_lengths(input_text)

    # Get inputs for collision test
    print("\n--- Collision Test ---")
    text1 = input("Enter first string: ")
    text2 = input("Enter second string: ")
    algo = input("Enter hashing algorithm (default is sha256): ").strip() or "sha256"

    collision_test(text1, text2, algo)
