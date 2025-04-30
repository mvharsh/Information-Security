
def diffie_hellman():
    # Get prime number (P) and primitive root (G) from the user
    P = int(input("Enter a prime number (P): "))
    G = int(input("Enter a primitive root of P (G): "))

    # Private keys for Alice and Bob
    private_a = random.randint(1, P-1)
    private_b = random.randint(1, P-1)

    print(f"Alice's private key: {private_a}")
    print(f"Bob's private key: {private_b}")

   
 # Public keys for Alice and Bob
    public_a = (G ** private_a) % P
    public_b = (G ** private_b) % P

    print(f"Alice's public key: {public_a}")
    print(f"Bob's public key: {public_b}")

    # Shared secret keys
    shared_secret_a = (public_b ** private_a) % P
    shared_secret_b = (public_a ** private_b) % P

    print(f"Shared secret for Alice: {shared_secret_a}")
    print(f"Shared secret for Bob: {shared_secret_b}")

    # Confirm keys match
    if shared_secret_a == shared_secret_b:
        print("Key exchange successful! Shared secret established.")
    else:
        print("Key exchange failed.")

if __name__ == "__main__":
    diffie_hellman()
