package com.atec.learning.track.service;

import java.util.List;

import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.domain.type.RdrItemType;
import com.atec.learning.track.exceptions.UserTrackExceptions;
import com.rayondart.core.profile.domain.RdrCustomer;

public interface UserTrackService {
	
	public UserTrack readTrackById(Long userTrackId);
	
	public UserTrack add(UserTrack userTrack);
	
	public void remove(UserTrack userTrack);
	
	public UserTrack readTrackByCustomerAndItem(RdrCustomer customer, RdrItemType itemType, Long itemValue);
	
	public List<UserTrack> readUserTracksByCustomer(RdrCustomer customer);
	
	public double calculateScoreUserItem(UserTrack userTrack) throws UserTrackExceptions;
}
