package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model{
	
	@Id
	@Required
	public String email;	
	@Required
	public String name;
	@Required
	public String password;
	
	public static Model.Finder<String, User> finder = new Finder(String.class, User.class);
	
	public static User authenticate(String email, String password){
		return finder.where().eq("email", email).eq("password", password).findUnique();
	}
	
}
