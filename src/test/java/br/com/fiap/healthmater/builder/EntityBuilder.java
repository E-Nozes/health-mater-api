package br.com.fiap.healthmater.builder;

import br.com.fiap.healthmater.entity.Address;
import br.com.fiap.healthmater.entity.City;
import br.com.fiap.healthmater.entity.State;
import br.com.fiap.healthmater.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EntityBuilder {

    public User buildUser() {
        User user = new User();
        user.setEmail("user@test.com");
        user.setPassword("12345678");
        user.setAddress(this.buildAddress());

        return user;
    }

    public Address buildAddress() {
        Address address = new Address();
        address.setAddress("Rua Vigário Albernaz 738");
        address.setCity(this.buildCity());
        address.setNumber("738");
        address.setZipCode("04134-021");

        return address;
    }

    public City buildCity() {
        City city = new City();
        city.setName("São Paulo");
        city.setState(this.buildState());

        return city;
    }

    public State buildState() {
        State state = new State();
        state.setName("São Paulo");
        state.setFederalUnity("SP");

        return state;
    }

}
