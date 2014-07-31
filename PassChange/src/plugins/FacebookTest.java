package plugins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FacebookTest {
	
	private Facebook facebook;
	
	@Before
	public void setUp() throws Exception {
		facebook=new Facebook("klg71@web.de", "***REMOVED***");
	}

	@Test
	public void testAuthenticate() {
			try {
				facebook.authenticate();
			} catch (Exception e) {

				fail(e.getMessage());
			}
		
	}

	@Test
	public void testChangePassword() {
		try {
			facebook.authenticate();
			facebook.changePassword("***REMOVED***");
		} catch (Exception e) {

			fail(e.getMessage());
		}
	}

}
