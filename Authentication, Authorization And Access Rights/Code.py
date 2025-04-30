import hashlib
import getpass

def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()

# Storing users with hashed passwords and roles
users = {
    "admin": {"password": hash_password("admin123"), "role": "ADMIN"},
    "user": {"password": hash_password("user123"), "role": "USER"}
}

def authenticate(username, password):
    if username in users and users[username]["password"] == hash_password(password):
        return True
    return False


def authorize(username, action):
    role = users[username]["role"]
    if role == "ADMIN":
        return True  # Admin has all rights
    elif role == "USER" and action == "VIEW":
        return True  # User can only view
    return False  # Access Denied

def main():
    username = input("Enter Username: ")
    password = getpass.getpass("Enter Password: ")  # Hides password input
    
    if authenticate(username, password):
        print("Authentication Successful!")
        action = input("Enter Action (VIEW/EDIT/DELETE): ").upper()
        
        if authorize(username, action):
            print(f"Access Granted for action: {action}")
        else:
            print("Access Denied!")
    else:
        print("Authentication Failed!")

if __name__ == "__main__":
    main()

