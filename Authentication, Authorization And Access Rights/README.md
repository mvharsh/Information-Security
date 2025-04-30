**AIM:**

To implement a simple authentication and authorization system using Python. It securely stores user credentials using SHA-256 hashing and assigns role-based access control (RBAC) to manage different levels of permissions.
<br><br>
**ALGORITHM:**

  1.	Define a password hashing function using SHA-256 to ensure secure password storage.
  2.	Create a dictionary of users with pre-defined usernames, hashed passwords, and assigned roles.
  3.	Authenticate users:
  -	Accept username and password input.
  -	Hash the input password and compare it with the stored hashed password.
  -	If matched, authentication is successful.
  4.	Authorize user actions:
  -	If the user is an admin, they have full access (VIEW, EDIT, DELETE).
  -	If the user is a regular user, they can only perform the VIEW action.
  -	Otherwise, deny access.
  5.	Prompt the user for an action (VIEW/EDIT/DELETE).
  6.	Grant or deny access based on the user’s role.
  7.	Display appropriate messages for authentication success/failure and authorization decisions.
<br><br>

**OUTPUT:**
<br><br>
Enter Username: admin

Enter Password: ••••••••••

Authentication Successful!

Enter Action (VIEW/EDIT/DELETE): EDIT

Access Granted for action: EDIT
<br><br><br>
Enter Username: user

Enter Password: ••••••••••

Authentication Successful!

Enter Action (VIEW/EDIT/DELETE): EDIT

Access Denied!
<br><br><br>
Enter Username: user

Enter Password: ••••••••••

Authentication Successful!

Enter Action (VIEW/EDIT/DELETE): VIEW

Access Granted for action: VIEW
