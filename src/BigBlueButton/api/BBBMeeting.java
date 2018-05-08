package BigBlueButton.api;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Sakai model object for a BigBlueButton meeting.
 * @author Adrian Fish
 * 
 * see https://github.com/sakaicontrib/bbb-tool/blob/master/api/src/java/org/sakaiproject/bbb/api/BBBMeeting.java
 */
@Getter @Setter @ToString
public class BBBMeeting {
	private String id = null;
	private String name = null;
	private String siteId = null;
	private String attendeePassword = null;
	private String moderatorPassword = null;
	private String ownerId = null;
	private String ownerDisplayName = null;
	private Date startDate = null;
	private Date endDate = null;
	private Boolean waitForModerator = null;
	private Boolean multipleSessionsAllowed = null;
	private String presentation = null;
	private Boolean groupSessions = null;
	private Boolean deleted = null;
	private Boolean recording = null;
	private Long recordingDuration = null;
	private String recordingEmail = null;
	
	private Map<String, String> meta = new HashMap<String, String>();
	
	@Getter (AccessLevel.NONE)
	private Props props = null;
	
	private List<Participant> participants = null;
	private String joinUrl = null;
	private Integer voiceBridge = null;
	private String hostUrl = null;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public Props getProps() {
		if(props == null)
			props = new Props();
		return props;
	}
	
	public String getReference() {
		return "/" + BBBMeetingManager.ENTITY_PREFIX + "/" + id;
	}
	
	public String getReference(String arg0) {
		return getReference();
	}
	
	public String getUrl() {
		return null;
	}
	
	public String getUrl(String arg0) {
		return getUrl();
	}
	
	public Element toXml(Document arg0, Stack<Element> arg1) {
		return null;
	}
	
	public void setWelcomeMessage(String welcomeMessage){
	    this.props.setWelcomeMessage(welcomeMessage);
	}
	
//	public ResourceProperties getProperties() {
//		return null;
//	}

}
