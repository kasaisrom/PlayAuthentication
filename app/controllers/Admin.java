package controllers;

import static play.libs.Json.toJson;

import java.util.List;
import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.admin.index;

@Security.Authenticated(Secured.class)
public class Admin extends Controller{
	
	public static Result GO_ADMIN = redirect(routes.Admin.index());
	
	public static Result index() {
		return ok(index.render());
	}

	public static Result addUser() {
		User user = Form.form(User.class).bindFromRequest().get();
		user.save();
		return GO_ADMIN;
	}

	public static Result getUsers() {
		List<User> users = new Model.Finder(String.class, User.class).all();
		return ok(toJson(users));
	}

}
