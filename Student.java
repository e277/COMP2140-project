public class Student {
    private String fname, lname,email;
    private int ID;

    /**
     * Admin Constructor
     * 
     * @param fname    The user's first name
     * @param lname    The user's last name
     * @param ID   The user's identification number
     * @param email    The user's email address
     */
    public Student(String fname, String lname, int ID, String email) {
        this.fname = fname;
        this.lname = lname;
        this.ID = ID;
        this.email = email;
    };

    /**
     * Accessor method getFname
     * 
     * @return the administrator's first name
     */
    public String getFName() {

        return fname;
    }

    /**
     * getLName Assesor Method
     * 
     * @return the administrator's last name
     */
    public String getLName() {
        return lname;
    }

    /**
     * The getID Assesor Method
     * 
     * @return the administrator's identification number
     */
    public int getID() {
        return ID;
    }

    public String getEmail(){
        return email;
    }

    public String toString(){
        return getFName() + " " + getLName() + " " + getID() + " " + getEmail();
    }
}
