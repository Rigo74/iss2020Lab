package it.unibo.sonarclientserver.personal.commons;

public enum Message {
	ACK("ok"),
	REQUEST_THETA("getTheta");
	
	final String message;
	
	private Message(final String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
