package BigBlueButton.api;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Represents a user in the system
 * Allows for better control over the data being input and output (than the core Sakai classes)
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com) (azeckoski @ vt.edu) (azeckoski @ unicon.net)
 * 
 * see https://github.com/sakaiproject/sakai/blob/master/gradebook/grades-rest/src/main/java/org/sakaiproject/gradebook/entity/User.java
 */
public class User {

    public String userId;
    public String username;
    public String name;
    public String fname;
    public String lname;
    public String email;
    public String sortName;

    protected User() {
    }

    public User(String userId, String username, String name) {
        this(userId, username, name, null, null);
    }

    public User(String userId, String username, String name, String sortName, String email) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.sortName = sortName;
        this.email = email;
    }

    @Override
    public String toString() {
        return userId + ":" + username;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSortName() {
        return sortName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public static class UsernameComparator implements Comparator<User>, Serializable {

        public final static long serialVersionUID = 1l;

        public int compare(User o1, User o2) {
            return o1.username.compareTo(o2.username);
        }
    }

    public static class SortnameComparator implements Comparator<User>, Serializable {

        public final static long serialVersionUID = 1l;

        public int compare(User o1, User o2) {
            return o1.sortName.compareTo(o2.sortName);
        }
    }

}