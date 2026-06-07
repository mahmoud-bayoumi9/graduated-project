package demoPlaze;

public  class validationErrorMessage {
    public static  final  String requiredName="Please fill out this field.";
    public static  final  String requiredEmail="Please fill out this field.";
    public  static  String Emailvalidation(String email){
        return "Please include an '@' in the email address. '" + email + "' is missing an '@'.";

    }
}
