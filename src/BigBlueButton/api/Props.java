package BigBlueButton.api;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Additional properties for the BigBlueButton meeting model object.
 * 
 * see https://github.com/sakaicontrib/bbb-tool/blob/master/api/src/java/org/sakaiproject/bbb/api/Props.java
 */
@Getter @Setter
public class Props implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	private String				welcomeMessage		= null;
	private String				calendarEventId		= null;

}
