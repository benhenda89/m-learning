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

@Entity
@Table(name = "USER_TRACK")
@Inheritance(strategy = InheritanceType.JOINED)
public class TrackReferencesImpl implements TrackPreferences {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(UserTrackImpl.class);
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "TrackId")
	@GenericGenerator(name = "TrackId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "TrackImpl"),
			@Parameter(name = "entity_name", value = "com.atec.learning.track.domain.TrackReferencesImpl") })
	@Column(name = "TRACK_ID")
	public long item_id;
	@Column(name = "CUSTOMER")
	@ManyToOne
	@JoinTable(name = "RdrCustomer")
	public long id_customer;
	@Column(name = "RdrNote")
	public double calcul_score;

	public Long getItemId() {
		return item_id;
	}

	public void setItemId(Long item_id) {
		this.item_id = item_id;
	}

	public long getCustomerId() {

		return id_customer;
	}

	public void setCustomerId(long id_customer) {
		this.id_customer = id_customer;

	}

	public double getCalculScore() {

		return calcul_score;
	}

	public void setCalculScore(double calcul_score) {
		this.calcul_score = calcul_score;

	}

}
