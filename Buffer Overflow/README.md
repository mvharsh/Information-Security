**AIM:**

To demonstrate a buffer overflow vulnerability and how it can corrupt stack data (canary), potentially leading to detection or exploitation. It's a basic security lab setup to understand stack-based memory corruption.

**ALGORITHM:**

**1.  Start main:**
-	Check if input argument is provided.
-	If not, print usage and exit.
  
**2.  Call vulnerable_function(input):**
-	Define a 4-byte canary with value "ABC" to detect overflow.
-	Define a 16-byte buffer for user input.
-	Use strcpy to copy input into buffer (unsafe, no bounds check).
-	Compare current canary value with original.
-	If changed → print overflow warning and cause a crash (write to NULL).
-	Print buffer content.

**OUTPUT:**

PS D:\IS\Buffer Overflow> gcc -o buffer_overflow.exe buffer_overflow.c

PS D:\IS\Buffer Overflow>./buffer_overflow.exe hello

Buffer content: hello

PS D:\IS\Buffer Overflow>./buffer_overflow.exe AAAAAABBBBBBBBBBBBBBCCCCCCCCCCCC  

Canary corrupted! Buffer overflow detected!
