package utilities;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static ThreadLocal<Map<String, String>> testData = new ThreadLocal<Map<String, String>>() {
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    public static void setTestData(String key, String value) {
        testData.get().put(key, value);
    }

    public static String getTestData(String key) {
        return testData.get().get(key);
    }

    public static void clear() {
        testData.get().clear();
    }

    public static String getDescriptiveTestName() {
        String username = getTestData("username");
        String password = getTestData("password");

        if (username == null) username = "";
        if (password == null) password = "";

        if (username.equals("standard_user") && password.equals("secret_sauce")) {
            return "Login with valid credentials";
        } else if (username.equals("invalid_user") && password.equals("wrong_pass")) {
            return "Login with invalid credentials";
        } else if (username.isEmpty() && password.equals("secret_sauce")) {
            return "Login with empty username";
        } else if (username.equals("standard_user") && password.isEmpty()) {
            return "Login with empty password";
        } else {
            return "Login with credentials (" + username + "/" + password + ")";
        }
    }
}