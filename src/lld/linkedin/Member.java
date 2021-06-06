package lld.linkedin;

import java.util.Date;
import java.util.List;

public class Member extends Person {
    private Date dateOfMembership;
    private String headline;
    private byte[] photo;
    private List<Member> memberSuggestions;
    private List<Member> memberFollows;
    private List<Member> memberConnections;
    private List<Company> companyFollows;
    private List<Group> groupFollows;
    private Profile profile;

    public boolean sendMessage(Message message) {
        return false;
    }

    public boolean createPost(Post post) {
        return false;
    }

    public boolean sendConnectionInvitation(ConnectionInvitation invitation) {
        return false;
    }
}

class ConnectionInvitation {
}