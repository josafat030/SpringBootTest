package com.ang.test.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ang.test.offer.domain.Offer;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	
    public ProductDTO(Long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
    @NotNull
	private Long id;
    // Not null
    // 150 chars
    // Prevent XSS
    @NotNull
    @Size(min = 0, max = 150)
    private String name;
    // 10 integer positions, 2 decimal positions
    // No negative
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    private BigDecimal price;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Offer offer;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

    
}
