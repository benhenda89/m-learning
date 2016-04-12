package com.atec.learning.track.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.atec.learning.track.domain.type.RdrItemType;
import com.rayondart.core.profile.domain.RdrCustomer;

@Entity
@Table(name = "USER_TRACK")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserTrackImpl implements UserTrack{
	
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(UserTrackImpl.class);
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(generator= "TrackId")
    @GenericGenerator(
        name="TrackId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="TrackImpl"),
            @Parameter(name="entity_name", value="com.atec.learning.track.domain.UserTrackImpl")
        })
	 @Column(name = "TRACK_ID")
	protected Long id;
	 
	
	
	@Column(name="ItemType")
	protected String itemType;
	
	@Column(name="ItemValue")
	protected Long itemValue;
	
	@Column(name="TrackView")
	protected boolean trackView;
	
	@Column(name="TrackAchat")
	
	protected boolean trackAchat;
	@Column(name="TrackLike")
	protected boolean trackLike;
	
	@Column(name="TrackRated")
	protected boolean trackRated;
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	
	
	public RdrItemType getItemType() {
		return RdrItemType.getInstance(itemType);
	}

	public void setItemType(RdrItemType itemType) {
		this.itemType = itemType.getType();
	}

	public Long getItemValue() {
		return itemValue;
	}
	
	public void setItemValue(Long itemValue) {
		this.itemValue = itemValue;
	}
	
	public boolean getTrackView() {
		return trackView;
	}
	
	public boolean getTrackAchat() {
		return trackAchat;
	}
	
	public void setTrackAchat(boolean trackAchat) {
		this.trackAchat = trackAchat;
	}

	public boolean getTrackLike() {
		return trackLike;
	}

	
	public boolean getTrackRated() {
		return trackRated;
	}
	

	
	
	
	public void setTrackView(Boolean string) {
		this.trackView = trackView;
		
	}
	
	public void setTrackLike(Boolean string) {
		this.trackLike = trackLike;
		
	}
	
	public void setTrackRated(Boolean trackRated) {
		this.trackRated = trackRated;
		
	}

}
