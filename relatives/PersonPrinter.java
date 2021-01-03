package relatives;

import java.util.ArrayList;

public class PersonPrinter {

    private final PersonRepresentator representator;

    public PersonPrinter(PersonRepresentator representator) {
        this.representator = representator;
    }

    private void print(Person person) {
        System.out.print(representator.represent(person));
    }

    public void printDescendants(Person person) {
        printDescendantsRecursive(person, new ArrayList<>());
    }

    private void printLines(ArrayList<Boolean> list) {
        list.forEach(e -> System.out.print(" " + (e ? " " : "|")));
    }

    private void printDescendantsRecursive(Person person, ArrayList<Boolean> list) {
        //printing first person
        System.out.print("---");
        System.out.print(person.getNumberOfMarriages() == 0 ? "-" : "/");
        print(person);
        for (int j = 0; j < person.getNumberOfMarriages(); j++) {
            //printing spouse
            Marriage marriage = person.getNthMarriage(j);
            System.out.print("\n");
            printLines(list);
            System.out.print(" " + (marriage.getNumberOfChildren() == 0 ? "  " : "--") + "|\n");
            list.add(marriage.getNumberOfChildren() == 0);
            printLines(list);
            System.out.print(" \\"); // "\\" prints as "\"
            print(marriage.getOther(person));
            for (int i = 0; i < marriage.getNumberOfChildren(); i++) {
                //printing child
                System.out.print("\n");
                printLines(list);
                System.out.print("\n");
                printLines(list);
                if (i == marriage.getNumberOfChildren() - 1) {
                    list.set(list.size() - 1, true);
                }
                printDescendantsRecursive(marriage.getNthChild(i), list);
            }
            list.remove(list.size() - 1);
        }
    }
    
    public static void main(String[] args) {
    	Person charles = Person.createPerson("Charles", "Mountbatten-Windsor");
        Person diana = Person.createPerson("Diana", "Spencer");
        Marriage charles_diana = Marriage.createMarriage(charles, diana);
        Person william = Person.createPerson("William", "Mountbatten-Windsor", charles_diana);
        Person harry = Person.createPerson("Harry", "Mountbatten-Windsor", charles_diana);
        Person camilla = Person.createPerson("Camilla", "Parker Bowles");
        Marriage charles_camilla = Marriage.createMarriage(charles, camilla);
        
        PersonRepresentator pr = RepresentatorBuilder.createRepresentator(RepresentatorBuilder.DateFormat.NONE, false);
        PersonPrinter pp = new PersonPrinter(pr);
        pp.printDescendants(charles);
        
        String expected = "---/Charles Mountbatten-Windsor\n" + 
        		" --|\n" + 
        		" | \\Diana Spencer\n" + 
        		" |\n" + 
        		" |----William Mountbatten-Windsor\n" + 
        		" |\n" + 
        		" |----Harry Mountbatten-Windsor\n" + 
        		"   |\n" + 
        		"   \\Camilla Parker Bowles";
        System.out.println("\n\n This is what was expected:");
        System.out.println(expected);
    }

}
