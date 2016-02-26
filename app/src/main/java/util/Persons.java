package util;

public class Persons {
	int id;
	String personName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return personName.toString();
	}
}
