package relatives;

import java.util.ArrayList;

public class Marriage {
    private final Person[] spouses = new Person[2];
    private final ArrayList<Person> children = new ArrayList<>();

    private Marriage(Person spouse1, Person spouse2) {
        spouses[0] = spouse1;
        spouses[1] = spouse2;
    }
    
    public static Marriage createMarriage(Person spouse1, Person spouse2) {
        Marriage m = new Marriage(spouse1, spouse2);
        // this is why we need a factory method
        spouse1.addMarriage(m);
        spouse2.addMarriage(m);
        return m;
    }

    public void addChild(Person child) {
        children.add(child);
        child.setParents(this);
    }
    
    public Person getOther(Person spouse) {
        if (spouse == spouses[0]) {
            return spouses[1];
        } else if (spouse == spouses[1]) {
            return spouses[0];
        } else {
            throw new RunTimeException("");
        }
    }
    
    public int getNumberOfChildren() {
        return children.size();
    }
    
    public Person getNthChild(int index) {
    	return children.get(index);
    }
}
