package BigBlueButton.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Model object representing a BigBlueButton meeting participant.
 * @author Adrian Fish,Nuno Fernandes
 * 
 * see https://github.com/sakaicontrib/bbb-tool/blob/master/api/src/java/org/sakaiproject/bbb/api/Participant.java
 */
@Getter @Setter
public class Participant {
	public static final String	MODERATOR		= "moderator";
	public static final String	ATTENDEE		= "attendee";

	public static final String	SELECTION_ALL	= "all";
	public static final String	SELECTION_GROUP	= "group";
	public static final String	SELECTION_ROLE	= "role";
	public static final String	SELECTION_USER	= "user";
	
	private String selectionType = null;
	private String selectionId = null;
	private String role = null;
	
	public Participant() {}
	
	public Participant(String selectionType, String selectionId, String role) {
		this.selectionType = selectionType;
		this.selectionId = selectionId;
		this.role = role;
	}
	
	public boolean isModerator() {
		return MODERATOR.equals(role);
	}
	
	public String toString(){
	    return "[" + selectionType + ", " + selectionId + ", " + role + "]"; 
	}
}