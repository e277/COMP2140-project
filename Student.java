public class Student {
    private String fname, lname;
    private int ID;

    /**
     * Admin Constructor
     * 
     * @param fname    the user's first name
     * @param lname    The user's last name
     * @param ID       The user's identification number
     */
    public Student(String fname, String lname, int ID) {
        this.fname = fname;
        this.lname = lname;
        this.ID = ID;
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

    public String toString(){
        return getFName() + " " + getLName() + " " + getID();
    }
}
