import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;

//This class is the Component class for the composite pattern.
//TwitterUser is the leaf class and TwitterGroup is the composite class.
public interface Group extends TwitterElement {
	
	public void add(Group child);
	public void remove(TwitterUser user);
	public Hashtable<String, Group> getChildren();
	public Group getChild(String name);
	public boolean isGroup();
	public void setGroup(boolean isGroup);
	public String getName();
	public String getID();
	public String toString();
}

