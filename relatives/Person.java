package relatives;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Person {
	private final String name;
    private final String surname;
    private final ArrayList<Marriage> marriages = new ArrayList<>();
    private Marriage parents;
    private LocalDate birthDate;
    private LocalDate deathDate = null; // can be null
    private String birthPlace;
    private String deathPlace = null; // can be null

    private Person(String name, String surname, Marriage parents) {
        this.name = name;
        this.surname = surname;
        this.parents = parents;
    }

    public static Person createPerson(String name, String surname, Marriage parents) {
        Person p = new Person(name, surname, parents);
        parents.addChild(p); //this is why we need a static factory method
        return p;
    }

    public static Person createPerson(String name, String surname) {
        return new Person(name, surname, null);
    }

    public void setParents(Marriage parents) {
        this.parents = parents;
    }

    public void addMarriage(Marriage marriage) {
        marriages.add(marriage);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public int getNumberOfMarriages() {
        return marriages.size();
    }
    
    public Marriage getNthMarriage(int index) {
        return marriages.get(index);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Optional<LocalDate> getDeathDate() {
        return Optional.ofNullable(deathDate);
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Optional<String> getDeathPlace() {
        return Optional.ofNullable(deathPlace);
    }

    public void setDeathPlace(String deathPlace) {
        this.deathPlace = deathPlace;
    }
    
    public Marriage getParents() {
    	return parents;
    }
    
    public static void main(String args[]) {
    	String name = "Name";
    	String surname = "Surname";
    	Person person = createPerson(name, surname);
    	if (!name.contentEquals(person.name)) {
    		System.out.println("test failed: name mismatching");
    	}
    	if (!surname.contentEquals(person.surname)) {
    		System.out.println("test failed: surname mismatching");
    	}
    	
    	Person wife = createPerson("wife_name", "wife_surname");
    	Marriage marriage = Marriage.createMarriage(person, wife);
    	if (person.getNumberOfMarriages() != 1) {
    		System.out.println("test failed: person not having 1 marriage");
    	}
    	if (wife.getNumberOfMarriages() != 1) {
    		System.out.println("test failed: wife not having 1 marriage");
    	}
    	if (person.getNthMarriage(0) != marriage) {
    		System.out.println("test failed: person's marriage mismatching");
    	}
    	if (wife.getNthMarriage(0) != marriage) {
    		System.out.println("test failed: wife's marriage mismatching");
    	}
    	if (marriage.getOther(person) != wife) {
    		System.out.println("test failed: wife not in marriage");
    	}
    	if (marriage.getOther(wife) != person) {
    		System.out.println("test failed: person not in marriage");
    	}
    	if (marriage.getNumberOfChildren() != 0) {
    		System.out.println("test failed: marriage not having 0 children");
    	}
        try {
            marriage.getOther(Person.createPerson("", "");
            System.out.println("test failed: getOther did not throw exception");
        } catch (RuntimeException e) {}
    	
    	Person son = createPerson("son_name", "son_surname", marriage);
    	if (son.getParents() != marriage) {
    		System.out.println("test failed: son's parents mismatching");
    	}
    	if (marriage.getNumberOfChildren() != 1) {
    		System.out.println("test failed: marriage not having 1 children");
    	}
    	if (marriage.getNthChild(0) != son) {
    		System.out.println("test failed: son mismatching");
    	}
    	
    	Person son2 = createPerson("son2_name", "son2_surname");
    	marriage.addChild(son2);
    	if (son2.getParents() != marriage) {
    		System.out.println("test failed: son2's parents mismatching");
    	}
    	if (marriage.getNumberOfChildren() != 2) {
    		System.out.println("test failed: marriage not having 2 children");
    	}
    	if (marriage.getNthChild(1) != son2) {
    		System.out.println("test failed: son2 mismatching");
    	}
    	
    	System.out.println("All Person tests executed");
    }
}
