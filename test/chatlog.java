import static org.junit.Assert.*;

import org.junit.Test;
import com.justin.twitch.irc.IrcClient;

public class chatlog {

	@Test
	public void testUserName() {
		IrcClient irc = new IrcClient();
		assertEquals("k_m_g_bot", irc.getUserNameFromChat(":k_m_g_bot!k_m_g_bot@k_m_g_bot.tmi.twitch.tv PRIVMSG #cerealkiller1918 :hey"));
	}

}
