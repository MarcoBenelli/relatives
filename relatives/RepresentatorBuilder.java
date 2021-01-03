package relatives;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RepresentatorBuilder {

    public enum DateFormat {
        NONE, YEAR, FULL;
    }

    public static PersonRepresentator createRepresentator(DateFormat date, boolean place) {
        CompositePersonRepresentator representator = new CompositePersonRepresentator(
                new CompositePersonRepresentator(Person::getName, p -> " ", Person::getSurname)
        );
        if (date != DateFormat.NONE || place) {
            representator.add(p -> " (");
            if (place) {
                representator.add(Person::getBirthPlace);
            }
            if (date != DateFormat.NONE && place) {
            	representator.add(p -> " ");
            }
            if (date != DateFormat.NONE) {
                PersonRepresentator pr = null;
                switch (date) {
                    case YEAR:
                        pr = p -> p.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy"));
                        break;
                    case FULL:
                        pr = p -> p.getBirthDate().toString();
                }
                representator.add(pr);
            }
            
            CompositePersonRepresentator cd = new CompositePersonRepresentator();
            cd.add(p -> " - ");
            if (place) {
                cd.add(p -> p.getDeathPlace().get());
            }
            if (date != DateFormat.NONE && place) {
            	cd.add(p -> " ");
            }
            if (date != DateFormat.NONE) {
                PersonRepresentator pr = null;
                switch (date) {
                    case YEAR:
                        pr = p -> p.getDeathDate().get().format(DateTimeFormatter.ofPattern("yyyy"));
                        break;
                    case FULL:
                        pr = p -> p.getDeathDate().get().toString();
                }
                cd.add(pr);
            }
            representator.add(new DeathRepresentator(cd));
            
            representator.add(p -> ")");
        }
        return representator;
    }
    
    public static void main(String args[]) {
    	Person alive = Person.createPerson("name", "surname");
    	alive.setBirthDate(LocalDate.of(2000, 1, 1));
    	alive.setBirthPlace("birth_place");
    	Person dead = Person.createPerson("name", "surname");
    	dead.setBirthDate(LocalDate.of(2000, 1, 1));
    	dead.setBirthPlace("birth_place");
    	dead.setDeathDate(LocalDate.of(2001, 1, 1));
    	dead.setDeathPlace("death_place");
    	
    	PersonRepresentator pr00 = createRepresentator(DateFormat.NONE, false);
    	if (!pr00.represent(alive).contentEquals("name surname")) {
    		System.out.println("test failed: alive NONE false");
    		System.out.println("  got: " + pr00.represent(alive));
    	}
    	if (!pr00.represent(dead).contentEquals("name surname")) {
    		System.out.println("test failed: dead NONE false");
    		System.out.println("  got: " + pr00.represent(dead));
    	}
    	
    	PersonRepresentator pr01 = createRepresentator(DateFormat.NONE, true);
    	if (!pr01.represent(alive).contentEquals("name surname (birth_place)")) {
    		System.out.println("test failed: alive NONE true");
    		System.out.println("  got: " + pr01.represent(alive));
    	}
    	if (!pr01.represent(dead).contentEquals("name surname (birth_place - death_place)")) {
    		System.out.println("test failed: dead NONE true");
    		System.out.println("  got: " + pr01.represent(dead));
    	}
    	
    	PersonRepresentator pr10 = createRepresentator(DateFormat.YEAR, false);
    	if (!pr10.represent(alive).contentEquals("name surname (2000)")) {
    		System.out.println("test failed: alive YEAR false");
    		System.out.println("  got: " + pr10.represent(alive));
    	}
    	if (!pr10.represent(dead).contentEquals("name surname (2000 - 2001)")) {
    		System.out.println("test failed: dead YEAR false");
    		System.out.println("  got: " + pr10.represent(dead));
    	}
    	
    	PersonRepresentator pr11 = createRepresentator(DateFormat.YEAR, true);
    	if (!pr11.represent(alive).contentEquals("name surname (birth_place 2000)")) {
    		System.out.println("test failed: alive YEAR true");
    		System.out.println("  got: " + pr11.represent(alive));
    	}
    	if (!pr11.represent(dead).contentEquals("name surname (birth_place 2000 - death_place 2001)")) {
    		System.out.println("test failed: dead YEAR true");
    		System.out.println("  got: " + pr11.represent(dead));
    	}
    	
    	PersonRepresentator pr20 = createRepresentator(DateFormat.FULL, false);
    	if (!pr20.represent(alive).contentEquals("name surname (2000-01-01)")) {
    		System.out.println("test failed: alive FULL false");
    		System.out.println("  got: " + pr20.represent(alive));
    	}
    	if (!pr20.represent(dead).contentEquals("name surname (2000-01-01 - 2001-01-01)")) {
    		System.out.println("test failed: dead FULL false");
    		System.out.println("  got: " + pr20.represent(dead));
    	}
    	
    	PersonRepresentator pr21 = createRepresentator(DateFormat.FULL, true);
    	if (!pr21.represent(alive).contentEquals("name surname (birth_place 2000-01-01)")) {
    		System.out.println("test failed: alive FULL true");
    		System.out.println("  got: " + pr21.represent(alive));
    	}
    	if (!pr21.represent(dead).contentEquals("name surname (birth_place 2000-01-01 - death_place 2001-01-01)")) {
    		System.out.println("test failed: dead FULL true");
    		System.out.println("  got: " + pr21.represent(dead));
    	}
    }
}
