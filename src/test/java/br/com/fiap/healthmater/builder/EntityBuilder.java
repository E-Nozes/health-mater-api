package br.com.fiap.healthmater.builder;

import br.com.fiap.healthmater.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class EntityBuilder {

    public static User buildUser() {
        User user = new User();
        user.setEmail("user@test.com");
        user.setPassword("12345678");
        user.setAddress(buildAddress());
        user.setProfiles(buildProfiles());

        return user;
    }

    public static Address buildAddress() {
        Address address = new Address();
        address.setAddress("Rua Vigário Albernaz 738");
        address.setCity(buildCity());
        address.setNumber("738");
        address.setZipCode("04134-021");

        return address;
    }

    public static City buildCity() {
        City city = new City();
        city.setName("São Paulo");
        city.setState(buildState());

        return city;
    }

    public static State buildState() {
        State state = new State();
        state.setId(24);
        state.setName("São Paulo");
        state.setFederalUnity("SP");

        return state;
    }

    public static Set<Profile> buildProfiles() {
        Set<Profile> profiles = new HashSet<>();
        profiles.add(buildProfile());

        return profiles;
    }

    public static Profile buildProfile() {
        Profile profile = new Profile();
        profile.setId(1);

        return profile;
    }

}
