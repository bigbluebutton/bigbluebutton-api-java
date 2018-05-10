package BigBlueButton.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import BigBlueButton.api.BBBException;
import BigBlueButton.api.BBBMeeting;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Base class for interacting with any BigBlueButton API version.
 * @author Nuno Fernandes
 * 
 * Last modified by Yunkai Wang
 */
public class BaseBBBAPI implements BBBAPI {
    /**
     * BBB server url, including bigbluebutton webapp path. Will default to
     * http://localhost/bigbluebutton if not specified
     */
    protected String bbbUrl = "http://127.0.0.1/bigbluebutton";
    /** BBB security salt */
    protected String bbbSalt = null;

    // API Server Path
    protected final static String API_SERVERPATH = "/api/";

    // API Calls
    protected final static String APICALL_CREATE = "create";
    protected final static String APICALL_ISMEETINGRUNNING = "isMeetingRunning";
    protected final static String APICALL_GETMEETINGINFO = "getMeetingInfo";
    protected final static String APICALL_GETMEETINGS = "getMeetings";
    protected final static String APICALL_JOIN = "join";
    protected final static String APICALL_END = "end";
    protected final static String APICALL_VERSION = "";
    protected final static String APICALL_getRecordS = "getRecordings";
    protected final static String APICALL_PUBLISHRECORDINGS = "publishRecordings";
    protected final static String APICALL_UPDATERECORDINGS = "updateRecordings";
    protected final static String APICALL_DELETERECORDINGS = "deleteRecordings";
    protected final static String APICALL_GETCONFIGXML = "getDefaultConfigXML";
    protected final static String APICALL_SETCONFIGXML = "setConfigXML";
    
    // API Response Codes
    protected final static String APIRESPONSE_SUCCESS = "SUCCESS";
    protected final static String APIRESPONSE_FAILED = "FAILED";

    // API Versions
    public final static String APIVERSION_063 = "0.63";
    public final static String APIVERSION_064 = "0.64";
    public final static String APIVERSION_070 = "0.70";
    public final static String APIVERSION_080 = "0.80";
    public final static String APIVERSION_081 = "0.81";
    public final static String APIVERSION_MINIMUM = APIVERSION_063;
    public final static String APIVERSION_LATEST = APIVERSION_081;

    // -----------------------------------------------------------------------
    // --- Initialization related methods ------------------------------------
    // -----------------------------------------------------------------------
    public BaseBBBAPI(String url, String salt) {
        this.bbbUrl = url;

        if (bbbUrl.endsWith("/") && bbbUrl.length() > 0) {
            bbbUrl = bbbUrl.substring(0, bbbUrl.length() - 1);
        }

        this.bbbSalt = salt;
    }

    public String getUrl() {
        return this.bbbUrl;
    }

    /*
    public String getSalt() {
        return this.bbbSalt;
    }
    */

    private String encode(String msg) throws UnsupportedEncodingException {
    	return URLEncoder.encode(msg, getParametersEncoding());
    }
    
    // -----------------------------------------------------------------------
    // --- BBB API implementation methods ------------------------------------
    // -----------------------------------------------------------------------
    
    /* Create BBB meeting */
    public BBBMeeting createMeeting(final String meetingID) throws BBBException {
    	return createMeeting(new BBBMeeting(meetingID), null);
    }
    
    public BBBMeeting createMeeting(final BBBMeeting meeting) throws BBBException {
    	return createMeeting(meeting, null);
    }
    
