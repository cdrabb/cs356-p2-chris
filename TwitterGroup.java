import java.util.Hashtable;

public class TwitterGroup implements Group, TwitterElement {
	
	private boolean isGroup;
	private Hashtable<String, Group> children;
	private String name;
	private String ID;
	
	public TwitterGroup(String name, String ID)
	{
		this.name = name;
		this.ID = ID;
		this.isGroup = true;
		this.children = new Hashtable<String, Group>();
	}
	public void add(Group child)
	{
		children.put(child.getName(), child);
	}
	public void remove(TwitterUser user)
	{
		
	}
	//Returns all members of the group.
	public Hashtable<String, Group> getChildren()
	{
		return children;
	}
	//returns specific member of the group.
	public Group getChild(String name)
	{
		return children.get(name);
	}
	public boolean isGroup()
	{
		return isGroup;
	}
	public void setGroup(boolean isGroup) 
	{
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
	@Override
	public void accept(TwitterElementVisitor visitor) 
	{
		visitor.visit(this);
	}
}
