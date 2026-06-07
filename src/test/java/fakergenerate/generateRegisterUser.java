//package fakergenerate;
//import com.github.javafaker.Faker;
//import models.registerUser;
//public class generateRegisterUser {
//
//    public static registerUser returnedRegisterUser() {
//        Faker faker = new Faker();
//        String name = faker.name().firstName().toLowerCase();
//        String email = name + faker.number().numberBetween(10, 999) + "@example.com";
//
//        return new registerUser(name, email);
//    }
//}
