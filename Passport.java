package programs;

/**
 * This class represents a person's passport. It has three instance variables
 * representing the first, last and middle name (all are String variables). A
 * character instance variable representing a separator (to be used for
 * formatting purposes) is also part of the class. In addition, the class has a
 * StringBuffer instance variable that represents the passport stamps the person
 * has received.
 * 
 * For this class you need to define and use a private method called
 * validateAndFormat that takes a character as a parameter.
 * 
 * The class will keep track of the number of instances created by using a
 * private static field called objectCount.
 * 
 * @author CS
 *
 */
public class Passport {

	// instantiate private instance variables
	private String firstName;
	private String middleName;
	private String lastName;
	private char separator;
	private StringBuffer stamps;
	private static int objectCount = 0; // static because its class-oriented, not object

	public Passport(String firstname, String middlename, String lastname) {

		// first, convert first and last name to formatted style
		String firstFormatted = validateAndFormat(firstname);
		String lastFormatted = validateAndFormat(lastname);

		// check null for both
		if (firstFormatted == null || lastFormatted == null) {
			return;
		}

		// middle name is special case
		String middleFormatted = "";
		// must check if it's empty first
		if (middlename.isBlank() == false) {
			// only if not, convert to formatted style
			middleFormatted = validateAndFormat(middlename);
		}

		// check null
		if (middleFormatted == null) {
			return;
		}

		// all cases check, set instance variables
		this.firstName = firstFormatted;
		this.middleName = middleFormatted;
		this.lastName = lastFormatted;

		separator = ',';
		stamps = new StringBuffer();
		objectCount++;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {

		// check validity of middleName
		if (middleName != null && !middleName.isEmpty()) {
			return lastName + separator + firstName + separator + middleName;
		}

		return lastName + separator + firstName;
	}

	public Passport(String firstname, String lastname) {
		this(firstname, "", lastname);
	}

	public Passport() {
		this("SAMPLEFIRSTNAME", "SAMPLEMIDDLENAME", "SAMPLELASTNAME");
	}

	public Passport(Passport passport) {
		this(passport.firstName, passport.middleName, passport.lastName);
	}

	public Passport addStamp(String stamp) {

		if (stamp == null || stamp.isBlank() == true) {
			return this;
		} else {

			stamps.append(stamp);
		}

		return this;
	}

	public StringBuffer getStamps() {
		return new StringBuffer(this.stamps);
	}

	public char getSeparator() {
		return separator;
	}

	public boolean setSeparator(char separator) {

		// check validity of separator
		if (separator == '@' || Character.isSpaceChar(separator) || Character.isLetter(separator)) {
			return false;
		}

		this.separator = separator;
		return true;

	}

	public boolean equals(Object obj) {
		// check auto equals
		if (this == obj) {
			return true;
		}

		// if null, or if obj is not even a Passport
		if (obj == null || !(obj instanceof Passport)) {
			return false;
		}

		Passport p = (Passport) obj;
		return this.firstName.equals(p.firstName) 
				&& this.middleName.equals(p.middleName)
				&& this.lastName.equals(p.lastName);

	}

	public int compareTo(Passport passport) {

		if (passport == null) {
			throw new NullPointerException("Cannot compare to null Passport");
		}

		// compare last name then first name
		int lastNameComp = this.lastName.compareTo(passport.lastName);
		if (lastNameComp != 0) {
			return lastNameComp;
		}

		int firstNameComp = this.firstName.compareTo(passport.firstName);
		if (firstNameComp != 0) {
			return firstNameComp;
		}

		return this.middleName.compareTo(passport.middleName);

	}

	public static int getNumberOfPassportObjects() {
		return objectCount;
	}

	public static void resetNumberOfPassportObjects() {
		objectCount = 0;
	}

	public static Passport normalize(Passport passport, boolean uppercase) {
		if (passport == null) {
			return null;
		}

		// create new Passport to return
		Passport retPass = new Passport();
		if (uppercase == true) {
			retPass.firstName = passport.firstName.toUpperCase();
			retPass.middleName = passport.middleName.toUpperCase();
			retPass.lastName = passport.lastName.toUpperCase();
		} else {
			retPass.firstName = passport.firstName.toLowerCase();
			retPass.middleName = passport.middleName.toLowerCase();
			retPass.lastName = passport.lastName.toLowerCase();
		}

		// set new separator and new StringBuffer of stamps
		retPass.setSeparator(passport.getSeparator());
		retPass.stamps = new StringBuffer(passport.stamps);
		return retPass;
	}

	public boolean changeLastname(String lastname) {
		String formatted = validateAndFormat(lastname);

		if (formatted != null) {
			// only set if the formatted version is not null
			this.lastName = formatted;
			return true;
		}
		return false;
	}

	/*
	 * This method will generate and return a formatted string in lowercase with the
	 * first character in uppercase. The parameter is valid if it is not null and it
	 * is not blank according to the string method isBlank(). If the parameter is
	 * invalid, the method will return null and perform no further processing. If
	 * the parameter is valid, spaces surrounding the parameter will be removed, the
	 * string will be converted to lowercase, and the first character of the string
	 * (after spaces have been removed) will be in upper case. The following methods
	 * can be helpful during the implementation of this method:
	 * Character.toUpperCase, and the string methods charAt and substring.
	 * 
	 * You can test this method by initially defining it public; once you have
	 * tested it, make it private.
	 * 
	 */

	private static String validateAndFormat(String name) {
		// check validity
		if (name == null || name.isBlank()) {
			return null;
		}

		// get rid of black spaces
		name = name.trim().toLowerCase();
		// format first character uppercase, rest lower
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);

		return name;
	}
}