package assignment4_COMP2631;

public class Person {
    private final String name;
    private final int yearOfBirth;
    private final boolean isMom;
    private final String childsName;
    // Add other relevant fields if needed, such as place of birth, etc.

    public Person(String name, int yearOfBirth, boolean isMom, String childsName) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.isMom = isMom;
        this.childsName = childsName;
    }
    // Getter method for the 'name' field
    public String getName() {
        return name;
    }

    // Getter method for the 'yearOfBirth' field
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    // Getter method for the 'isMom' field
    public boolean isMom() {
        return isMom;
    }

    // Getter method for the 'childsName' field
    public String getChildsName() {
        return childsName;
    }
    public String toString() {
        return "Person{ " +
               "Name: '" + name + '\'' +
               ", Year: " + yearOfBirth +
               ", Child: '" + childsName + '\'' +
               '}';
    }
}
