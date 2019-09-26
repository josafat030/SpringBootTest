package com.ang.test.offer.repository;

import com.ang.test.offer.domain.Offer;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	@Query("SELECT o FROM Offer o WHERE ?1 BETWEEN o.activeFrom AND o.activeUntil")
	List<Offer> findAllByDate(Date date);
}
