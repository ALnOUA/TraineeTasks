package com.ua.services;

import com.ua.model.Country;
import com.ua.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository repo;

    public List<Country> listAll() {
        return repo.findAll();
    }

    public void save(Country country) {
        repo.save(country);
    }

    public Country get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
