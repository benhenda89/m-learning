package com.atec.learning.track.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.core.order.service.OrderServiceImpl;
import org.broadleafcommerce.core.rating.domain.RatingSummary;
import org.broadleafcommerce.core.rating.service.type.RatingType;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.stereotype.Service;

import com.atec.learning.track.dao.UserTrackDao;
import com.atec.learning.track.domain.UserNote;
import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.domain.type.RdrItemType;
import com.atec.learning.track.domain.type.RdrUserAction;
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
	public UserTrack create(){
		return userTrackDao.create();
	}
	
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
	public UserTrack readTrackByCustomerAndItem(RdrCustomer customer,RdrItemType itemType, Long itemValue) {
		return userTrackDao.readTrackByCustomerAndItem(customer.getId(), itemType.getType(), itemValue);
	}

	@Override
	public List<UserTrack> readUserTracksByCustomer(RdrCustomer customer) {
		return readUserTracksByCustomer(customer);
	}

	// method score calculating by user and item
	@Override
	public double calculateScoreUserItem(UserTrack userTrack) throws UserTrackExceptions{
		double userScore = 0L; // final Score.
		double totalScore = 10L; // temporary this value
		LOG.debug("calculate Score for "+userTrack.getItemType().getType()+" with Id = "+userTrack.getItemValue());
		
		if(Objects.nonNull(userTrack)){
			if(userTrack.getTrackLike()){ // if item is viewed
				userScore = userScore + TrackUtils.generateNewScore(totalScore, Double.parseDouble(UserNote.LIKE.getType()));
			}
			
			if(userTrack.getTrackView()){ // if item is liked
				userScore = userScore + TrackUtils.generateNewScore(totalScore, Double.parseDouble(UserNote.VIEW.getType()));
			}
			
			if(userTrack.getTrackRated()){ //if item is rated
				RatingSummary ratingSummary = ratingService.readRatingSummary(userTrack.getItemValue().toString(), RatingType.PRODUCT);
				if(Objects.nonNull(ratingSummary)){
					/*
					 * il ya du traitement ici :
					 * - si la valeur du rate > 2.5 = User 3ajbou el produit
					 * - si inférieur --> user ma3ejbouch
					 */
					userScore = userScore + TrackUtils.generateNewScore(totalScore, Double.parseDouble(UserNote.RATE.getType()));
				}
			}
			
			if(userTrack.getTrackAchat()){ // if item is buyed.
				userScore = userScore + TrackUtils.generateNewScore(totalScore, Double.parseDouble(UserNote.ACHAT.getType()));
			}
		}
		return userScore;//the final value of userTrack
	}

	@Override									
	public void updateUserProductsTracks(Map<String, Map<String, Boolean>> actionsUsers, RdrCustomer customer) throws UserTrackExceptions {
		// tester si la map n'est pas vide
		if(MapUtils.isNotEmpty(actionsUsers)){
			// parcourir la map
			for (Map.Entry<String, Map<String, Boolean>> entry : actionsUsers.entrySet()) {
				/* pour chaque produit , nous allons :
				*	1- si le produit existe dans la table User_Track
				*	2- si il existe nous allons faire un update de la ligne trouver
				*	3- sinon créer une instance track 
				*	4- avant de sauvegarder le UserTrack , on fait appel à la methode calculateScoreUserItem
				*/
				Long productId = Long.parseLong(entry.getKey());
				Map<String, Boolean> actUser = entry.getValue();
				
				
				UserTrack userTrack = readTrackByCustomerAndItem(customer, RdrItemType.PRODUCT, productId);
				// sil n'existe pas , nous allons créer une nouvelle instance
				if(Objects.isNull(userTrack)){
					userTrack = create();
				}
				
				// parcourir la map actUser
				// extraire les actes
				// sauvegarde dans la base
				for (Map.Entry<String, Boolean> acte : actUser.entrySet()) {
					if(acte.getKey().equals(RdrUserAction.VIEW.getType())){
						userTrack.setTrackView(acte.getValue().booleanValue());
					}
					if(acte.getKey().equals(RdrUserAction.LIKE.getType())){
						userTrack.setTrackLike(acte.getValue().booleanValue());
					}
					if(acte.getKey().equals(RdrUserAction.RATE.getType())){
						userTrack.setTrackLike(acte.getValue().booleanValue());
					}
					if(acte.getKey().equals(RdrUserAction.ACHAT.getType())){
						userTrack.setTrackLike(acte.getValue().booleanValue());
					}
				}
				// on a fini la mise à jour du track
				
				// nous allons calculer le score avant de sauvegarder.
				double userScore = calculateScoreUserItem(userTrack);
				userTrack.setUserItemScore(userScore);
				
				userTrack = add(userTrack);
			}
		}
	}
}
