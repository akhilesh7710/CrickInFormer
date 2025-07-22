package com.CrickInFormer.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CrickInFormer.entities.Match;
import com.CrickInFormer.service.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {
	@Autowired
	private MatchService matchService;
	
	
	//get live matches
	@GetMapping("/live")
	public ResponseEntity<List<Match>>getLiveMathches(){
		return new ResponseEntity<>(matchService.getLivMatches(),HttpStatus.OK);	
	}
	
	//get all matches
	@GetMapping
	public ResponseEntity<List<Match>>getAllMatches(){
		return new ResponseEntity<>(matchService.getAllMatches(),HttpStatus.OK);
	}
	
	//gell point table
	
	@GetMapping("/Point-table")
	public ResponseEntity<?>getPointTable(){
		return new ResponseEntity<>(matchService.getPointTable(),HttpStatus.OK);
	}
	

}
