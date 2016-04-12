package com.atec.learning.track.domain;

import java.io.Serializable;

import org.broadleafcommerce.profile.core.domain.Customer;

public interface TrackPreferences  extends Serializable{
	
	
	
	/**
	 * get the id
	 * @return
	 */
	public Long getItemId();
	
	/**
	 * set the id
	 * @param id
	 */
	public void setItemId(Long item_id);
	
	/**
	 * get {@link Customer}.
	 * Returns
	 */
	public long getCustomerId();
	
	/**
	 * set the customer{@link Customer}.
	 * @param customer
	 */
	public void setCustomerId(long customer_id);
	/**
	 * get calcul_score
	 * 
	 */
	public double getCalculScore();
	/**
	 * set calcul_score
	 * 
	 */
	public void setCalculScore(double userScore);
	
}
