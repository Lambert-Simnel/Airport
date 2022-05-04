public class Passenger {

    private String name;
    private int ID;
    private String contactInfo;

    public Passenger(String name, int ID, String contactInfo) {
        this.name = name;
        this.ID = ID;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }



}
