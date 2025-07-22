package com.CrickInFormer.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CrickInFormer.entities.Match;
@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
	
	//match fetch krna chahta hoon-->
	//provide kar de-->teamheading
	
	Optional<Match>findByTeamHeading(String teamHeading);
	//List<Match> findByTeamHeading(String teamHeading);


}
