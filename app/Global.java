import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			//if (Ebean.find(User.class).findRowCount() == 0) {
				//Map<String, List<Object>> all = (Map<String, List<Object>>)Yaml.load("data.yml");
                //Ebean.save(all.get("users"));
			//}
		}
	}
}