package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
public class User extends Model{
	
	@Id
	@Constraints.Required
	@Formats.NonEmpty
	public String email;	
	
	@Constraints.Required
	@Formats.NonEmpty
	public String name;
	
	@Constraints.Required
	@Formats.NonEmpty
	public String password;
	
	public User() {
		super();
	}

	public User(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public static Model.Finder<String, User> finder = new Finder(String.class, User.class);
	
    public static User findByEmail(String email) {
        return finder.where().eq("email", email).findUnique();
    }
	
	public static User authenticate(String email, String password){
		return finder.where().eq("email", email).eq("password", password).findUnique();
	}
	
}
