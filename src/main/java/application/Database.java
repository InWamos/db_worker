package application;

public class Database {
    
    public String urlToDB;
    public String username;
    public String password;
    
    public Database(String urlToDB, String username, String password) {
    
        this.urlToDB = urlToDB;
        this.username = username;
        this.password = password;
    
    }

    public String getUrlToDB() {
        return urlToDB;
    }

    public void setUrlToDB(String urlToDB) {
        this.urlToDB = urlToDB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
