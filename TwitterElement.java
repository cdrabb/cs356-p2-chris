//This is the element interface for the Visitor pattern.
public interface TwitterElement {

	public void accept(TwitterElementVisitor visitor);
}
