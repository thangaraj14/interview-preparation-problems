package lld.cricinfo;

import java.util.Date;
import java.util.List;

public abstract class Match {
    private int number;
    private Date startTime;
    private MatchResult result;

    private Playing11[] teams;
    private List<Inning> innings;
    private List<Umpire> umpires;
    private Referee referee;
    private List<Commentator> commentators;
/*	private List<MatchStat> matchStats;

	public boolean assignStatdium(Stadium stadium) {
		return false;
	}

	public boolean assignRefree(Refree refree) {
		return false;
	}*/
}
