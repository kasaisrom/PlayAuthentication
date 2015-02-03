package controllers;

import java.util.List;

import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import static play.libs.Json.toJson;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	public static Result addUser() {
		User user = Form.form(User.class).bindFromRequest().get();
		user.save();
		return redirect(routes.Application.index());
	}

	public static Result getUsers() {
		List<User> users = new Model.Finder(String.class, User.class).all();
		return ok(toJson(users));
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session("email", loginForm.get().email);
			return redirect(routes.Application.index());
		}
	}

	public static class Login {
		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return "Login failed";
			} else {
				return null;
			}
		}

	}
}
