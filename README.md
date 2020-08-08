SpringBoot Data Jpa REST application example
=============================================

Technical Stack:
======
Spring Boot 2, Spring Data Jpa, Spring Test, Junit Tests, Jackson, PostgreSQL.

How it works
=============
The application uses Spring boot (MVC, Data Jpa).

* Use the idea of Domain Driven Design to separate the service tier, repository and model tiers.
* Use Spring Data Jpa to implement persistence.

And the code organize as this:
1. REST 'api' is the web layer to implement by Spring MVC
2. 'core' is the business model including entities, repositories and services
3. Code covered with tests
    
Database
======
It uses a PostgreSQL database (for now), can be changed easily in the db.properties for any other database.
