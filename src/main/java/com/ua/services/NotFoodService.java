package com.ua.services;

import java.util.List;
import com.ua.model.NotFood;
import com.ua.repositories.NotFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotFoodService {

    @Autowired
    private NotFoodRepository repo;

    public List<NotFood> listAll() {
        return repo.findAll();
    }

    public void save(NotFood notFood) {
        repo.save(notFood);
    }

    public NotFood get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
