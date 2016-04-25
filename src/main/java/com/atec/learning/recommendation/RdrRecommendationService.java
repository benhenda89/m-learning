package com.atec.learning.recommendation;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

/**
 * 
 * @author mahbouba
 *
 */


public interface RdrRecommendationService {
	
	public List<Long> recommendation() throws IOException, TasteException;
	
}