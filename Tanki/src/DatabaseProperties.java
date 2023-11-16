import java.util.Properties;

public class DatabaseProperties extends Properties {

    public String getUrl() {
        return getProperty("database.url");
    }

    public String getLogin() {
        return getProperty("database.user");
    }

    public String getPassword() {
        return getProperty("database.password");
    }
}
