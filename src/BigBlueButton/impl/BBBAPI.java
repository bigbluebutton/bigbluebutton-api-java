package BigBlueButton.impl;

import java.util.Map;
import BigBlueButton.api.BBBException;
import BigBlueButton.api.BBBMeeting;

/**
 * List of supported API for BigBlueButton, see BigBlueButton doc for more information
 * Based on: https://github.com/sakaicontrib/bbb-tool/blob/master/impl/src/java/org/sakaiproject/bbb/impl/bbbapi/BBBAPI.java
 * 
 * Last modified by Yunkai Wang
 */
public interface BBBAPI {
	
	/**
	 * Get the API version of the server
	 * 
	 * @return API version
	 */
	public String getAPIVersion();

	/**
	 * Get the base url of the server
	 * 
	 * @return url
	 */
	public String getUrl();

	/**
     * Allow user to create meeting
     * 
     * @return BBBMeeting object on success, on failure, BBBException is thrown
     * @throws BBBException
     */
	public BBBMeeting createMeeting(final String meetingID) throws BBBException;
	public BBBMeeting createMeeting(final BBBMeeting meeting) throws BBBException;
	public BBBMeeting createMeeting(final BBBMeeting meeting, final BBBModule module) throws BBBException;
	
	/**
	 * Check if the meeting is already running
	 * 
	 * @return true if the meeting is running, false otherwise
	 * @throws BBBException
	 */
	public boolean isMeetingRunning(String meetingID) throws BBBException;
	
	/**
	 * Get meeting information corresponds to the given meetingID and role
	 * 
	 * @return Map that contains all meeting information
	 * @throws BBBException
	 */
	public Map<String, Object> getMeetingInfo(String meetingID, String password) throws BBBException;
	public Map<String, Object> getMeetingInfo(final BBBMeeting meeting) throws BBBException;

	/**
	 * End the given meeting
	 * 
	 * @return true if the meeting is successfully ended or does not exist, false otherwise
	 * @throws BBBException
	 */
	public boolean endMeeting(String meetingID, String password) throws BBBException;
	public boolean endMeeting(final BBBMeeting meeting) throws BBBException;

	/**
	 * Get the url to join the given meeting with the display name and corresponding role type
	 * 
	 * @return url for joining the meeting
	 */
	public String getJoinMeetingURL(String meetingID, String password, String userDisplayName);
	public String getJoinMeetingURL(String meetingID, String password, String userDisplayName, String userId);
	
	/**
	 * Get the list of all live meetings in server, every parameter like meetingIDs can be a list of meetings but separated
	 * by commas(e.g., "id1,id2")
	 * 
	 * @return a map which has a field named meetings, and the value is the list of meeting information
	 * @throws BBBException
	 */
	public Map<String, Object> getMeetings() throws BBBException;
	
	/**
	 * Get the list of recordings that map the given fields
	 * 
	 * @return a map which has a field named recordings, and the value is the list of recording information
	 * @throws BBBException
	 */
	public Map<String, Object> getRecordings() throws BBBException;
	public Map<String, Object> getRecordings(String meetingIDs) throws BBBException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs) throws BBBException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states) throws BBBException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states, Map<String, String> meta) throws BBBException;
	
	/**
	 * Delete a given recording (or a list of recordings whose ids are separated by commas)
	 * 
	 * @return true if the recording is successfully deleted, exception is thrown on failure
	 * @throws BBBException
	 */
	public boolean deteteRecordings(String recordIDs) throws BBBException;
	
	/**
	 * Publish/unpublish the recording(s)
	 * 
	 * @return true if the recording(s) is successfully published/unpublished, exception is thrown on failure
	 * @throws BBBException
	 */
	public boolean publishRecordings(String recordIDs, boolean publish) throws BBBException;

	/**
	 * Update the recording(s)
	 * 
	 * @return true if the recording(s) is successfully updated, exception is thrown on failure
	 * @throws BBBException
	 */
	public boolean updateRecordings(String recordIDs) throws BBBException;
	public boolean updateRecordings(String recordIDs, Map<String, String> meta) throws BBBException;
	
	/**
	 * Get the default config xml file from the BBB server and save it to the given file path
	 * 
	 * @return true on success, on failure, BBBException is thrown
	 * @throws BBBException
	 */
	public boolean getDefaultConfigXML(String fileName) throws BBBException;
	
	
	/**
	 * Set the config for the given meeting
	 * 
	 * @return true on success, on failure, BBBException is thrown
	 * @throws BBBException
	 */
	public boolean setConfigXML(String meetingID, String fileName) throws BBBException;
}
