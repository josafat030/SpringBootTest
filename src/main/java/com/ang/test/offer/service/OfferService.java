package com.ang.test.offer.service;

import com.ang.test.offer.domain.Offer;
import com.ang.test.offer.domain.Product;
import com.ang.test.offer.dto.Errors;
import com.ang.test.offer.dto.OfferDTO;
import com.ang.test.offer.dto.ProductDTO;
import com.ang.test.offer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> findAll(Date activeOn) {
        return offerRepository.findAllByDate(activeOn);
    }

    public List<OfferDTO> findAllDTO(Date activeOn) {
    	if(activeOn==null){
    		activeOn=new Date();
    	}
        List<Offer> offers = findAll(activeOn);
        if(offers.isEmpty())
            return new ArrayList<>();
        return offers.stream().map(offer -> new OfferDTO(offer.getId()
                , offer.getDiscountPct(), offer.getActiveFrom(), offer.getActiveUntil()
                , offer.getProduct().getPrice().multiply(new BigDecimal("1.00").subtract(offer.getDiscountPct()))
                , offer.getProduct().getPrice().multiply(offer.getDiscountPct())
                , new ProductDTO(
                    offer.getProduct().getId(),
                    offer.getProduct().getName(),
                    offer.getProduct().getPrice()
        ))).collect(Collectors.toList());
    }

    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    public OfferDTO save(OfferDTO offerDTO) {
    	if(offerDTO==null || offerDTO.getId()==null) {
    		OfferDTO error=new OfferDTO();
    		Errors errorMessage=new Errors();
    		errorMessage.setError("The ID of Offer must not be null");
    		error.setErrors(errorMessage);
    	}
    	if(offerDTO.getProduct()==null || offerDTO.getProduct().getId()==null) {
    		OfferDTO error=new OfferDTO();
    		Errors errorMessage=new Errors();
    		errorMessage.setError("The ID of Product must not be null");
    		error.setErrors(errorMessage);
    	}
        Offer offer = new Offer(
                offerDTO.getId(),
                offerDTO.getDiscountPct(),
                offerDTO.getActiveFrom(),
                offerDTO.getActiveUntil(),
                new Product(
                        offerDTO.getProduct().getId(),
                        offerDTO.getProduct().getName(),
                        offerDTO.getProduct().getPrice()
                )
        );
        Offer saved = save(offer);
        return new OfferDTO(saved.getId()
                , saved.getDiscountPct(), saved.getActiveFrom(), saved.getActiveUntil()
                , saved.getProduct().getPrice().multiply(new BigDecimal("1.00").subtract(saved.getDiscountPct()))
                , saved.getProduct().getPrice().multiply(saved.getDiscountPct())
                , new ProductDTO(
                saved.getProduct().getId(),
                saved.getProduct().getName(),
                saved.getProduct().getPrice()
        ));
    }
    public String delete(Long id) {
    	offerRepository.deleteById(id);
    	return "The product has been deleted";
    	
    }

}
