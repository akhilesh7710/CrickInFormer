package com.CrickInFormer.entities;


import java.util.Date;

import com.CrickInFormer.entities.MatchStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="crick_matches")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int matchId;
	@Column(unique = true)
	private String teamHeading;
	private String matchNumberVenue;
	private String battingTeam;
	private String battingTeamScore;
	private String bowlTeam;
	private String liveText;
	private String matchLink;
	private String textComplete;
	
	@Enumerated
	private MatchStatus status;
	
private Date date=new Date();

public Match() {
	
}	

public Match(int matchId, String teamHeading, String matchNumberVenue, String battingTeam, String battingTeamScore,
		String bowlTeam, String liveText, String matchLink, String textComplete, MatchStatus status, Date date) {
	super();
	this.matchId = matchId;
	this.teamHeading = teamHeading;
	this.matchNumberVenue = matchNumberVenue;
	this.battingTeam = battingTeam;
	this.battingTeamScore = battingTeamScore;
	this.bowlTeam = bowlTeam;
	this.liveText = liveText;
	this.matchLink = matchLink;
	this.textComplete = textComplete;
	this.status = status;
	this.date = date;
}

public int getMatchId() {
	return matchId;
}

public void setMatchId(int matchId) {
	this.matchId = matchId;
}

public String getTeamHeading() {
	return teamHeading;
}

public void setTeamHeading(String teamHeading) {
	this.teamHeading = teamHeading;
}

public String getMatchNumberVenue() {
	return matchNumberVenue;
}

public void setMatchNumberVenue(String matchNumberVenue) {
	this.matchNumberVenue = matchNumberVenue;
}

public String getBattingTeam() {
	return battingTeam;
}

public void setBattingTeam(String battingTeam) {
	this.battingTeam = battingTeam;
}

public String getBattingTeamScore() {
	return battingTeamScore;
}

public void setBattingTeamScore(String battingTeamScore) {
	this.battingTeamScore = battingTeamScore;
}

public String getBowlTeam() {
	return bowlTeam;
}

public void setBowlTeam(String bowlTeam) {
	this.bowlTeam = bowlTeam;
}

public String getLiveText() {
	return liveText;
}

public void setLiveText(String liveText) {
	this.liveText = liveText;
}

public String getMatchLink() {
	return matchLink;
}

public void setMatchLink(String matchLink) {
	this.matchLink = matchLink;
}

public String getTextComplete() {
	return textComplete;
}

public void setTextComplete(String textComplete) {
	this.textComplete = textComplete;
}

public MatchStatus getStatus() {
	return status;
}

public void setStatus(MatchStatus status) {
	this.status = status;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public void setMatchStatus() {
    if (textComplete == null || textComplete.isBlank()) {
        this.status = MatchStatus.LIVE;
    } else {
        this.status = MatchStatus.COMPLETED;
    }
}

@Override
public String toString() {
	return "Match [matchId=" + matchId + ", teamHeading=" + teamHeading + ", matchNumberVenue=" + matchNumberVenue
			+ ", battingTeam=" + battingTeam + ", battingTeamScore=" + battingTeamScore + ", bowlTeam=" + bowlTeam
			+ ", liveText=" + liveText + ", matchLink=" + matchLink + ", textComplete=" + textComplete + ", status="
			+ status + ", date=" + date + "]";
}

public void setBowlTeamScore(String bowlTeamScore) {
	// TODO Auto-generated method stub
	
}


}
	
	
	