    public BBBMeeting createMeeting(final BBBMeeting meeting, final BBBModule module) throws BBBException {
    	 try {
    		 StringBuilder query = new StringBuilder();
    		 query.append("meetingID=" + meeting.getMeetingID());
    		 if (meeting.getName() != null)
    			 query.append("&name=" + encode(meeting.getName()));
    		 if (meeting.getAttendeePW() != null)
    			 query.append("&attendeePW=" + meeting.getAttendeePW());
    		 if (meeting.getModeratorPW() != null)
    			 query.append("&moderatorPW=" + meeting.getModeratorPW());
    		 if (meeting.getWelcome() != null)
    			 query.append("&welcome=" + encode(meeting.getWelcome()));
    		 if (meeting.getDialNumber() != null)
    			 query.append("&dialNumber=" + meeting.getDialNumber());
    		 if (meeting.getVoiceBridge() != null)
    			 query.append("&voiceBridge=" + meeting.getVoiceBridge());
    		 if (meeting.getWebVoice() != null)
    			 query.append("&webVoice=" + encode(meeting.getWebVoice()));
    		 if (meeting.getLogoutURL() != null)
    			 query.append("&logoutURL=" + encode(meeting.getLogoutURL()));
    		 if (meeting.getRecord() != null)
    			 query.append("&record=" + Boolean.toString(meeting.getRecord()));
    		 if (meeting.getDuration() != null)
    			 query.append("&duration=" + meeting.getDuration().toString());
             if (!meeting.getMeta().isEmpty()) {
            	 for(Entry<String, String> entry : meeting.getMeta().entrySet()) {
                     String key = entry.getKey();
                     String value = entry.getValue();
                     query.append("&meta_" + key + "=");
                     query.append(encode(value));
                 }
             }
             if (meeting.getModeratorOnlyMessage() != null)
    			 query.append("&moderatorOnlyMessage=" + encode(meeting.getModeratorOnlyMessage()));
             if (meeting.getAutoStartRecording() != null)
    			 query.append("&autoStartRecording=" + Boolean.toString(meeting.getAutoStartRecording()));
             if (meeting.getAllowStartStopRecording() != null)
    			 query.append("&allowStartStopRecording=" + Boolean.toString(meeting.getAllowStartStopRecording()));
             if (meeting.getWebcamsOnlyForModerator() != null)
    			 query.append("&logo=" + Boolean.toString(meeting.getWebcamsOnlyForModerator()));
             if (meeting.getLogo() != null)
    			 query.append("&logo=" + encode(meeting.getLogo()));
             if (meeting.getCopyright() != null)
    			 query.append("&copyright=" + encode(meeting.getCopyright()));
             if (meeting.getMuteOnStart() != null)
    			 query.append("&muteOnStart=" + Boolean.toString(meeting.getMuteOnStart()));
             query.append(getCheckSumParameterForQuery(APICALL_CREATE, query.toString()));
             
             Map<String, Object> response = doAPICall(APICALL_CREATE, query.toString(),
            		 module == null ? null : module.to_xml());

             // capture important information from returned response
             meeting.setModeratorPW((String)response.get("moderatorPW"));
             meeting.setAttendeePW((String)response.get("attendeePW"));
             meeting.setDialNumber((String)response.get("dialNumber"));
             meeting.setVoiceBridge((String)response.get("voiceBridge"));
             SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
             try {
				meeting.setStartDate(formatter.parse((String)response.get("createDate")));
			} catch (ParseException e) { }
             
             return meeting;
         } catch (BBBException e) {
             throw e;
         } catch (IOException e) {
             throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
         }
    }

    /* Check if meeting is running */
    public boolean isMeetingRunning(String meetingID)
            throws BBBException {
        try {
            StringBuilder query = new StringBuilder();
            query.append("meetingID=" + meetingID);
            query.append(getCheckSumParameterForQuery(APICALL_ISMEETINGRUNNING, query.toString()));

            Map<String, Object> response = doAPICall(APICALL_ISMEETINGRUNNING, query.toString());
            return Boolean.parseBoolean((String) response.get("running"));
        } catch (Exception e) {
            throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
        }
    }

    /* Get list of all running BBB meetings */
    public Map<String, Object> getMeetings() throws BBBException {
        try {
            StringBuilder query = new StringBuilder();
            query.append(getCheckSumParameterForQuery(APICALL_GETMEETINGS, query.toString()));
            Map<String, Object> response = doAPICall(APICALL_GETMEETINGS, query.toString());

            return response;
        } catch (Exception e) {
            throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
        }
    }

    /* Get BBB meeting information */
    public Map<String, Object> getMeetingInfo(final BBBMeeting meeting) throws BBBException {
    	return getMeetingInfo(meeting.getMeetingID(), meeting.getModeratorPW());
    }
    
    public Map<String, Object> getMeetingInfo(String meetingID, String password)
            throws BBBException {
        try {
            StringBuilder query = new StringBuilder();
            query.append("meetingID=" + meetingID);
            query.append("&password=" + password);
            query.append(getCheckSumParameterForQuery(APICALL_GETMEETINGINFO, query.toString()));
            Map<String, Object> response = doAPICall(APICALL_GETMEETINGINFO, query.toString());
            return response;
        } catch (BBBException e) {
            throw new BBBException(e.getMessageKey(), e.getMessage(), e);
        }
    }

