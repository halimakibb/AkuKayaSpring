package main.java.akuKaya.models;

public class ReturnMessage {
	public enum MessageType {Success, Error}
	
	private MessageType messageType;
	
	private String messageText;

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public ReturnMessage(MessageType messageType, String messageText) {
		this.messageType = messageType;
		this.messageText = messageText;
	}
	
	public ReturnMessage() {
		
	}
}
