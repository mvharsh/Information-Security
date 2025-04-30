**AIM:**

To demonstrate how different SHA hashing algorithms (SHA-1, SHA-256, and SHA-512) produce different hash lengths for a given input, and to test whether two different inputs result in the same hash (a hash collision).

**ALGORITHM:**

  1.  Start the program.
  2.  Prompt the user to enter a string for comparing SHA hash lengths.
  3.  Generate and display hashes of the input using SHA-1, SHA-256, and SHA-512:
  -	Use hashlib to compute each hash.
  -	Show each hash along with its character length.
  4.  Prompt the user to enter two different strings for collision testing.
  5.  Prompt the user to enter a hashing algorithm (default is SHA-256).
  6.  Generate hashes for both strings using the selected algorithm.
  7.  Compare the two hashes:
  -	If they are equal, display a message indicating a collision.
  -	Otherwise, indicate that the hashes are different.
  8.  End the program.
