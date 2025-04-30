**AIM:**

To build a simple blog post web app using Flask where users can submit and view comments, while also demonstrating how cross-site scripting (XSS) vulnerabilities can occur and how to prevent them using input sanitization (escape) and safe output rendering.

**ALGORITHM:**

**1.  Initialize the Flask app and a global list to store comments.**

**2.  Define the home route ('/'):**
-	Render the index.html template.
-	Pass the list of comments to be displayed on the page.
  
**3.  Define the route /add_comment to handle POST requests:**
-	Retrieve the user-submitted comment from the form.
-	Optionally escape the comment (secure version) to prevent XSS.
-	Append the comment to the global list.
-	Re-render the page with updated comments.
  
**4.  In the HTML template (index.html):**
-	Create a form to submit comments.
-	Loop through the comments and display them on the page.
-	Use {{ comment | safe }} for demonstration (vulnerable) or {{ comment }} (secure).
  
**5.  Run the app using app.run(debug=True).**

**OUTPUT:**

**Vulnerable Site**

![image](https://github.com/user-attachments/assets/b96f3b22-91b0-4fc8-882f-82f191d3e610)
![image](https://github.com/user-attachments/assets/347f070a-2171-48d9-8931-2212dddfe8ab)
![image](https://github.com/user-attachments/assets/8e3a1301-eae2-4ccb-9e80-eccc11ad3ad9)


**Secure Site**

![image](https://github.com/user-attachments/assets/e21d9de5-a359-4601-9427-68c83e7dbd55)
![image](https://github.com/user-attachments/assets/12cdff7e-b6c3-4830-a39c-9d801ca43c4a)

