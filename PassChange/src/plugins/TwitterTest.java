package plugins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwitterTest {
	private Twitter twitter;
	@Before
	public void setUp() throws Exception {
		twitter=new Twitter("","");
	}

	@Test
	public void testAuthenticate() {
		try {
			twitter.authenticate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateAuthentification() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePasswordChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePasswordChangeString() {
		fail("Not yet implemented");
	}

}
