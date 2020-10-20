package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Profile;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.ResourceNotFoundException;
import br.com.fiap.healthmater.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for {@link Profile} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class ProfileService {

    private static final String PROFILE_LEVEL_1 = "LEVEL_1";

    @Autowired
    private ProfileRepository profileRepository;

    public User addDefaultProfile(User user) {
        Profile profileLevel1 = this.profileRepository.findByDescriptionIgnoreCase(PROFILE_LEVEL_1)
                .orElseThrow(() -> new ResourceNotFoundException("Default profile not found"));

        user.getProfiles().add(profileLevel1);

        return user;
    }

}
