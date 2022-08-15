package utils;

public class EmailValidator {

    private static String emailRegex = "^([a-z\\d\\.-_]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";

    public static boolean isValidEmail(String email) {
        return email.toLowerCase().matches(emailRegex);
    }

}
