package model.Entity.User;

import model.abstraction.User;

public class Staff extends User {
    private int ID;
    private static int count;
    public Staff(String fullName, String phoneNumber, String email, String address) {
        super(fullName, phoneNumber, address, email);
        this.ID = ++count;
    }

    public Staff(String userName, String passWord) {
        super(userName, passWord);
        this.ID = ++count;
    }

    public Staff(String userName, String fullName, String phoneNumber, String email, String passWord, String address) {
        super(userName, fullName, phoneNumber, email, passWord, address);
        this.ID = ++count;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
}
