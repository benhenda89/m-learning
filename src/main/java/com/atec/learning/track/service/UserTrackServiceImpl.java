package com.atec.learning.track.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.core.order.service.OrderServiceImpl;
import org.broadleafcommerce.core.rating.domain.RatingSummary;
import org.broadleafcommerce.core.rating.service.type.RatingType;
import org.springframework.stereotype.Service;

import com.atec.learning.track.dao.UserTrackDao;
import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.domain.type.RdrItemType;
import com.atec.learning.track.exceptions.UserTrackExceptions;
import com.rayondart.core.profile.domain.RdrCustomer;
import com.rayondart.core.rating.service.RdrRatingService;

@Service("rdrUserTrackService")
public class UserTrackServiceImpl implements UserTrackService{

	
    private static final Log LOG = LogFactory.getLog(UserTrackServiceImpl.class);
	@Resource(name = "blRatingService")
	protected RdrRatingService ratingService;
	
	@Resource(name = "rdrUserTrackDao")
	protected UserTrackDao userTrackDao;
	
	@Override
	public UserTrack readTrackById(Long userTrackId) {
		
		return userTrackDao.readTrackById(userTrackId);
	}

	@Override
	public UserTrack add(UserTrack userTrack) {
		
		return userTrackDao.add(userTrack);
	}

	@Override
	public void remove(UserTrack userTrack) {
	
		userTrackDao.remove(userTrack);
	}

	@Override
	public UserTrack readTrackByCustomerAndItem(RdrCustomer customer,
			RdrItemType itemType, Long itemValue) {
	
		return userTrackDao.readTrackByCustomerAndItem(customer.getId(), itemType.getType(), itemValue);
	}

	@Override
	public List<UserTrack> readUserTracksByCustomer(RdrCustomer customer) {
	
		return readUserTracksByCustomer(customer);
	}

	// method score calculating by user and item
	@Override
	public double calculateScoreUserItem(UserTrack userTrack) throws UserTrackExceptions{
		double userScore = 0L;
		LOG.debug("the product"+userTrack.getItemValue()+"of type"+userTrack.getItemType());
		if(Objects.nonNull(userTrack)){
			if(userTrack.getTrackLike()){ // if item is viewed
				
				
				//recalculer et sauvegarder la valeur de userTrack
				userScore=(userScore+2)/2;
			
			}
			
			if(userTrack.getTrackView()){ // if item is liked
		
				
				//recalculer et sauvegarder la valeur de userTrack
				userScore=(userScore+1)/2;
				
				
			
			}
			
			if(userTrack.getTrackRated()){ //if item is rated
				
				
				RatingSummary ratingSummary = ratingService.readRatingSummary(userTrack.getItemValue().toString(), RatingType.PRODUCT);
				
				if(Objects.nonNull(ratingSummary)){
					
					//recuperer la valeur de rating a partir de la table blc_rating_summary
					
					
					double k=(ratingSummary.getAverageRating()+3)/2;
					
					//recalculer et sauvegarder la valeur de userTrack
					userScore=(userScore+k)/2;
					
					
				}
			}
			
			if(userTrack.getTrackAchat()){ // if item is buyed.
				
				
				
				//recalculer et sauvegarder la valeur de userTrack
				userScore=(userScore+4)/2;
				
				
			}
		}
		return userScore;//the final value of userTrack
	}
}
