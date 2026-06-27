package fakergenerate;
import models.confirmationUser;
import com.github.javafaker.Faker;
import java.util.Locale;

public class generateConfirmationUser {
    public static confirmationUser returnedConfirmationUser() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = firstName + " " + lastName;
        String email = firstName.toLowerCase() + faker.number().numberBetween(10, 999) + "@example.com";
        
        // 🎯 إضافة النقطة (.) لضمان مطابقة خيارات الموقع "Mr." أو "Mrs."
        String title = faker.bool().bool() ? "Mr." : "Mrs."; 
        
        String day = String.valueOf(faker.number().numberBetween(1, 28));
        String month = faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        String year = String.valueOf(faker.number().numberBetween(1980, 2005));
        String password = faker.internet().password(6, 12, true, true);
        String company = faker.company().name();
        String address = faker.address().streetAddress();
        String address2 = faker.address().secondaryAddress();
        String country = "United States";
        String state = faker.address().state();
        String city = faker.address().city();
        String zipCode = faker.address().
