package com.CrickInFormer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrickInFormer.entities.Match;
import com.CrickInFormer.repositry.MatchRepo;

import jakarta.transaction.Transactional;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchRepo matchRepo;

	//match history all matches in our database  (39:32)
	@Override
	public List<Match> getAllMatches() {
		return matchRepo.findAll();
	}

	@Override
	@Transactional
	//scrapping data from live website 40.min..( JSoup (a web scraping library) 
	public List<Match> getLivMatches(){

		List<Match> matches = new ArrayList<>();
		try {
			String url = "https://www.cricbuzz.com/cricket-match/live-scores";
			Document document = Jsoup.connect(url).get();
			Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");

			for (Element matchElement : liveScoreElements) {
				String teamsHeading = matchElement.select("h3.cb-lv-scr-mtch-hdr a").text();
				String matchNumberVenue = matchElement.select("span").text();

				// Batting team information
				Elements matchBatTeamInfo = matchElement.select("div.cb-hmscg-bat-txt");
				String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
				String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();

				// Bowling team information
				Elements bowlTeamInfo = matchElement.select("div.cb-hmscg-bwl-txt");
				String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
				String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();

				// Match text updates
				String textLive = matchElement.select("div.cb-text-live").text();
				String textComplete = matchElement.select("div.cb-text-complete").text();

				// Match link
				String matchLink = matchElement.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href"); // FIXED: Removed .toString()

				// Create and populate Match object
				Match match1 = new Match();
				match1.setTeamHeading(teamsHeading);
				match1.setMatchNumberVenue(matchNumberVenue);
				match1.setBattingTeam(battingTeam);
				match1.setBattingTeamScore(score);
				match1.setBowlTeam(bowlTeam);
				match1.setBowlTeamScore(bowlTeamScore);
				match1.setLiveText(textLive);
				match1.setMatchLink(matchLink);
				match1.setTextComplete(textComplete);
				match1.setMatchStatus();

				matches.add(match1);
				updateMatchInDb(match1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matches;
	}

	private void updateMatchInDb(Match match1) {

		Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
		if (match == null) {
			this.matchRepo.save(match1);
		} else {

			match1.setMatchId(match.getMatchId());
			this.matchRepo.save(match1);
		}

	}

	@Override
	public List<List<String>> getPointTable() {

		List<List<String>> pointTable = new ArrayList<>();
		String tableURL = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/points-table";
		try {
			Document document = Jsoup.connect(tableURL).get();
			Elements table = document.select("table.cb-srs-pnts");
			Elements tableHeads = table.select("thead>tr>*");
			List<String> headers = new ArrayList<>();
			tableHeads.forEach(element -> {
				headers.add(element.text());
			});
			pointTable.add(headers);
			Elements bodyTrs = table.select("tbody>*");
			bodyTrs.forEach(tr -> {
				List<String> points = new ArrayList<>();
				if (tr.hasAttr("class")) {
					Elements tds = tr.select("td");
					String team = tds.get(0).select("div.cb-col-84").text();
					points.add(team);
					tds.forEach(td -> {
						if (!td.hasClass("cb-srs-pnts-name")) {
							points.add(td.text());
						}
					});
					// System.out.println(points);
					pointTable.add(points);
				}

			});

			System.out.println(pointTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pointTable;
	}
}


