package com.atec.learning.track.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.springframework.stereotype.Repository;

import com.atec.learning.track.domain.UserTrack;

@Repository("rdrUserTrackDao")
public class UserTrackDaoImpl implements UserTrackDao{
	
    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;
	
    
    @Override
	public UserTrack readTrackById(Long userTrackId) {
		
		return em.find(UserTrack.class, userTrackId);	
	}

    @Override
	public UserTrack add(UserTrack userTrack) {
		return em.merge(userTrack);
	}

	
    @Override
	public void remove(UserTrack userTrack) {
		
		em.remove(userTrack);
	}

    @Override
	public UserTrack readTrackByCustomerAndItem(Long customerId,
			String itemType, Long itemValue) {
		TypedQuery<UserTrack> q = em.createNamedQuery("READ_ALL_TRACKS_BY_CUSTOMER_AND_ITEM",UserTrack.class);
		  q.setParameter("customerId",customerId);
		  q.setParameter("itemType",itemType);
		  q.setParameter("itemValue",itemValue);
		
		UserTrack userTracks=(UserTrack) q.getSingleResult();
		
		return userTracks;
	}

    @Override
	public List<UserTrack> readUserTracksByCustomerId(Long customerId) {
		TypedQuery<UserTrack> q = em.createNamedQuery("READ_ALL_TRACKS_BY_CUSTOMER",UserTrack.class);
		 q.setParameter("customerId",customerId);
		return q.getResultList();
	}

    @Override
	public UserTrack create() {
		
		return (UserTrack) entityConfiguration.createEntityInstance(UserTrack.class.getName());
	}

}
