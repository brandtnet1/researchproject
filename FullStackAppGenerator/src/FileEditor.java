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
			
			writeAppJS();
			writeUsersJS();
			writeIndexJS();
			writeAuthControllerJS();
			writeLayoutJade();
			writeLoginJade();
			writeRegisterJade();
			
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
	
	protected void writeAppJS() {
		appendToFile("../../webapp/app/app.js", "var mongoose = require('mongoose');");
		appendToFile("../../webapp/app/app.js", "mongoose.Promise = global.Promise;");
		appendToFile("../../webapp/app/app.js", "mongoose.connect('mongodb://localhost/node-auth').then(() => console.log('connection successful')).catch((err) => console.error(err));");
		appendToFile("../../webapp/app/app.js", "var passport = require('passport');");
		appendToFile("../../webapp/app/app.js", "var LocalStrategy = require('passport-local').Strategy;");
		
		appendToFile("../../webapp/app/app.js", "app.use(require('express-session')({");
		appendToFile("../../webapp/app/app.js", "    secret: 'keyboard cat',");
		appendToFile("../../webapp/app/app.js", "    resave: false,");
		appendToFile("../../webapp/app/app.js", "    saveUninitialized: false");
		appendToFile("../../webapp/app/app.js", "}));");
		appendToFile("../../webapp/app/app.js", "app.use(passport.initialize());");
		appendToFile("../../webapp/app/app.js", "app.use(passport.session());");
		
		appendToFile("../../webapp/app/app.js", "var User = require('./models/User');");
		appendToFile("../../webapp/app/app.js", "passport.use(new LocalStrategy(User.authenticate()));");
		appendToFile("../../webapp/app/app.js", "passport.serializeUser(User.serializeUser());");
		appendToFile("../../webapp/app/app.js", "passport.deserializeUser(User.deserializeUser());");
	}
	
	protected void writeUsersJS() {
		
		File models = new File("../../webapp/app/models");
		models.mkdir();
		File usermodels = new File("../../webapp/app/models/User.js");
		
		appendToFile("../../webapp/app/models/User.js", "var mongoose = require('mongoose');");
		appendToFile("../../webapp/app/models/User.js", "var Schema = mongoose.Schema;");
		appendToFile("../../webapp/app/models/User.js", "var passportLocalMongoose = require('passport-local-mongoose');");
		appendToFile("../../webapp/app/models/User.js", "var UserSchema = new Schema({ username: String, password: String });");
		appendToFile("../../webapp/app/models/User.js", "UserSchema.plugin(passportLocalMongoose);");
		appendToFile("../../webapp/app/models/User.js", "module.exports = mongoose.model('User', UserSchema);");
	}
	
	protected void writeIndexJS() {
		File index = new File("../../webapp/app/routes/index.js");
		index.delete();
		index = new File("../../webapp/app/routes/index.js");
		
		appendToFile("../../webapp/app/routes/index.js", "var express = require('express');");
		appendToFile("../../webapp/app/routes/index.js", "var router = express.Router();");
		appendToFile("../../webapp/app/routes/index.js", "var auth = require('../controllers/AuthController.js');");
		
		appendToFile("../../webapp/app/routes/index.js", "router.get('/', auth.home);");
		
		appendToFile("../../webapp/app/routes/index.js", "router.get('/register', auth.register);");
		appendToFile("../../webapp/app/routes/index.js", "router.post('/register', auth.doRegister);");
		
		appendToFile("../../webapp/app/routes/index.js", "router.get('/login', auth.login);");
		appendToFile("../../webapp/app/routes/index.js", "router.post('/login', auth.doLogin);");
		
		appendToFile("../../webapp/app/routes/index.js", "router.get('/logout', auth.logout);");
		
		appendToFile("../../webapp/app/routes/index.js", "module.exports = router;");
	}
	
	protected void writeAuthControllerJS() {
		File controllers = new File("../../webapp/app/controllers");
		controllers.mkdir();
		File authcontroller = new File("../../webapp/app/controllers/AuthController.js");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "var mongoose = require('mongoose');");
		appendToFile("../../webapp/app/controllers/AuthController.js", "var passport = require('passport');");
		appendToFile("../../webapp/app/controllers/AuthController.js", "var User = require('../models/User');");
		appendToFile("../../webapp/app/controllers/AuthController.js", "var userController = {};");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.home = function(req, res) { res.render('index', { user : req.user }); };");
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.register = function(req, res) { res.render('register') };");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.doRegister = function(req, res) { ");
		appendToFile("../../webapp/app/controllers/AuthController.js", "  User.register(new User({ username : req.body.username, name : req.body.name }), req.body.password, function(err, user) {");
		appendToFile("../../webapp/app/controllers/AuthController.js", "    if (err) {");
		appendToFile("../../webapp/app/controllers/AuthController.js", "      return res.render('register', { user : user });");
		appendToFile("../../webapp/app/controllers/AuthController.js", "    }");
		appendToFile("../../webapp/app/controllers/AuthController.js", "    passport.authenticate('local')(req, res, function() {");
		appendToFile("../../webapp/app/controllers/AuthController.js", "      res.redirect('/');");
		appendToFile("../../webapp/app/controllers/AuthController.js", "    });");
		appendToFile("../../webapp/app/controllers/AuthController.js", "  });");
		appendToFile("../../webapp/app/controllers/AuthController.js", "};");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.login = function(req, res) { res.render('login') };");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.doLogin = function(req, res) { ");
		appendToFile("../../webapp/app/controllers/AuthController.js", "  passport.authenticate('local')(req, res, function() {");
		appendToFile("../../webapp/app/controllers/AuthController.js", "    res.render('index', { user : res.req.user });");
		appendToFile("../../webapp/app/controllers/AuthController.js", "  });");
		appendToFile("../../webapp/app/controllers/AuthController.js", "};");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "userController.logout = function(req, res) { req.logout; res.redirect('/') };");
		
		appendToFile("../../webapp/app/controllers/AuthController.js", "module.exports = userController;");
	}
	
	protected void writeLayoutJade() {
		
		File layout = new File("../../webapp/app/views/layout.jade");
		layout.delete();
		layout = new File("../../webapp/app/views/layout.jade");
		
		appendToFile("../../webapp/app/views/layout.jade", "doctype html");
		appendToFile("../../webapp/app/views/layout.jade", "html");
		appendToFile("../../webapp/app/views/layout.jade", "  head");
		appendToFile("../../webapp/app/views/layout.jade", "    title Test Site");
		appendToFile("../../webapp/app/views/layout.jade", "  body");
		appendToFile("../../webapp/app/views/layout.jade", "    div");
		appendToFile("../../webapp/app/views/layout.jade", "      h1 Test Site:");
		appendToFile("../../webapp/app/views/layout.jade", "      a(href='/') Home");
		
		appendToFile("../../webapp/app/views/layout.jade", "      if(!user)");
		appendToFile("../../webapp/app/views/layout.jade", "        a(href='/login') Login");
		appendToFile("../../webapp/app/views/layout.jade", "        a(href='/register') Register");
		
		appendToFile("../../webapp/app/views/layout.jade", "      if(user)");
		appendToFile("../../webapp/app/views/layout.jade", "        a(href='/logout') logout");
		
		appendToFile("../../webapp/app/views/layout.jade", "      #[br]");
		appendToFile("../../webapp/app/views/layout.jade", "      h1 Welcome #{user.username}");
		
		appendToFile("../../webapp/app/views/layout.jade", "    div.container");
		appendToFile("../../webapp/app/views/layout.jade", "      div.content");
		appendToFile("../../webapp/app/views/layout.jade", "        block content");
	}
	
	protected void writeLoginJade() {
		
		File layout = new File("../../webapp/app/views/login.jade");

		appendToFile("../../webapp/app/views/layout.jade", "extends layout");
		appendToFile("../../webapp/app/views/layout.jade", "block content");
		appendToFile("../../webapp/app/views/layout.jade", "  .container");
		appendToFile("../../webapp/app/views/layout.jade", "    form.form-signin(role='form', action='/login', method='post')");
		appendToFile("../../webapp/app/views/layout.jade", "      h2.form-signin-heading Please sign in");
		appendToFile("../../webapp/app/views/layout.jade", "      label.sr-only(for='inputEmail')");
		appendToFile("../../webapp/app/views/layout.jade", "      input.form-control(type='text', name='username', id='inputEmail', placeholder='Username', required, autofocus)");
		appendToFile("../../webapp/app/views/layout.jade", "      input.form-control(type='password', name='password', id='inputPassword', placeholder='Password')");
		appendToFile("../../webapp/app/views/layout.jade", "      button.btn.btn-lg.btn-primary.btn-block(type='submit') LOGIN");
		
	}
	
	protected void writeRegisterJade() {
		
		File layout = new File("../../webapp/app/views/register.jade");

		appendToFile("../../webapp/app/views/register.jade", "extends layout");
		appendToFile("../../webapp/app/views/register.jade", "block content");
		appendToFile("../../webapp/app/views/register.jade", "  .container");
		appendToFile("../../webapp/app/views/register.jade", "    form.form-signin(role='form', action='/register', method='post', style='max-width: 300px;')");
		appendToFile("../../webapp/app/views/register.jade", "      h2.form-signin-heading Sign up here");
		appendToFile("../../webapp/app/views/register.jade", "      input.form-control(type='text', name='name', placeholder='Your Name')");
		appendToFile("../../webapp/app/views/register.jade", "      input.form-control(type='text', name='username', placeholder='Your Username')");
		appendToFile("../../webapp/app/views/register.jade", "      input.form-control(type='password', name='password', placeholder='Your Password')");
		appendToFile("../../webapp/app/views/register.jade", "      button.btn.btn-lg.btn-primary.btn-block(type='submit') Sign Up");
		
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
