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
		try {
			twitter.authenticate();
			twitter.changePassword("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}

}
