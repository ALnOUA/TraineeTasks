package com.ua.services;

import com.ua.model.Currency;
import com.ua.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository repo;

    public List<Currency> listAll() {
        return repo.findAll();
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
