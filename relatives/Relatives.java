package relatives;

import java.time.LocalDate;

public class Relatives {

	public static void main(String[] args) {
		Person elizabeth = Person.createPerson("Elizabeth", "Windsor");
        elizabeth.setBirthDate(LocalDate.of(1926, 4, 21));
        elizabeth.setBirthPlace("London");
        Person philip = Person.createPerson("Philip", "Mountbatten");
        philip.setBirthDate(LocalDate.of(1921, 6, 10));
        philip.setBirthPlace("Mon Repos");
        Marriage elizabeth_philip = Marriage.createMarriage(elizabeth, philip);
        Person charles = Person.createPerson("Charles", "Mountbatten-Windsor", elizabeth_philip);
        charles.setBirthDate(LocalDate.of(1948, 11, 14));
        charles.setBirthPlace("London");
        Person anne = Person.createPerson("Anne", "Mountbatten-Windsor", elizabeth_philip);
        anne.setBirthDate(LocalDate.of(1950, 8, 15));
        anne.setBirthPlace("London");
        Person andrew = Person.createPerson("Andrew", "Mountbatten-Windsor", elizabeth_philip);
        andrew.setBirthDate(LocalDate.of(1960, 2, 19));
        andrew.setBirthPlace("London");
        Person edward = Person.createPerson("Edward", "Mountbatten-Windsor", elizabeth_philip);
        edward.setBirthDate(LocalDate.of(1964, 3, 10));
        edward.setBirthPlace("London");
        Person diana = Person.createPerson("Diana", "Spencer");
        diana.setBirthDate(LocalDate.of(1961, 7, 1));
        diana.setBirthPlace("Sandringham");
        Marriage charles_diana = Marriage.createMarriage(charles, diana);
        Person william = Person.createPerson("William", "Mountbatten-Windsor", charles_diana);
        william.setBirthDate(LocalDate.of(1982, 6, 21));
        william.setBirthPlace("London");
        Person harry = Person.createPerson("Harry", "Mountbatten-Windsor", charles_diana);
        harry.setBirthDate(LocalDate.of(1984, 9, 15));
        harry.setBirthPlace("London");
        diana.setDeathDate(LocalDate.of(1997, 8, 31));
        diana.setDeathPlace("Paris");
        Person camilla = Person.createPerson("Camilla", "Parker Bowles");
        camilla.setBirthDate(LocalDate.of(1947, 7, 17));
        camilla.setBirthPlace("London");
        Marriage charles_camilla = Marriage.createMarriage(charles, camilla);
        Person catherine = Person.createPerson("Catherine", "Middleton");
        catherine.setBirthDate(LocalDate.of(1982, 1, 9));
        catherine.setBirthPlace("Reading");
        Marriage william_catherine = Marriage.createMarriage(william, catherine);
        Person george = Person.createPerson("George", "Mountbatten-Windsor", william_catherine);
        george.setBirthDate(LocalDate.of(2013, 7, 22));
        george.setBirthPlace("London");
        Person charlotte = Person.createPerson("Charlotte", "Mountbatten-Windsor", william_catherine);
        charlotte.setBirthDate(LocalDate.of(2015, 5, 2));
        charlotte.setBirthPlace("London");
        Person louise = Person.createPerson("Louise", "Mountbatten-Windsor", william_catherine);
        louise.setBirthDate(LocalDate.of(2018, 4, 23));
        louise.setBirthPlace("London");
        
        
        PersonRepresentator pr = RepresentatorBuilder.createRepresentator(RepresentatorBuilder.DateFormat.YEAR, true);
        PersonPrinter pp = new PersonPrinter(pr);
        pp.printDescendants(elizabeth);
	}

}
