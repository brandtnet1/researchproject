import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileEditor {
    
    ArrayList<String> routes = new ArrayList<String>();

	public FileEditor(int framework) {
		
		// WebApp file path = ../../webapp
		
		if(framework == 1){
			
			File webapp = new File("../../webapp/.flaskenv");
			appendToFile("../../webapp/.flaskenv", "FLASK_APP=site.py");
			
			writeConfigFile();
		    writeSiteFile();
		    writeInitFile();
		    writeFormsFile();
		    writeModelsFile();
		    writeRoutesFile();
		    writeBaseHTML();
		    writeIndexHTML();
		    writeLoginHTML();
		    writeRegisterHTML();
		    
		} else {
			
		}


	    
	}
	
	protected void writeConfigFile() {
		appendToFile("../../webapp/config.py", "import os");
		appendToFile("../../webapp/config.py", "basedir = os.path.abspath(os.path.dirname(__file__))");
		
		appendToFile("../../webapp/config.py", "class Config(object):");
		appendToFile("../../webapp/config.py", "    SECRET_KEY = os.environ.get('SECRET_KEY') or 'random'");
		appendToFile("../../webapp/config.py", "    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or 'sqlite:///' + os.path.join(basedir, 'app.db')");
		appendToFile("../../webapp/config.py", "    SQLALCHEMY_TRACK_MODIFICATIONS = False");
	}
	
	protected void writeSiteFile() {
		appendToFile("../../webapp/site.py", "from app import app, db");
		appendToFile("../../webapp/site.py", "from app.models import User");
		
		appendToFile("../../webapp/site.py", "@app.shell_context_processor");
		appendToFile("../../webapp/site.py", "def make_shell_context_processor():");
		appendToFile("../../webapp/site.py", "    return {'db':db, 'User': User}");
		
		appendToFile("../../webapp/site.py", "app.run(host='0.0.0.0', port=8080, debug=True)");
	}
	
	protected void writeInitFile() {
		appendToFile("../../webapp/app/__init__.py", "from flask import Flask");
		appendToFile("../../webapp/app/__init__.py", "from config import Config");
		appendToFile("../../webapp/app/__init__.py", "from flask_sqlalchemy import SQLAlchemy");
		appendToFile("../../webapp/app/__init__.py", "from flask_migrate import Migrate");
		appendToFile("../../webapp/app/__init__.py", "from flask_login import LoginManager");
		appendToFile("../../webapp/app/__init__.py", "app = Flask(__name__)");
		appendToFile("../../webapp/app/__init__.py", "app.config.from_object(Config)");
		appendToFile("../../webapp/app/__init__.py", "db = SQLAlchemy(app)");
		appendToFile("../../webapp/app/__init__.py", "migrate = Migrate(app, db)");
		appendToFile("../../webapp/app/__init__.py", "login = LoginManager(app)");
		appendToFile("../../webapp/app/__init__.py", "login.login_view = 'login'");
		appendToFile("../../webapp/app/__init__.py", "from app import routes, models");
	}
	
	protected void writeFormsFile() {
		appendToFile("../../webapp/app/forms.py", "from flask_wtf import FlaskForm");
		appendToFile("../../webapp/app/forms.py", "from wtforms import StringField, PasswordField, BooleanField, SubmitField");
		appendToFile("../../webapp/app/forms.py", "from wtforms.validators import DataRequired, ValidationError, Email, EqualTo");
		appendToFile("../../webapp/app/forms.py", "from app.models import User");
		
		appendToFile("../../webapp/app/forms.py", "class LoginForm(FlaskForm):");
		appendToFile("../../webapp/app/forms.py", "    username = StringField('Username', validators = [DataRequired()])");
		appendToFile("../../webapp/app/forms.py", "    password = PasswordField('Password', validators = [DataRequired()])");
		appendToFile("../../webapp/app/forms.py", "    remember_me = BooleanField('Remember Me')");
		appendToFile("../../webapp/app/forms.py", "    submit = SubmitField('Sign In')");
		
		appendToFile("../../webapp/app/forms.py", "class RegistrationForm(FlaskForm):");
		appendToFile("../../webapp/app/forms.py", "    username = StringField('Username', validators = [DataRequired()])");
		appendToFile("../../webapp/app/forms.py", "    email = StringField('Email', validators = [DataRequired()])");
		appendToFile("../../webapp/app/forms.py", "    password = PasswordField('Password', validators = [DataRequired()])");
		appendToFile("../../webapp/app/forms.py", "    password2 = PasswordField('Repeat Password', validators = [DataRequired(), EqualTo('password')])");
		appendToFile("../../webapp/app/forms.py", "    submit = SubmitField('Register')");
		
		appendToFile("../../webapp/app/forms.py", "    def validate_username(self, username):");
		appendToFile("../../webapp/app/forms.py", "        user = User.query.filter_by(username = username.data).first()");
		appendToFile("../../webapp/app/forms.py", "        if user is not None:");
		appendToFile("../../webapp/app/forms.py", "            raise ValidationError('Please use a different username.')");
		
		appendToFile("../../webapp/app/forms.py", "    def validate_email(self, email):");
		appendToFile("../../webapp/app/forms.py", "        user = User.query.filter_by(email = email.data).first()");
		appendToFile("../../webapp/app/forms.py", "        if user is not None:");
		appendToFile("../../webapp/app/forms.py", "            raise ValidationError('Please use a different email.')");
	}
	
	protected void writeModelsFile() {
		appendToFile("../../webapp/app/models.py", "from datetime import datetime");
		appendToFile("../../webapp/app/models.py", "from werkzeug.security import generate_password_hash, check_password_hash");
		appendToFile("../../webapp/app/models.py", "from flask_login import UserMixin");
		appendToFile("../../webapp/app/models.py", "from app import db, login");
		
		appendToFile("../../webapp/app/models.py", "@login.user_loader");
		appendToFile("../../webapp/app/models.py", "def load_user(id):");
		appendToFile("../../webapp/app/models.py", "    return User.query.get(int(id))");
		
		appendToFile("../../webapp/app/models.py", "class User(UserMixin, db.Model):");
		appendToFile("../../webapp/app/models.py", "    id = db.Column(db.Integer, primary_key = True)");
		appendToFile("../../webapp/app/models.py", "    username = db.Column(db.String(64), index = True, unique = True)");
		appendToFile("../../webapp/app/models.py", "    email = db.Column(db.String(124), index = True, unique = True)");
		appendToFile("../../webapp/app/models.py", "    password_hash = db.Column(db.String(124))");

		appendToFile("../../webapp/app/models.py", "    def __repr__(self):");
		appendToFile("../../webapp/app/models.py", "        return '<User {}>'.format(self.username)");

		appendToFile("../../webapp/app/models.py", "    def set_password(self, password):");
		appendToFile("../../webapp/app/models.py", "        self.password_hash = generate_password_hash(password)");
		
		appendToFile("../../webapp/app/models.py", "    def check_password(self, password):");
		appendToFile("../../webapp/app/models.py", "        return check_password_hash(self.password_hash, password)");
	}
	
	protected void writeRoutesFile() {
		appendToFile("../../webapp/app/routes.py", "from flask import render_template, flash, redirect, url_for, request");
		appendToFile("../../webapp/app/routes.py", "from flask_login import current_user, login_user, logout_user, login_required");
		appendToFile("../../webapp/app/routes.py", "from werkzeug.urls import url_parse");
		appendToFile("../../webapp/app/routes.py", "from app import app, db");
		appendToFile("../../webapp/app/routes.py", "from app.forms import LoginForm, RegistrationForm");
		appendToFile("../../webapp/app/routes.py", "from app.models import User");
		
		appendToFile("../../webapp/app/routes.py", "@app.route('/')");
		appendToFile("../../webapp/app/routes.py", "@app.route('/index')");
		appendToFile("../../webapp/app/routes.py", "@login_required");
		appendToFile("../../webapp/app/routes.py", "def index():");
		appendToFile("../../webapp/app/routes.py", "    user = {'username': ''}");
		appendToFile("../../webapp/app/routes.py", "    return render_template('index.html', title = 'Home', user = user)");
		
		appendToFile("../../webapp/app/routes.py", "@app.route('/login', methods = ['GET', 'POST'])");
		appendToFile("../../webapp/app/routes.py", "def login():");
		appendToFile("../../webapp/app/routes.py", "    if current_user.is_authenticated:");
		appendToFile("../../webapp/app/routes.py", "        return redirect(url_for('index'))");
		appendToFile("../../webapp/app/routes.py", "    form = LoginForm()");
		appendToFile("../../webapp/app/routes.py", "    if form.validate_on_submit():");
		appendToFile("../../webapp/app/routes.py", "        user = User.query.filter_by(username = form.username.data).first()");
		appendToFile("../../webapp/app/routes.py", "        if user is None or not user.check_password(form.password.data):");
		appendToFile("../../webapp/app/routes.py", "            flash('Invalid username or password')");
		appendToFile("../../webapp/app/routes.py", "            return redirect(url_for('login'))");
		appendToFile("../../webapp/app/routes.py", "        login_user(user, remember = form.remember_me.data)");
		appendToFile("../../webapp/app/routes.py", "        next_page = request.args.get('next')");
		appendToFile("../../webapp/app/routes.py", "        if not next_page or url_parse(next_page).netloc != '':");
		appendToFile("../../webapp/app/routes.py", "            next_page = url_for('index')");
		appendToFile("../../webapp/app/routes.py", "        return redirect(next_page)");
		appendToFile("../../webapp/app/routes.py", "    return render_template('login.html', title = 'Sign In', form = form)");
		
		appendToFile("../../webapp/app/routes.py", "@app.route('/logout')");
		appendToFile("../../webapp/app/routes.py", "def logout():");
		appendToFile("../../webapp/app/routes.py", "    logout_user()");
		appendToFile("../../webapp/app/routes.py", "    return redirect(url_for('index'))");
		
		appendToFile("../../webapp/app/routes.py", "@app.route('/register', methods = ['GET', 'POST'])");
		appendToFile("../../webapp/app/routes.py", "def register():");
		appendToFile("../../webapp/app/routes.py", "    if current_user.is_authenticated:");
		appendToFile("../../webapp/app/routes.py", "        return redirect(url_for('index'))");
		appendToFile("../../webapp/app/routes.py", "    form = RegistrationForm()");
		appendToFile("../../webapp/app/routes.py", "    if form.validate_on_submit():");
		appendToFile("../../webapp/app/routes.py", "        user = User(username = form.username.data, email = form.email.data)");
		appendToFile("../../webapp/app/routes.py", "        user.set_password(form.password.data)");
		appendToFile("../../webapp/app/routes.py", "        db.session.add(user)");
		appendToFile("../../webapp/app/routes.py", "        db.session.commit()");
		appendToFile("../../webapp/app/routes.py", "        flash('Congratulations, you are now a registered user!')");
		appendToFile("../../webapp/app/routes.py", "        return redirect(url_for('login'))");
		appendToFile("../../webapp/app/routes.py", "    return render_template('register.html', title = 'Register', form = form)");
	}
	
	protected void writeBaseHTML() {
		File webapp = new File("../../webapp/app/templates/base.html");
		
		appendToFile("../../webapp/app/templates/base.html", "<html>");
		appendToFile("../../webapp/app/templates/base.html", "    <head>");
		appendToFile("../../webapp/app/templates/base.html", "      {% if title %}");
		appendToFile("../../webapp/app/templates/base.html", "      <title>{{ title }} - Test Site</title>");
		appendToFile("../../webapp/app/templates/base.html", "      {% else %}");
		appendToFile("../../webapp/app/templates/base.html", "      <title>Test Site</title>");
		appendToFile("../../webapp/app/templates/base.html", "      {% endif %}");
		appendToFile("../../webapp/app/templates/base.html", "    </head>");
		appendToFile("../../webapp/app/templates/base.html", "    <body>");
		appendToFile("../../webapp/app/templates/base.html", "      <div>");
		appendToFile("../../webapp/app/templates/base.html", "        Test Site:");
		appendToFile("../../webapp/app/templates/base.html", "        <a href=\"{{ url_for('index') }}\">Home</a>");
		appendToFile("../../webapp/app/templates/base.html", "        {% if current_user.is_anonymous %}");
		appendToFile("../../webapp/app/templates/base.html", "        <a href=\"{{ url_for('login') }}\">Login</a>");
		appendToFile("../../webapp/app/templates/base.html", "        {% else %}");
		appendToFile("../../webapp/app/templates/base.html", "        <a href=\"{{ url_for('logout') }}\">Logout</a>");
		appendToFile("../../webapp/app/templates/base.html", "        {% endif %}");		
		appendToFile("../../webapp/app/templates/base.html", "      </div>");
		appendToFile("../../webapp/app/templates/base.html", "      {% block content %}{% endblock %}");
		appendToFile("../../webapp/app/templates/base.html", "    </body>");
		appendToFile("../../webapp/app/templates/base.html", "</html>");
	}
	
	protected void writeIndexHTML() {
		File webapp = new File("../../webapp/app/templates/index.html");
		
		appendToFile("../../webapp/app/templates/index.html", "{% extends \"base.html\" %}");
		appendToFile("../../webapp/app/templates/index.html", "{% block content %}");
		appendToFile("../../webapp/app/templates/index.html", "  <h1>Hi, {{ user.username }}!</h1>");
		appendToFile("../../webapp/app/templates/index.html", "  {% for post in posts %}");
		appendToFile("../../webapp/app/templates/index.html", "  <div><p>{{ post.author.username }} says: <b>{{ post.body }}</b></p></div>");
		appendToFile("../../webapp/app/templates/index.html", "  {% endfor %}");
		appendToFile("../../webapp/app/templates/index.html", "{% endblock %}");
	}
	
	protected void writeLoginHTML() {
		File webapp = new File("../../webapp/app/templates/login.html");
		
		appendToFile("../../webapp/app/templates/login.html", "{% extends \"base.html\" %}");
		appendToFile("../../webapp/app/templates/login.html", "{% block content %}");
		appendToFile("../../webapp/app/templates/login.html", "  <h1>Sign In</h1>");
		appendToFile("../../webapp/app/templates/login.html", "  <form action=\"\" method=\"post\" novalidate>");
		appendToFile("../../webapp/app/templates/login.html", "    {{ form.hidden_tag() }}");
		appendToFile("../../webapp/app/templates/login.html", "    <p>");
		appendToFile("../../webapp/app/templates/login.html", "      {{ form.username.label }}<br>");
		appendToFile("../../webapp/app/templates/login.html", "      {{ form.username(size=32) }}<br>");
		appendToFile("../../webapp/app/templates/login.html", "      {% for error in form.username.errors %}");
		appendToFile("../../webapp/app/templates/login.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/login.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/login.html", "    </p>");
		appendToFile("../../webapp/app/templates/login.html", "    <p>");
		appendToFile("../../webapp/app/templates/login.html", "      {{ form.password.label }}<br>");
		appendToFile("../../webapp/app/templates/login.html", "      {{ form.password(size=32) }}<br>");
		appendToFile("../../webapp/app/templates/login.html", "      {% for error in form.password.errors %}");
		appendToFile("../../webapp/app/templates/login.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/login.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/login.html", "    </p>");
		appendToFile("../../webapp/app/templates/login.html", "    <p>{{ form.remember_me() }} {{ form.remember_me.label }}</p>");
		appendToFile("../../webapp/app/templates/login.html", "    <p>{{ form.submit() }}</p>");
		appendToFile("../../webapp/app/templates/login.html", "  </form>");
		appendToFile("../../webapp/app/templates/login.html", "  <p>Need an account? <a href=\"{{ url_for('register') }}\">Click to Register!</a></p>");
		appendToFile("../../webapp/app/templates/login.html", "{% endblock %}");
	}
	
	protected void writeRegisterHTML() {
		File webapp = new File("../../webapp/app/templates/register.html");
		
		appendToFile("../../webapp/app/templates/register.html", "{% extends \"base.html\" %}");
		appendToFile("../../webapp/app/templates/register.html", "{% block content %}");
		appendToFile("../../webapp/app/templates/register.html", "  <h1>Register</h1>");
		appendToFile("../../webapp/app/templates/register.html", "  <form action=\"\" method=\"post\">");
		appendToFile("../../webapp/app/templates/register.html", "    {{ form.hidden_tag() }}");
		appendToFile("../../webapp/app/templates/register.html", "    <p>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.username.label }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.username(size=32) }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {% for error in form.username.errors %}");
		appendToFile("../../webapp/app/templates/register.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/register.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/register.html", "    </p>");
		appendToFile("../../webapp/app/templates/register.html", "    <p>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.email.label }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.email(size=64) }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {% for error in form.email.errors %}");
		appendToFile("../../webapp/app/templates/register.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/register.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/register.html", "    </p>");
		appendToFile("../../webapp/app/templates/register.html", "    <p>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.password.label }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.password(size=32) }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {% for error in form.password.errors %}");
		appendToFile("../../webapp/app/templates/register.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/register.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/register.html", "    </p>");
		appendToFile("../../webapp/app/templates/register.html", "    <p>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.password2.label }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {{ form.password2(size=32) }}<br>");
		appendToFile("../../webapp/app/templates/register.html", "      {% for error in form.password2.errors %}");
		appendToFile("../../webapp/app/templates/register.html", "      <span style=\"color: red;\">[{{ error }}]</span>");
		appendToFile("../../webapp/app/templates/register.html", "      {% endfor %}");
		appendToFile("../../webapp/app/templates/register.html", "    </p>");
		appendToFile("../../webapp/app/templates/register.html", "    <p>{{ form.submit() }}</p>");
		appendToFile("../../webapp/app/templates/register.html", "  </form>");
		appendToFile("../../webapp/app/templates/register.html", "{% endblock %}");
	}
	
	protected String getFilePath(String fileName) {
	    return fileName;
	}
	
	public void appendToFile (String filePath, String text) {

        BufferedWriter bw = null;

        try {
             bw = new BufferedWriter(new FileWriter(filePath, true));
        	 bw.write(text);
        	 bw.newLine();
        	 bw.flush();
        } catch (IOException ioe) {
	        ioe.printStackTrace();
        } finally {
            if (bw != null) {
    	        try {
    	            bw.close();
    	        } catch (IOException ioe2) {
    	            System.out.println(ioe2);
    	        }
    	 }
      } 
   }
}
