package controllers;

import models.User;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	public static Result GO_HOME = redirect(routes.Application.index());
	public static Result GO_ADMIN = redirect(routes.Admin.index());

	public static Result index() {
		String email = ctx().session().get("email");
		if (email != null) {
			User user = User.findByEmail(email);
			if (user != null) {
				return GO_ADMIN;
			} else {
				session().clear();
			}
		}
		return ok(index.render(Form.form(Login.class)));
	}

	public static Result logout() {
		session().clear();
		flash("success", Messages.get("youve.been.logged.out"));
		return GO_HOME;
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(index.render(loginForm));
		} else {
			session("email", loginForm.get().email);
			return GO_ADMIN;
		}
	}

	public static class Login {
		public String email;
		public String password;

		public String validate() {
			if (User.authenticate(email, password) == null) {
				return Messages.get("Invalid username or password");
			}
			return null;

		}
	}
}
