package com.ua.services;

import java.util.List;

import com.ua.model.Food;
import com.ua.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FoodService {

    @Autowired
    private FoodRepository repo;

    public List<Food> listAll() {
        return repo.findAll();
    }

    public void save(Food food) {
        repo.save(food);
    }

    public Food get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
