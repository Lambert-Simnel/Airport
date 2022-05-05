public class Passenger {

    private String name;
    private String ID;
    private int contactInfo;

    public Passenger(String name, int contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }



}
