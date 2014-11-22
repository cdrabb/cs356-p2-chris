
public class TweetMessage implements TwitterElement{

	private long timeStamp;
	private String message;
	private String poster;
	private String posterUUID;
	
	public TweetMessage(String message, TwitterUser user)
	{
		this.message = message;
		poster = user.getName();
		posterUUID = user.getID();
		timeStamp = System.currentTimeMillis();
	}
	
	public String toString()
	{
		return poster + ": " + message;
	}

	@Override
	public void accept(TwitterElementVisitor visitor) 
	{
		visitor.visit(this);
	}
	public String getMessage()
	{
		return message;
	}
}
