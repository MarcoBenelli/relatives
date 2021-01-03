package relatives;

public class DeathRepresentator implements PersonRepresentator {

	private final PersonRepresentator personRepresentator;

	public DeathRepresentator(PersonRepresentator personRepresentator) {
		this.personRepresentator = personRepresentator;
	}

	@Override
	public String represent(Person p) {

		if (p.getDeathDate().isPresent()) {
			return personRepresentator.represent(p);
		} else {
			return "";
		}

	}
}
