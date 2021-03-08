package com.ua.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
		discriminatorType = DiscriminatorType.STRING)
public  class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long product_id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "currency_id")
	private Currency currency;
	private String name;
	private float price;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;

	public Long getProduct_id() {
		return product_id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
	/*@ManyToOne
	@JoinColumn(name = "country_id", insertable=false, updatable=false)
	private Country country;*/



	public Product() {
	}

	public Product(Long product_id, Currency currency, String name, float price) {
		this.product_id = product_id;
		this.currency = currency;
		this.name = name;
		this.price = price;
	}

	protected Product(Long product_id, String name, float price) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
	}
}
