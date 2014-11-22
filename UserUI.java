import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;


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
		int newsFeedSize = user.getNewsFeed().getSize();
		int followingSize = user.getFollowers().getSize();
		//while(true)
		//{
			if((newsFeedSize < user.getNewsFeed().getSize()) || followingSize < user.getFollowers().getSize())
				refresh();
		//}
	}
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
	@Override
	public void manageTextArea() {
		// TODO Auto-generated method stub
		userID = new JTextArea("User ID");
		userID.setBounds(5, 5, 150, 35);
		
		tweetMsg = new JTextArea("Tweet Message");
		tweetMsg.setBounds(5, 170, 150, 35);
		tweetMsg.setLineWrap(true);
	}
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
