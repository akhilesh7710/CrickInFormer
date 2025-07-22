package com.CrickInFormer.service;

import java.util.List;
import java.util.Map;

import com.CrickInFormer.entities.Match;

public interface MatchService {
	
	//get all matches
	List<Match>getAllMatches();
	
	//get live matches
	List<Match> getLivMatches();
	
	//get cwc2025 point table
	List<List<String>>getPointTable();
	

}
