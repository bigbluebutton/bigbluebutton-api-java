package BigBlueButton.api;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Object for a BigBlueButton meeting.
 * @author Adrian Fish
 * Based on: https://github.com/sakaicontrib/bbb-tool/blob/master/api/src/java/org/sakaiproject/bbb/api/BBBMeeting.java
 * 
 * Last modified by Yunkai Wang
 */
@Getter @Setter @ToString
public class BBBMeeting {
	private String name = null;
	private String meetingID;
	private String attendeePW = null;
	private String moderatorPW = null;
	private String dialNumber = null;
	private String voiceBridge = null;
	private String webVoice = null;
	private String logoutURL = null;
	private Boolean record = null;
	private Long duration = null;
	
	// user cannot directly modify this field
	@Setter (AccessLevel.NONE)
	private Map<String, String> meta = new HashMap<String, String>();
	private String moderatorOnlyMessage = null;
	private Boolean autoStartRecording = null;
	private Boolean allowStartStopRecording = null;
	private Boolean webcamsOnlyForModerator = null;
	private String logo = null;
	private String copyright = null;
	private Boolean muteOnStart = null;
	private String welcome = null;
	private Date startDate = null;
	private Date endDate = null;
	
	public BBBMeeting(String meetingID) {
		this.meetingID = meetingID;
	}
	
	public void addMeta(String key, String value) {
		meta.put(key, value);
	}
	
	public void removeMeta(String key) {
		if (meta.containsKey(key))
			meta.remove(key);
	}
	
//	private String siteId = null;
//	private String ownerId = null;
//	private String ownerDisplayName = null;

//	private Boolean waitForModerator = null;
//	private Boolean multipleSessionsAllowed = null;
//	private String presentation = null;
//	private Boolean groupSessions = null;
//	private Boolean deleted = null;
//	private String recordingEmail = null;
	
//	private String joinUrl = null;
//	private String hostUrl = null;
}
