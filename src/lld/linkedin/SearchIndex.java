package lld.linkedin;

import java.util.HashMap;
import java.util.List;

public class SearchIndex implements Search {
    HashMap<String, List<Member>> memberNames;
    HashMap<String, List<Company>> companyNames;
    HashMap<String, List<JobPosting>> jobTitles;

    //	public boolean addMember(Member member) {
    //		if (memberNames.containsKey(member.getName())) {
    //			memberNames.get(member.getName()).add(member);
    //		} else {
    //			memberNames.put(member.getName(), member);
    //		}
    //	}

    public boolean addCompany(Company company) {
        return false;
    }

    public boolean addJobPosting(JobPosting jobPosting) {
        return false;
    }

    public List<Member> searchMember(String name) {
        return memberNames.get(name);
    }

    public List<Company> searchCompany(String name) {
        return companyNames.get(name);
    }

    public List<JobPosting> searchJob(String title) {
        return jobTitles.get(title);
    }
}