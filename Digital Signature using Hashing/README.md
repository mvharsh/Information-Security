**AIM:**

To ensure the integrity and authenticity of a Word document by generating a SHA-512 hash, saving it as a digital signature, and later verifying that the document has not been tampered with by comparing the current hash with the saved signature.

**ALGORITHM:**

1.  Prompt the user to enter the path of a Word document (.docx).
2.  Extract content from the document, including text from:	Paragraphs and	Tables (rows and cells)
3.  Generate a SHA-512 hash of the extracted content.
4.  Sign the document: Save the generated hash in a separate .sig file with the same name as the document.
5.  Verify the signature:
-	Check if the .sig file exists.
-	Read the saved hash from the .sig file.
-	Recompute the current hash of the document content.
-	Compare the saved hash with the current hash
-	If they match → The document is intact.
-	If they differ → The document has been modified/tampered.
6.  Output the result to the user.
