//This is the visitor class for the Visitor pattern.
public interface TwitterElementVisitor {

	public void visit(AdminUI admin);
	public void visit(TwitterUser user);
	public void visit(TwitterGroup group);
	public void visit(TweetMessage message);
	
}
