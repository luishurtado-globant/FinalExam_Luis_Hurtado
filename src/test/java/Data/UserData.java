package Data;

import org.testng.annotations.DataProvider;

public class UserData {

    private String email = "luis.hurtado@globant.com";
    private String password = "testpassword123";


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @DataProvider (name = "userInfo")
    public Object[][] userInfo() {
        return new Object[][] {{getEmail(), getPassword()}};
    }
}
