import javax.annotation.Resource;

import com.atec.learning.track.domain.UserTrack;
import com.atec.learning.track.service.UserTrackService;


public class TestTrack {

	@Resource(name = "rdrUserTrackService")
	private UserTrackService userTrackService;
	
	private UserTrack userTrack;
	
	
	
//	@Test
//	public void testUserTrackService() throws UserTrackExceptions {
//		
//		
//	Assert.assertEquals(userTrackService.calculateScoreUserItem(userTrack),"calcul of trackScoreUserItem");
//	
//	}
}
