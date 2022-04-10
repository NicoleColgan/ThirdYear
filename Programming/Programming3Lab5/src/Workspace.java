import java.awt.List;
import java.util.ArrayList;

/**
 * Class has a method to add a collaborator, and a toString
 * @author nicole Colgan 19345826
 *
 */
public class Workspace {
	private String workspaceName, workspaceDescription;
	private UserAccount owner;
	private java.util.List<UserAccount> collaborators;
	public Workspace(String name, String des, UserAccount owner) {
		setWorkspaceName(name);
		setWorkspaceDescription(des);
		this.setOwner(owner);
		collaborators= new ArrayList<>();
	}
	public String getWorkspaceName() {
		return workspaceName;
	}
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}
	public String getWorkspaceDescription() {
		return workspaceDescription;
	}
	public void setWorkspaceDescription(String workspaceDescription) {
		this.workspaceDescription = workspaceDescription;
	}
	public UserAccount getOwner() {
		return owner;
	}
	public void setOwner(UserAccount owner) {
		this.owner = owner;
	}
	public void addCollaborator(UserAccount u) {
		if(u != null) 
			collaborators.add(u);
	}
	@Override
	public String toString() {
		return "Work space name: "+workspaceName+"\nCollaborators:\n"+printCollabs()+"\n";
	}
	public String printCollabs() {
		String returnStr="";
		for(UserAccount u: collaborators) {
			returnStr+="\n"+u;
		}
		return returnStr;
	}
}
