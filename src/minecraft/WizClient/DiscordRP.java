package WizClient;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.arikia.dev.drpc.DiscordUser;

public class DiscordRP {
	
	private boolean running = true;
	private long created = 0;
	
	public void start() {
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Websome " + user.username + "#" + user.discriminator + ".");
				update("Booting up...", "");
			}
		}).build();
		
		DiscordRPC.discordInitialize("958757176627847198", handlers, true);
		
		new Thread("Discord RPC Callback") {
			
			@Override
			public void run() {
				while(running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
	}
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}
	public void update(String firtLine, String secondLine) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
		b.setBigImage("large", "");
		b.setDetails(firtLine);
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
		
	}
}