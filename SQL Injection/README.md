**AIM:**

To demonstrate the impact of SQL Injection vulnerabilities in web applications by implementing both vulnerable and secure versions of common user account operations (Signup, Login, Update Password, Delete User) using SQLite and Streamlit.

**ALGORITHM:**

**1.  Start Streamlit App and initialize a SQLite database with a users table (username, password).**

**2.  Display UI options:** Signup, Login, Update Password, Delete User.

**3.  Allow user to toggle between Vulnerable and Secure Mode.**

**4.  Based on the selected action:**

**Signup:**
-	In Vulnerable Mode: Run raw SQL with string formatting (subject to injection).
-	In Secure Mode: Use parameterized queries to insert user securely.
  
**Login:**
-	In Vulnerable Mode: Match credentials using a raw SQL query.
-	In Secure Mode: Use prepared statements to safely authenticate.
  
**Update Password:**
-	In Vulnerable Mode: Update password using raw SQL.
-	In Secure Mode: Use parameterized query and confirm if the user exists before update.
  
**Delete User**:
-	In Vulnerable Mode: Delete user using raw SQL.
-	In Secure Mode: Check if user exists and then delete using parameterized query.
  
**5.  Display success, error, or warning messages based on the operation outcome**.

**6.  Optionally, show the entire users table to visualize the database state.**

**OUTPUT:**

**Case 1: Authentication Bypass via SQL Injection**

Vulnerable Code (SQL Injection bypasses authentication)
  
![image](https://github.com/user-attachments/assets/ef2bc375-faa8-47f3-a864-afcc0043b46d)
![image](https://github.com/user-attachments/assets/88377b91-daff-47ae-9808-387ca59fdc35)

Secure Code (SQL Injection prevented!)

![image](https://github.com/user-attachments/assets/b5e41d0f-710a-4716-b58e-b9944c85c1ef)
![image](https://github.com/user-attachments/assets/60205b9e-dd49-4a9e-b7db-6255f8eb5596)

**Case 2: Unauthorized Password Reset via SQL Injection**

Vulnerable Code (Attacker can hijack password of any user!) 

Secure Code (SQL Injection blocked — safe!)

![image](https://github.com/user-attachments/assets/43d85ea1-8c6a-44b8-89bc-05b69e9cd5f0)
![image](https://github.com/user-attachments/assets/75cd531e-9f99-4f6e-8d61-08a351c2db0e)

**Case 3: Insert/Delete via SQL Injection**

Vulnerable Code (Destructive injection succeeded!)

Secure Code (Secure code prevents multi-statement injection)

![image](https://github.com/user-attachments/assets/c78883cc-1eff-4196-b522-b3918e9cb4ac)
![image](https://github.com/user-attachments/assets/28d35aef-096c-4804-88d7-cdb2e64a071a)

**Case 4: Delete with OR-based SQL Injection**

Vulnerable Code (Attack succeeds via logic injection)

Secure Code (Injection blocked — safe delete)

![image](https://github.com/user-attachments/assets/3e5e5fe5-bd29-45d6-addf-a43a3934e4ef)
![image](https://github.com/user-attachments/assets/f511b8ea-feba-4bb0-9899-96fec7f1709c)