    /* End given BBB meeting */
    public boolean endMeeting(final BBBMeeting meeting) throws BBBException {
    	return endMeeting(meeting.getMeetingID(), meeting.getModeratorPW());
    }
    
    public boolean endMeeting(String meetingID, String password) throws BBBException {
    	StringBuilder query = new StringBuilder();
        query.append("meetingID=" + meetingID);
        query.append("&password=" + password);
        query.append(getCheckSumParameterForQuery(APICALL_END, query.toString()));

        try {
            doAPICall(APICALL_END, query.toString());
        } catch (BBBException e) {
			if(BBBException.MESSAGEKEY_NOTFOUND.equals(e.getMessageKey())) {
				// we can safely ignore this one: the meeting is not running
				return true;
			}else{
				throw e;
			}
        }

        return true;
    }
    
    /** Get recordings from BBB server */
	public Map<String, Object> getRecordings() throws BBBException {
		return getRecordings(null, null, null, null);
	}
	
	public Map<String, Object> getRecordings(String meetingIDs) throws BBBException {
		return getRecordings(meetingIDs, null, null, null);
	}
	
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs) throws BBBException {
		return getRecordings(meetingIDs, recordIDs, null, null);
	}
	
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states) throws BBBException {
		return getRecordings(meetingIDs, recordIDs, states, null);
	}
    
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states, Map<String, String> meta) throws BBBException {
	  	  try {
	          StringBuilder query = new StringBuilder();
	          if (meetingIDs != null)
	          	query.append("meetingID=" + meetingIDs);
	          if (recordIDs != null)
	            query.append("recordID=" + recordIDs);
	          if (states != null)
		        query.append("state=" + states);
	          if (meta != null && meta.size() != 0) {
	        	  for(Entry<String, String> entry : meta.entrySet()) {
	        		  String key = entry.getKey();
	                  String value = entry.getValue();
	                  query.append("&meta_" + key + "=");
	                  query.append(encode(value));
	              }
	          }
	          query.append(getCheckSumParameterForQuery(APICALL_getRecordS, query.toString()));
	          Map<String, Object> response = doAPICall(APICALL_getRecordS, query.toString());

	          return response;
	      } catch (BBBException e) {
	          throw new BBBException(e.getMessageKey(), e.getMessage(), e);
	      } catch (IOException e) {
	             throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
	      }
	}
	
	/* Detete a record from BBB server */
	public boolean deteteRecordings(String recordIDs) throws BBBException {
		StringBuilder query = new StringBuilder();
        query.append("recordID=" + recordIDs);
        query.append(getCheckSumParameterForQuery(APICALL_DELETERECORDINGS, query.toString()));
        try {
            Map<String, Object> response = doAPICall(APICALL_DELETERECORDINGS, query.toString());
            return response.get("returncode").toString().equals("SUCCESS") ? true : false;
        } catch (BBBException e) {
            throw e;
        }
	}

    /* Publish/Unpublish a recording on BBB server */
    public boolean publishRecordings(String recordIDs, boolean publish) throws BBBException {
        StringBuilder query = new StringBuilder();
        query.append("recordID=" + recordIDs);
        query.append("&publish=" + Boolean.toString(publish));
        query.append(getCheckSumParameterForQuery(APICALL_PUBLISHRECORDINGS, query.toString()));

        try {
            Map<String, Object> response = doAPICall(APICALL_PUBLISHRECORDINGS, query.toString());
            return response.get("returncode").toString().equals("SUCCESS") ? true : false;
        } catch (BBBException e) {
            throw e;
        }
    }

    /* Update a recording on BBB server */
    public boolean updateRecordings(String recordingIDs) throws BBBException {
    	return updateRecordings(recordingIDs, null);
    }
    
	public boolean updateRecordings(String recordingIDs, Map<String, String> meta) throws BBBException {
		try {
			StringBuilder query = new StringBuilder();
	        query.append("recordID=" + recordingIDs);
	        if (meta != null && meta.size() != 0) {
	      	  for(Entry<String, String> entry : meta.entrySet()) {
	      		  String key = entry.getKey();
	                String value = entry.getValue();
	                query.append("&meta_" + key + "=");
	                query.append(encode(value));
	            }
	        }
	        query.append(getCheckSumParameterForQuery(APICALL_UPDATERECORDINGS, query.toString()));
	        Map<String, Object> response =  doAPICall(APICALL_UPDATERECORDINGS, query.toString());
	        return response.get("returncode").toString().equals("SUCCESS") ? true : false;
        } catch (BBBException e) {
            throw e;
        } catch (IOException e) {
            throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
        }
	}

	/* Build the join meeting url based on user role */
	public String getJoinMeetingURL(String meetingID, String password, String userDisplayName) {
		return getJoinMeetingURL(meetingID, password, userDisplayName, null);
	}
    
    public String getJoinMeetingURL(String meetingID, String password, String userDisplayName, String userId) {
    	StringBuilder url = null;
    	try {
	        StringBuilder joinQuery = new StringBuilder();
	        joinQuery.append("meetingID=" + meetingID);
	        if (userId != null)
	        	joinQuery.append("&userID=" + encode(userId));
	        
	        joinQuery.append("&fullName=");
	        userDisplayName = (userDisplayName == null) ? "user" : userDisplayName;
	        try {
	        	joinQuery.append(encode(userDisplayName));
	        } catch (UnsupportedEncodingException e) {
	        	joinQuery.append(userDisplayName);
	        }
	        joinQuery.append("&password=" + password);
	        joinQuery.append(getCheckSumParameterForQuery(APICALL_JOIN, joinQuery.toString()));
	
	        url = new StringBuilder(bbbUrl);
	        if (url.toString().endsWith("/api")) {
	            url.append("/");
	        } else {
	            url.append(API_SERVERPATH);
	        }
	        url.append(APICALL_JOIN + "?" + joinQuery);
    	} catch (UnsupportedEncodingException e) { }
        return url.toString();
    }

    /* Download default config xml file from the server and save the file to given file location */
    public boolean getDefaultConfigXML(String filePath) throws BBBException {
    	try {
    		StringBuilder query = new StringBuilder();
            query.append(getCheckSumParameterForQuery(APICALL_GETCONFIGXML, query.toString()));
            Map<String, Object> response = doAPICall(APICALL_GETCONFIGXML, query.toString());
            
            File file = new File(filePath);
			if (file.exists() && !file.canWrite()) {
				throw new IOException("Failed to edit " + filePath);
			} else if (!file.exists()) {
				if (!file.createNewFile())
					throw new IOException("Failed to create " + filePath);
			}
            
            FileOutputStream output = new FileOutputStream(file);
            output.write(((String)response.get("xml")).getBytes());
            output.close();
        	return true;
    	} catch (BBBException e) {
    		throw e;
    	} catch (IOException e) {
            throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
        }
    }
    
    /* set the config.xml file for the given meeting */
    public boolean setConfigXML(String meetingID, String filePath) throws BBBException {
    	try {
    		StringBuilder query = new StringBuilder();
    		query.append("meetingID=" + meetingID);
            query.append(getCheckSumParameterForQuery(APICALL_SETCONFIGXML, query.toString()));
            
            File file = new File(filePath);
			if (!file.exists() || !file.canRead()) {
				throw new IOException("Failed to read " + filePath);
			}
            
            FileInputStream input = new FileInputStream(file);
            byte[] b = input.readAllBytes();
            input.close();
            String xml = "";
            for (byte a : b) {
            	xml += (char)a;
            }
            System.out.println(xml);
            query.append("&configXML=" + encode(xml));

            Map<String, Object> response = doAPICall(APICALL_SETCONFIGXML, query.toString());
            
            System.out.println("In function: ");
            for (Entry<String, Object> e : response.entrySet()) {
    			System.out.println("Key: " + e.getKey() + " value: " + e.getValue());
    		}
            System.out.println("End of function");
            
            return response.get("returncode").toString().equals("SUCCESS") ? true : false;
    	} catch (BBBException e) {
    		throw e;
    	} catch (IOException e) {
            throw new BBBException(BBBException.MESSAGEKEY_INTERNALERROR, e.getMessage(), e);
        }
    }
    
    /** Get the BBB API version running on BBB server */
    public final String getAPIVersion() {
        String _version = null;
        try {
            Map<String, Object> response = doAPICall(APICALL_VERSION, null);
            _version = (String) response.get("version");
            _version = _version != null ? _version.trim() : null;
            if (_version == null || Float.valueOf(_version.substring(0, 3)) < 0.0) {
                _version = null;
            }
            _version = _version.trim();
        } catch (BBBException e) {
            if (BBBException.MESSAGEKEY_NOACTION.equals(e.getMessageKey())) {
                // we are clearly connecting to BBB < 0.70 => assuming minimum
                // version (0.63)
                _version = APIVERSION_MINIMUM;
            } else {
                // something went wrong => warn user
                _version = null;
            }
        } catch (Exception e) {
            // something went wrong => warn user
            _version = null;
        }
        return _version;
    }

    // -----------------------------------------------------------------------
    // --- BBB API utility methods -------------------------------------------
    // -----------------------------------------------------------------------
    /** Compute the query string checksum based on the security salt */
    protected String getCheckSumParameterForQuery(String apiCall,
            String queryString) {
        if (bbbSalt != null)
            return "&checksum=" + DigestUtils.shaHex(apiCall + queryString + bbbSalt);
        else
            return "";
    }

    /** Encoding used when encoding url parameters */
    protected String getParametersEncoding() {
        return "UTF-8";
    }


    /* Make an API call */
    protected Map<String, Object> doAPICall(String apiCall, String query) throws BBBException {
        return doAPICall(apiCall, query, null);
    }

    protected Map<String, Object> doAPICall(String apiCall, String query, String data) throws BBBException {
        StringBuilder urlStr = new StringBuilder(bbbUrl);
        if (urlStr.toString().endsWith("/api")){
            urlStr.append("/");
        } else {
            urlStr.append(API_SERVERPATH);
        }
        urlStr.append(apiCall);
        if (query != null) {
            urlStr.append("?");
            urlStr.append(query);
        }
        
        try {
            // open connection
            URL url = new URL(urlStr.toString());
            System.out.println(urlStr.toString());
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setUseCaches(false);
            httpConnection.setDoOutput(true);
            if(data != null){
                httpConnection.setRequestMethod("POST");
                httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpConnection.setRequestProperty("Content-Length", "" + data.length());
                httpConnection.setRequestProperty("Content-Language", "en-US");
                httpConnection.setDoInput(true);

                DataOutputStream wr = new DataOutputStream( httpConnection.getOutputStream() );
                wr.writeBytes (data);
                wr.flush();
                wr.close();
            } else {
                httpConnection.setRequestMethod("GET");
            }
            httpConnection.connect();
            
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // read response
                InputStreamReader isr = null;
                BufferedReader reader = null;
                StringBuilder xml = new StringBuilder();
                try {
                    isr = new InputStreamReader(httpConnection.getInputStream(), "UTF-8");
                    reader = new BufferedReader(isr);
                    String line = reader.readLine();
                    while (line != null) {
                    	if( !line.startsWith("<?xml version=\"1.0\"?>"))
                    		xml.append(line.trim());
                        line = reader.readLine();
                    }
                } finally {
                    if (reader != null)
                        reader.close();
                    if (isr != null)
                        isr.close();
                }
                httpConnection.disconnect();

                // parse response
                //Patch to fix the NaN error
                String stringXml = xml.toString();
                stringXml = stringXml.replaceAll(">.\\s+?<", "><");
                
                if (apiCall.equals(APICALL_GETCONFIGXML)) {
                	Map<String, Object> map = new HashMap<String, Object>();
                	map.put("xml", stringXml);
                	return map;
                }
                
                Document dom = null;

                // Initialize XML libraries
                DocumentBuilderFactory docBuilderFactory;
                DocumentBuilder docBuilder;
                docBuilderFactory = DocumentBuilderFactory.newInstance();
                try {
                    docBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
                    docBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

                    docBuilder = docBuilderFactory.newDocumentBuilder();
                    dom = docBuilder.parse(new InputSource( new StringReader(stringXml)));
                } catch (ParserConfigurationException e) { }
                Map<String, Object> response = getNodesAsMap(dom, "response");

                String returnCode = (String) response.get("returncode");
                if (APIRESPONSE_FAILED.equals(returnCode)) {
                    throw new BBBException((String) response.get("messageKey"), (String) response.get("message"));
                }
                
                return response;
            } else {
                throw new BBBException(BBBException.MESSAGEKEY_HTTPERROR, "BBB server responded with HTTP status code " + responseCode);
            }

		} catch(BBBException e) {
			throw new BBBException( e.getMessageKey(), e.getMessage(), e);
        } catch(IOException e) {
            throw new BBBException(BBBException.MESSAGEKEY_UNREACHABLE, e.getMessage(), e);
        } catch(SAXException e) {
        	throw new BBBException(BBBException.MESSAGEKEY_INVALIDRESPONSE, e.getMessage(), e);
        } catch(IllegalArgumentException e) {
        	throw new BBBException(BBBException.MESSAGEKEY_INVALIDRESPONSE, e.getMessage(), e);
        } catch(Exception e) {
            throw new BBBException(BBBException.MESSAGEKEY_UNREACHABLE, e.getMessage(), e);
        }
    }


    // -----------------------------------------------------------------------
    // --- BBB Other utility methods -----------------------------------------
    // -----------------------------------------------------------------------
    /** Get all nodes under the specified element tag name as a Java map */
    protected Map<String, Object> getNodesAsMap(Document dom, String elementTagName) {
        Node firstNode = dom.getElementsByTagName(elementTagName).item(0);
        return processNode(firstNode);
    }

    protected Map<String, Object> processNode(Node _node) {
        Map<String, Object> map = new HashMap<String, Object>();
        NodeList responseNodes = _node.getChildNodes();
        int images = 1; //counter for images (i.e image1, image2, image3)
        for (int i = 0; i < responseNodes.getLength(); i++) {
            Node node = responseNodes.item(i);
            String nodeName = node.getNodeName().trim();
            if (node.getChildNodes().getLength() == 1
                    && ( node.getChildNodes().item(0).getNodeType() == org.w3c.dom.Node.TEXT_NODE || node.getChildNodes().item(0).getNodeType() == org.w3c.dom.Node.CDATA_SECTION_NODE) ) {
            	String nodeValue = node.getTextContent();
                if (nodeName == "image" && node.getAttributes() != null){
                    Map<String, String> imageMap = new HashMap<String, String>();
                    Node heightAttr = node.getAttributes().getNamedItem("height");
                    Node widthAttr = node.getAttributes().getNamedItem("width");
                    Node altAttr = node.getAttributes().getNamedItem("alt");

                    imageMap.put("height", heightAttr.getNodeValue());
                    imageMap.put("width", widthAttr.getNodeValue());
                    imageMap.put("title", altAttr.getNodeValue());
                    imageMap.put("url", nodeValue);
                    map.put(nodeName + images, imageMap);
                    images++;
                } else {
                    map.put(nodeName, nodeValue != null ? nodeValue.trim() : null);
                }
            } else if (node.getChildNodes().getLength() == 0
                    && node.getNodeType() != org.w3c.dom.Node.TEXT_NODE
                    && node.getNodeType() != org.w3c.dom.Node.CDATA_SECTION_NODE) {
                map.put(nodeName, "");
            } else if (node.getChildNodes().getLength() >= 1) {
            	boolean isList = false;
            	for (int c = 0; c < node.getChildNodes().getLength(); ++c) {
            		try {
            			Node n = node.getChildNodes().item(c);
            			if (n.getChildNodes().item(0).getNodeType() != org.w3c.dom.Node.TEXT_NODE
                              && n.getChildNodes().item(0).getNodeType() != org.w3c.dom.Node.CDATA_SECTION_NODE) {
            				isList = true;
            				break;
            			}
            		} catch (Exception e) {
            			continue;
            		}
            	}
            	List<Object> list = new ArrayList<Object>();
            	if (isList) {
            		for (int c = 0; c < node.getChildNodes().getLength(); ++c) {
            			Node n = node.getChildNodes().item(c);
            			list.add(processNode(n));
            		}
            		if (nodeName == "preview"){
            			Node n = node.getChildNodes().item(0);
            			map.put(nodeName, new ArrayList<Object>(processNode(n).values()));
            		} else {
            			map.put(nodeName, list);
            		}
            	} else {
            		map.put(nodeName, processNode(node));
            	}
            } else {
                map.put(nodeName, processNode(node));
            }
        }
        return map;
    }
}
