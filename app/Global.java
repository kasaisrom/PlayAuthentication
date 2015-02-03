import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if (User.finder.findRowCount() == 0) {
				
				Map<String, User> users = new HashMap<String,User>();

				// Insert users
				//Ebean.save(() Yaml.load("data.yml"));
			}
		}
	}
}