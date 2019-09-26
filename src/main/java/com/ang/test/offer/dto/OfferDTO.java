package com.ang.test.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = Include.NON_EMPTY)
public class OfferDTO {

	
    public OfferDTO(Long id, BigDecimal discountPct, Date activeFrom, Date activeUntil, BigDecimal discountedPrice,
			BigDecimal saving, ProductDTO product) {
		super();
		this.id = id;
		this.discountPct = discountPct;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.discountedPrice = discountedPrice;
		this.saving = saving;
		this.product = product;
	}
    public OfferDTO() {
    	
    }
	private Long id;
    // Between 0 and 1
	@Min(0)
	@Max(1)
    private BigDecimal discountPct;
    // Mandatory
    // Output format: MM/dd/yyyy
    // Input format: MM/dd/yyyy
	@NotNull
	@DateTimeFormat(pattern = "MM.dd.yyyy")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date activeFrom;
    // Optional
    // Output format: MM/dd/yyyy
    // Input format: MM/dd/yyyy
	@DateTimeFormat(pattern = "MM.dd.yyyy")
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date activeUntil;
    // Calculated
    private BigDecimal discountedPrice;
    // Calculated
    private BigDecimal saving;
    // Product ID is mandatory
    @Valid
    @OneToOne
    private ProductDTO product;
    //To show error message
    private Errors errors;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getDiscountPct() {
		return discountPct;
	}
	public void setDiscountPct(BigDecimal discountPct) {
		this.discountPct = discountPct;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Date getActiveUntil() {
		return activeUntil;
	}
	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}
	public BigDecimal getDiscountedPrice() {
		return product.getPrice().min(product.getPrice().multiply(discountPct).divide(new BigDecimal(100)));
	}
	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public BigDecimal getSaving() {
		return product.getPrice().multiply(discountPct).divide(new BigDecimal(100));
	}
	public void setSaving(BigDecimal saving) {
		this.saving = saving;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
    
    

}
