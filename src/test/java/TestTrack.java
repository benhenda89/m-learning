import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.exceptions.UserTrackExceptions;
import com.atec.learning.track.service.UserTrackService;


public class TestTrack {

	@Resource(name = "rdrUserTrackService")
	private UserTrackService userTrackService;
	
	private UserTrack userTrack;
	
	
	
	@Test
	public void testUserTrackService() throws UserTrackExceptions {
		
		
	Assert.assertEquals(userTrackService.calculateScoreUserItem(userTrack),"calcul of trackScoreUserItem");
	
	}
}
