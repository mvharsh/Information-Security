from flask import Flask, render_template, request
from markupsafe import escape

app = Flask(__name__)
comments = []

@app.route('/')
def blog_post():
    return render_template('index.html', comments=comments)

# Vulnerable version of the add_comment

@app.route('/add_comment', methods=['POST'])
def add_comment():
    comment = request.form['comment']
    comments.append(comment)  # Potential XSS vulnerability
    return render_template('index.html', comments=comments)

# Secure version of the add_comment function 

# @app.route('/add_comment', methods=['POST'])
# def add_comment():
#     comment = escape(request.form['comment'])
#     comments.append(comment)  # XSS vulnerability fixed
#     return render_template('index.html', comments=comments)

if __name__ == "__main__":
    app.run(debug=True)
