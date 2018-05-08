package BigBlueButton.impl;

import java.util.Map;
import BigBlueButton.api.BBBException;
import BigBlueButton.api.BBBMeeting;

/**
 * see https://github.com/sakaicontrib/bbb-tool/blob/master/impl/src/java/org/sakaiproject/bbb/impl/bbbapi/BBBAPI.java
 */
public interface BBBAPI {

	public String getAPIVersion();

	public String getUrl();

	public String getSalt();

	public BBBMeeting createMeeting(BBBMeeting meeting, boolean autoclose, boolean recordingenabled,
			boolean recordingreadynotification, boolean preuploadpresentation, String logoutURL)
			throws BBBException;

	public boolean isMeetingRunning(String meetingID) throws BBBException;

	public Map<String, Object> getMeetingInfo(String meetingID, String password) throws BBBException;

	public Map<String, Object> getRecordings(String meetingID) throws BBBException;

	public boolean endMeeting(String meetingID, String password) throws BBBException;

	public boolean deleteRecordings(String meetingID, String recordID) throws BBBException;

	public boolean publishRecordings(String meetingID, String recordID, String publish) throws BBBException;

	public boolean protectRecordings(String meetingID, String recordID, String protect) throws BBBException;

	public String getJoinMeetingURL(String meetingID, String userId, String userDisplayName, String password);

	public void makeSureMeetingExists(BBBMeeting meeting, boolean autoclose, boolean recordingenabled, boolean recordingreadynotification, boolean preuploadpresentation)
			throws BBBException;

	public Map<String, Object> getMeetings() throws BBBException;

}
