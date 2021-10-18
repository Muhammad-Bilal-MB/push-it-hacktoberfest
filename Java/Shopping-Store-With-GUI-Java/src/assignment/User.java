package assignment;

public abstract class User {

    private String id;
    private String username;
    private String password;
    private String name;
    private String emailAddress;
    private String contactNo;
    private String address;

    public User(String id, String username, String password, String name, String emailAddress, String contactNo, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.contactNo = contactNo;
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
    return id +"-"+ username + "-"+ password+"-" +name +"-"+emailAddress +"-"+ contactNo +"-"+ address ;
    }

}
