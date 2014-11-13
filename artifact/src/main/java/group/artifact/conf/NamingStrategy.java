package group.artifact.conf;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class NamingStrategy extends ImprovedNamingStrategy {

	public String classToTableName(String className) {
		className = super.classToTableName(className);

		if (className.substring(className.length() - 1).equals("s")) {
			return className + "es";

		} else if (className.substring(className.length() - 1).equals("y")) {
			return className.substring(0, className.length() - 1) + "ies";

		} else if (className.substring(className.length() - 1).equals("x")) {
			return className + "es";
			
		} else if (className.substring(className.length() - 2).equals("ch")) {
			return className + "es";
			
		} else {
			return className + "s";
		}
	}

	public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
		return super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName) + "_id";
	}
}
