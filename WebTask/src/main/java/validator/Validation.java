package main.java.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import main.java.entity.User;

public class Validation {
	Logger log = (Logger) LogManager.getLogger();
	private static final String USER_NAME_PATTERN = "^[a-zA-Z0-9]{3,15}$";
	private static final String USER_FIRST_LAST_PATTERN = "^[a-zA-Z]{2,25}$";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private List<String> errors = new ArrayList<>();
	private Pattern pattern;

	public Validation() {

	}

	public List<String> checkUser(User user) {

		log.info("Validation User ".concat(user.toString()));
		pattern = Pattern.compile(USER_FIRST_LAST_PATTERN);
		if (pattern.matcher(user.getFirstName()).matches() == false) {
			errors.add("Invalid First Name");
		}
		pattern = Pattern.compile(USER_FIRST_LAST_PATTERN);
		if (pattern.matcher(user.getLastName()).matches() == false) {
			errors.add("Invalid Last Name");
		}
		pattern = Pattern.compile(USER_NAME_PATTERN);
		if (pattern.matcher(user.getLogin()).matches() == false) {
			errors.add("Invlid  username");
		}
		pattern = Pattern.compile(EMAIL_PATTERN);
		if (pattern.matcher(user.getEmail()).matches() == false) {
			errors.add("Invalid email");
		}
		pattern = Pattern.compile(USER_NAME_PATTERN);
		if (pattern.matcher(user.getPassword()).matches() == false) {
			errors.add("Invalid password (Only numbers and letters)");
		}
		return errors;

	}
}
