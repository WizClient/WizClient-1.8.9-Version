package WizClient.event;

public class EventCancelable extends Event{
	
	private boolean cancelled = false;
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled( boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
