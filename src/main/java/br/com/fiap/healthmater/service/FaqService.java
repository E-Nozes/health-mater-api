package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Faq;
import br.com.fiap.healthmater.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for {@link Faq} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    public List<Faq> findAll() {
        List<Faq> faqList = this.faqRepository.findAll();

        faqList.forEach(faq -> faq.getUser().setPassword(null));

        return faqList;
    }

}
