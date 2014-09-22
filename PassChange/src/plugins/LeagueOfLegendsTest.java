package plugins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeagueOfLegendsTest {
	private LeagueOfLegends league;
	@Before
	public void setUp() throws Exception {
		league= new LeagueOfLegends("","");
	}

	@Test
	public void testAuthenticate() {
		try {
			league.authenticate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

}
