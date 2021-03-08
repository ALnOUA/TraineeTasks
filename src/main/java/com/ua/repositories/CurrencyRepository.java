package com.ua.repositories;

import com.ua.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository  extends JpaRepository<Currency, Long> {

}
