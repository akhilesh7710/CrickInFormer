# CrickInFormer
 Overview:
 A simple REST API built with Spring Boot that scrapes live cricket match data and point tables from Cricbuzz using JSoup. Designed for learning and practical use with clean layered architecture and RESTful principles.

 Features:
 Live Match Data: Scrape and return real-time match info (teams, scores, venue, status).
 
All Matches: Get a list of all current matches from Cricbuzz.

Point Table: Scrape the tournament point table with team standings.

Auto Data Sync: Scraped data is saved/updated in the database using Spring Data JPA.

Clean REST APIs: All features are exposed via simple REST endpoints.

Layered Architecture: Follows standard Controller → Service → Repository structure.

Technologies Used:
Java 17

Spring Boot

Spring Data JPA

JSoup (Web Scraping)

postgresql

Postman (API Testing)



