package sd.akka.utils;

import java.io.Serializable;

public class Login implements Serializable {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the email associated with this object.
     *
     * @return the email associated with this object
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
