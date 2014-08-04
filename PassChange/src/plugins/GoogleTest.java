package plugins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GoogleTest {

	private Google google;
	@Before
	public void setUp() throws Exception {
		google=new Google("","");
	}

	@Test
	public void testAuthenticate() {
		try {
			google.authenticate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChangePassword(){
		try {
			google.authenticate();
			google.changePassword("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
