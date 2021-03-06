import java.util.Hashtable;

import javax.swing.DefaultListModel;

public class TwitterUser implements User, Observer, Group, TwitterElement {
	private DefaultListModel<TwitterUser> following;
	private DefaultListModel<TwitterUser> followers;
	private DefaultListModel<TweetMessage> newsFeed;
	private DefaultListModel<TweetMessage> archive;
	private TweetMessage tweet;
	private boolean isGroup;
	private Hashtable<String, Group> children;
	private String name;
	private String ID;
	private long creationTime;
	private long lastUpdate = 0;
	
	public TwitterUser(String name, String ID)
	{
		this.name = name;
		this.ID = ID;
		isGroup = false;
		following = new DefaultListModel<TwitterUser>();
		followers = new DefaultListModel<TwitterUser>();
		newsFeed = new DefaultListModel<TweetMessage>();
		archive =  new DefaultListModel<TweetMessage>();
		creationTime = System.currentTimeMillis(); //This is displayed in the header when	                                           
	}                                              //opening user view.
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
		return getName() + " - Updated " + getLastUpdate() + " seconds ago.";
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
 
	public void follow(TwitterUser user)
	{
		//If not already following the user, follow them,
		//add me to their followers and add their history of 
		//messages to my newsfeed.
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
		//Add tweet to my newsfeed, archive, and Admin archives
		//then notify my followers.
		tweet = new TweetMessage(message,this);
		newsFeed.addElement(tweet);
		archive.addElement(tweet);
		AdminUI.getInstance().getArchives().add(tweet);
		notifyFollowers();
		lastUpdate = System.currentTimeMillis();
	}

	public void notifyFollowers() 
	{
		//Notify followers to update.
		for(int i = 0; i < followers.getSize(); i++)
			followers.elementAt(i).update(this);
			
	}
	public void update(TwitterUser user)
	{
		//New tweet is added to the newsfeed.
		newsFeed.addElement(user.tweet);
	}
	public long getCreationTime()
	{
		return (System.currentTimeMillis() - creationTime)/1000;
	}
	public long getLastUpdate()
	{
		if(lastUpdate > 0)
			return (System.currentTimeMillis() - lastUpdate)/1000;
		return 0;
	}

	public void unfollow() 
	{
		
	}
	@Override
	public void accept(TwitterElementVisitor visitor) {
		visitor.visit(this);
		
	}

}
