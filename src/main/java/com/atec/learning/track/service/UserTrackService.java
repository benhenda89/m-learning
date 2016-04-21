package com.atec.learning.track.service;

import java.util.List;
import java.util.Map;

import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.domain.type.RdrItemType;
import com.atec.learning.track.exceptions.UserTrackExceptions;
import com.rayondart.core.profile.domain.RdrCustomer;


/**
 * 
 * @author mahbouba
 *
 */
public interface UserTrackService {
	
	public UserTrack create();
	
	public UserTrack readTrackById(Long userTrackId);
	
	public UserTrack add(UserTrack userTrack);
	
	public void remove(UserTrack userTrack);
	
	public UserTrack readTrackByCustomerAndItem(RdrCustomer customer, RdrItemType itemType, Long itemValue);
	
	public List<UserTrack> readUserTracksByCustomer(RdrCustomer customer);
	
	/**
	 * Persister dans BD , la map User Actions.
	 * Map des produits , avec pour chaque produits les actions faites (like , view , ....)
	 * @param actionsUsers
	 * @throws UserTrackExceptions 
	 */
	public  void updateUserProductsTracks  (Map<String,String> actionsUsers) throws UserTrackExceptions; 
	/**
	 * Calculer le score aprés chaque mise à jour sur un produit (Like, view, ...)

	 * @param userTrack
	 * @return
	 * @throws UserTrackExceptions
	 * @author takwa
	 */
	public double calculateScoreUserItem(UserTrack userTrack) throws UserTrackExceptions;
	
	
	
}
