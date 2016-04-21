package com.atec.learning.recommendation;

import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;

/**
 * 
 * @author mahbouba
 *
 */
public interface RdrRecommendationService {
	
	public Long recommendation() throws IOException, TasteException;
	
}