package fakergenerate;

import com.github.javafaker.Faker;
import models.confirmationUser;
import java.util.Locale;

public class generateConfirmationUser {

    public static confirmationUser returnedConfirmationUser() {
        Faker faker = new Faker(new Locale("en-US"));
        confirmationUser conf = new confirmationUser();

        // 🎯 توليد البيانات العادية بالـ Faker
        conf.setName(faker.name().username());
        conf.setEmail(faker.internet().emailAddress());
        conf.setPassword(faker.internet().password());
        conf.setTitle("Mr.");
        conf.setFirstName(faker.name().firstName());
        conf.setLastName(faker.name().lastName());
        conf.setCompany(faker.company().name());
        conf.setAddress(faker.address().streetAddress());
        conf.setAddress2(faker.address().secondaryAddress());
        conf.setState(faker.address().state());
        conf.setCity(faker.address().city());
        conf.setZipCode(faker.address().zipCode());
        conf.setMobile(faker.phoneNumber().cellPhone());

        // 🚀 تثبيت قيم الـ Dropdowns لضمان النجاح على الـ Pipeline وتجنب الـ Redirect Failure
        conf.setDay("10");
        conf.setMonth("May");
        conf.setYear("2000");
        conf.setCountry("United States");

        return conf;
    } // 👈 قفلة الدالة (returnedConfirmationUser)
} // 👈 قفلة الكلاس (generateConfirmationUser)
