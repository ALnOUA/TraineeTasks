package com.ua.services;

import com.ua.model.Currency;
import com.ua.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository repo;

    @PersistenceContext
    EntityManager entityManager;
    public List<Currency> listAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Currency> criteriaQuery = criteriaBuilder.createQuery(Currency.class);
        Root<Currency> currencyRoot = criteriaQuery.from(Currency.class);
        criteriaQuery.select(currencyRoot);
        TypedQuery<Currency> currencyTypedQuery = entityManager.createQuery(criteriaQuery);
        List<Currency> currencies = currencyTypedQuery.getResultList();
        return currencies;
    }

    public void save(Currency currency) {
        repo.save(currency);
    }

    public Currency get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
