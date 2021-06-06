package lld.linkedin;

import java.util.List;

public interface Search {
    public List<Member> searchMember(String name);

    public List<Company> searchCompany(String name);

    public List<JobPosting> searchJob(String title);
}

