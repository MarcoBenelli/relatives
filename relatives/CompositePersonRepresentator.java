package relatives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositePersonRepresentator implements PersonRepresentator {
	private final List<PersonRepresentator> children = new ArrayList<>();

	public CompositePersonRepresentator(PersonRepresentator... child) {
		children.addAll(Arrays.asList(child));
	}

	@Override
	public String represent(Person p) {
		String representation = "";
		for (PersonRepresentator child : children) {
			representation += child.represent(p);
		}
		return representation;
	}

	public void add(PersonRepresentator... child) {
		children.addAll(Arrays.asList(child));
	}
}
