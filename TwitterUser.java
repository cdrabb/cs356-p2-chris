import java.util.Hashtable;

import javax.swing.DefaultListModel;

public class TwitterUser implements User, Viewer, Group, TwitterElement {
	private DefaultListModel<TwitterUser> following;
	private DefaultListModel<TwitterUser> followers;
	private DefaultListModel<TweetMessage> newsFeed;
	private DefaultListModel<TweetMessage> archive;
	private TweetMessage tweet;
	private String UGID;
	private boolean isGroup;
	private Hashtable<String, Group> children;
	private String name;
	private String ID;
	
	public TwitterUser(String name, String ID)
	{
		this.name = name;
		this.ID = ID;
		this.isGroup = false;
		following = new DefaultListModel<TwitterUser>();
		followers = new DefaultListModel<TwitterUser>();
		newsFeed = new DefaultListModel<TweetMessage>();
		archive =  new DefaultListModel<TweetMessage>();

	}
	public void add(Group child)
	{
		children.put(child.getName(), child);
	}
	public void remove(TwitterUser user)
	{
		
	}
	public Hashtable<String, Group> getChildren()
	{
		return children;
	}
	public Group getChild(String name)
	{
		return children.get(name);
	}
	public boolean isGroup()
	{
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getName()
	{
		return name;
	}
	public String getID()
	{
		return ID;
	}
	public String toString()
	{
		return getName();
	}
	
	public DefaultListModel<TwitterUser> getFollowing()
	{
		return following;
	}
	
	public DefaultListModel<TwitterUser> getFollowers()
	{
		return followers;
	}
	public DefaultListModel<TweetMessage> getNewsFeed()
	{
		return newsFeed;
	}
	public String getUGID()
	{
		if(UGID == null)
			return "0";
		return UGID;
	}
 
	public void follow(TwitterUser user)
	{
		if(!following.contains(user))
        {
            following.addElement(user);
            user.getFollowers().addElement(this);
            for(int i = 0; i < user.archive.getSize(); i++)
            	newsFeed.addElement(user.archive.elementAt(i));
        }
	}
	
	public void post(String message)
	{
		tweet = new TweetMessage(message,this);
		newsFeed.addElement(tweet);
		archive.addElement(tweet);
		AdminUI.getInstance().getArchives().add(tweet);
		notifyFollowers();
	}

	public void notifyFollowers() 
	{
		for(int i = 0; i < followers.getSize(); i++)
			followers.elementAt(i).update(this);
			
	}
	public void update(TwitterUser user)
	{
		newsFeed.addElement(user.tweet);
	}

	public void unfollow() 
	{
		
	}
	@Override
	public void accept(TwitterElementVisitor visitor) {
		visitor.visit(this);
		
	}

}
