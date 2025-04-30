import hashlib
import os
from datetime import datetime
from docx import Document

# Extract content from a Word document
def extract_content(file_path):
    doc = Document(file_path)
    content = [para.text for para in doc.paragraphs]
    for table in doc.tables:
        for row in table.rows:
            for cell in row.cells:
                content.append(cell.text)
    return "\n".join(content)

# Generate SHA-512 hash of the document content
def generate_sha512(file_path):
    content = extract_content(file_path)
    return hashlib.sha512(content.encode("utf-8")).hexdigest()

# Sign the document by saving the hash in a separate signature file
def sign_document(file_path):
    file_hash = generate_sha512(file_path)
    signature_file = f"{file_path}.sig"

    with open(signature_file, "w") as sig_file:
        sig_file.write(file_hash)

    print(f"Document signed. Signature saved as {signature_file}")

# Verify the document integrity by comparing stored signature with recalculated hash
def verify_signature(file_path):
    signature_file = f"{file_path}.sig"

    if not os.path.exists(signature_file):
        print("No signature file found. Verification failed.")
        return

    with open(signature_file, "r") as sig_file:
        saved_signature = sig_file.read().strip()

    current_hash = generate_sha512(file_path)

    if saved_signature == current_hash:
        print("Signature is valid. The document has not been tampered with.")
    else:
        print("File tampered! Hash mismatch detected.")

# Example usage
file_path = input("Enter the path of the document: ") 
sign_document(file_path)  # Signing the document
verify_signature(file_path)  # Verifying the signature
