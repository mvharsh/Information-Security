import streamlit as st
import sqlite3

# DB Setup
conn = sqlite3.connect('users.db', check_same_thread=False)
cursor = conn.cursor()
cursor.execute('''CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)''')
conn.commit()

# Vulnerable Functions

def vulnerable_signup(username, password):
    try:
        query = f"INSERT INTO users (username, password) VALUES ('{username}', '{password}')"
        cursor.executescript(query)  # allow multiple SQL statements
        conn.commit()
    except sqlite3.OperationalError as e:
        st.error(f"SQL Error: {e}")

def vulnerable_login(username, password):
    try:
        query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
        cursor.execute(query)
        return cursor.fetchone()
    except sqlite3.OperationalError as e:
        st.error(f"SQL Error: {e}")
        return None

def vulnerable_update_password(username, new_password):
    try:
        query = f"UPDATE users SET password='{new_password}' WHERE username='{username}'"
        cursor.execute(query)
        conn.commit()
    except sqlite3.OperationalError as e:
        st.error(f"SQL Error: {e}")

def vulnerable_delete_user(username):
    try:
        query = f"DELETE FROM users WHERE username = '{username}'"
        cursor.execute(query)
        conn.commit()
    except sqlite3.OperationalError as e:
        st.error(f"SQL Error: {e}")

# Secure Functions

def secure_signup(username, password):
    cursor.execute("INSERT INTO users (username, password) VALUES (?, ?)", (username, password))
    conn.commit()

def secure_login(username, password):
    cursor.execute("SELECT * FROM users WHERE username = ? AND password = ?", (username, password))
    return cursor.fetchone()

def secure_update_password(username, new_password):
    cursor.execute("UPDATE users SET password = ? WHERE username = ?", (new_password, username))
    conn.commit()
    return cursor.rowcount > 0  # returns True if a row was actually updated

def secure_delete_user(username):
    cursor.execute("SELECT * FROM users WHERE username = ?", (username,))
    user = cursor.fetchone()
    if user:
        cursor.execute("DELETE FROM users WHERE username = ?", (username,))
        conn.commit()
        return True
    return False

# Streamlit UI

st.title("🛡️ SQL Injection: Vulnerable vs Secure")

secure_mode = st.checkbox("✅ Use Secure Mode", value=False)
menu = st.sidebar.selectbox("Choose Action", ["Signup", "Login", "Update Password", "Delete User"])
username = st.text_input("Username")

if menu != "Delete User" and menu != "Update Password":
    password = st.text_input("Password", type="password")
else:
    password = None  # No password input on delete

# For update password
new_password = st.text_input("New Password", type="password") if menu == "Update Password" else None

# Actions
if menu == "Signup":
    if st.button("Create Account"):
        if secure_mode:
            secure_signup(username, password)
            st.success("✅ Secure signup successful")
        else:
            vulnerable_signup(username, password)
            st.success("⚠️ Vulnerable signup executed")

elif menu == "Login":
    if st.button("Login"):
        user = secure_login(username, password) if secure_mode else vulnerable_login(username, password)
        if user:
            st.success(f"✅ Welcome {user[0]}!")
        else:
            st.error("❌ Invalid credentials")

elif menu == "Update Password":
    if st.button("Update Password"):
        if secure_mode:
            updated = secure_update_password(username, new_password)
            if updated:
                st.success("✅ Password updated securely")
            else:
                st.warning("⚠️ No such user found. Password not updated.")
        else:
            vulnerable_update_password(username, new_password)
            st.success("⚠️ Password updated (vulnerable)")

elif menu == "Delete User":
    if st.button("Delete User"):
        if secure_mode:
            deleted = secure_delete_user(username)
            if deleted:
                st.success("✅ User deleted securely")
            else:
                st.warning("⚠️ No such user found")
        else:
            vulnerable_delete_user(username)
            st.success("⚠️ User deleted (vulnerable)")



# Show current users
if st.checkbox("👁 View Users Table"):
    cursor.execute("SELECT * FROM users")
    st.table(cursor.fetchall())
