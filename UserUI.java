import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class UserUI implements UIManager {
	private TwitterUser user;
	private JFrame frame;
	private JButton followUser;
	private JButton postTweet;
	private JTextArea userID;
	private JTextArea tweetMsg;
	private JList<TwitterUser> following;
	private JList<TweetMessage> newsFeed;
	private JScrollPane followingScrollPane;
	private JScrollPane newsFeedScrollPane;
	
	public UserUI(final TwitterUser user)
	{
		this.user = user;
		buildUI();
	}
	
	//Handles buttons and action listeners.
	@Override
	public void manageButtons() {
		// TODO Auto-generated method stub
		followUser = new JButton("Follow User");
		followUser.setVerticalTextPosition(AbstractButton.CENTER);
		followUser.setHorizontalTextPosition(AbstractButton.LEADING);
		followUser.setActionCommand("follows user");
		followUser.setBounds(160, 5, 150, 35);
		followUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Perform action if the user is not trying to follow themselves and
        		//specified user actually exists.
        	    if(!userID.getText().equals(user.getName()) && 
        	    		AdminUI.getInstance().getElements().containsKey(userID.getText()))
        	    	user.follow((TwitterUser)AdminUI.getInstance().getElements().get(userID.getText()));
        	  }
        	});
		
		postTweet = new JButton("Post Tweet");
		postTweet.setVerticalTextPosition(AbstractButton.CENTER);
		postTweet.setHorizontalTextPosition(AbstractButton.LEADING);
		postTweet.setActionCommand("posts tweet");
		postTweet.setBounds(160, 170, 150, 35);
		postTweet.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    user.post(tweetMsg.getText());
        	  }
        	});
	}
	
	//Handles frame operations.
	@Override
	public void manageFrame() {
		// TODO Auto-generated method stub
		frame = new JFrame(user.getName() + "'s profile");
		frame.setLayout(null);
		frame.add(userID);
		frame.add(followUser);
		frame.add(tweetMsg);
		frame.add(postTweet);
		frame.add(followingScrollPane);
		frame.add(newsFeedScrollPane);
		
		frame.setSize(330, 370);
		frame.setVisible(true);
	}
	
	//Handles text areas.
	@Override
	public void manageTextArea() {
		// TODO Auto-generated method stub
		userID = new JTextArea("User ID");
		userID.setBounds(5, 5, 150, 35);
		
		tweetMsg = new JTextArea("Tweet Message");
		tweetMsg.setBounds(5, 170, 150, 35);
		tweetMsg.setLineWrap(true);
	}
	
	//refreshes the UI
	public void refresh()
	{
		following = new JList<TwitterUser>(user.getFollowing());
		followingScrollPane = new JScrollPane(following);
		followingScrollPane.setBounds(5, 45, 305, 120);
		
		newsFeed = new JList <TweetMessage>(user.getNewsFeed());
		newsFeedScrollPane = new JScrollPane(newsFeed);
		newsFeedScrollPane.setBounds(5, 210, 305, 120);
	}

	public void buildUI()
	{	
		manageButtons();
		
		manageTextArea();
		
		refresh();
		
		manageFrame();
	}
	
}
