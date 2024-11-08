# pollitos-league-manager 
This repo will be used to learn how to create controllers an other type of beans using a sport league scenario.

Scenario
There will be 2 leagues, both of them will be of the same type, one named SoccerLeague and the other BaseBallLeague

In each league there will be teams, players and matches.
For the team we need one ID, name and players.
For the players we need an ID, name.
For the matches, it will have both teams and the score of each one

We would need two have 2 profiles: The default one that will be empty and the populated,
the populated will have at least 2 teams each one with 2 players already created in each league.

There will be a limit of how many teams will be in a league, by the default it will be 10 but you can control that with a property

We need to create the following endpoints (in each request you should indicate what is the league you want to modify):
* Create team
* Create player indicating the team it is part of
* Create a match
* Get all the teams
* Get all the players from a team
* Get all the matches from a team
* Edit the player information
* Edit the team information
* Delete all the matches
* Delete all the players of a team

Criteria to evaluate:
* Creation of beans
* Usage of the annotation in the Controller layer
* Usage of Spring and Spring boot annotation
* Implementation of logs

Extra points:
* Creation of unit test or integration
* Correct usage of interfaces and abstract classes
* Proper instructions in the PR (description, title, steps to test)