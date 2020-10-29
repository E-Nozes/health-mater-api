package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Donation;
import br.com.fiap.healthmater.exception.DonationValidationFailureException;
import br.com.fiap.healthmater.repository.DonationRepository;
import br.com.fiap.healthmater.util.UserUtil;
import br.com.fiap.healthmater.validation.DonationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for {@link Donation} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonationValidator donationValidator;

    @Autowired
    private UserUtil userUtil;

    public Donation create(Donation donation) {
        validateDonationPayload(donation);

        donation.setDonor(this.userUtil.findLoggedUser());
        donation.setDonationDate(LocalDate.now());

        this.donationRepository.save(donation);

        donation.getDonor().setPassword(null);

        return donation;
    }

    private void validateDonationPayload(Donation donation) {
        List<String> validationMessages = this.donationValidator.validate(donation);

        DonationValidationFailureException validationFailure = new DonationValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }
    }

}
