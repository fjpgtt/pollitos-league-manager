# pollitos-league-manager 
This repo will be used to learn how to create controllers an other type of beans using a sport league scenario.

Scenario
There will be 2 leagues, both of them will be of the same type, one named SoccerLeague and the other BaseBallLeague

In each league there will be teamDTO, playerDTO and matches.
For the teamDTO we need one ID, name and playerDTO.
For the playerDTO we need an ID, name.
For the matches, it will have both teamDTO and the score of each one

We would need two have 2 profiles: The default one that will be empty and the populated,
the populated will have at least 2 teamDTO each one with 2 playerDTO already created in each league.

There will be a limit of how many teamDTO will be in a league, by the default it will be 10 but you can control that with a property

We need to create the following endpoints (in each request you should indicate what is the league you want to modify):
* Create teamDTO
* Create playerDTO indicating the teamDTO it is part of
* Create a matchDTO
* Get all the teamDTO
* Get all the playerDTO from a teamDTO
* Get all the matches from a teamDTO
* Edit the playerDTO information
* Edit the teamDTO information
* Delete all the matches
* Delete all the playerDTO of a teamDTO

Criteria to evaluate:
* Creation of beans
* Usage of the annotation in the Controller layer
* Usage of Spring and Spring boot annotation
* Implementation of logs

Extra points:
* Creation of unit test or integration
* Correct usage of interfaces and abstract classes
* Proper instructions in the PR (description, title, steps to test)