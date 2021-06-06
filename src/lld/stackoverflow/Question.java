package lld.stackoverflow;

import java.util.Date;
import java.util.List;

public class Question implements Search {
    private String title;
    private String description;
    private int viewCount;
    private int voteCount;
    private Date creationTime;
    private Date updateTime;
    private QuestionStatus status;
    private QuestionClosingRemark closingRemark;

    private Member askingMember;
    private Bounty bounty;
    private List<Photo> photos;
    private List<Comment> comments;
    private List<Answer> answers;

    public void close() {
    }

    public void undelete() {
    }

    public void addComment(Comment comment) {
    }

    public void addBounty(Bounty bounty) {
    }

    public static List<Question> search(String query) {
        return null;
    }
}
