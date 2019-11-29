# Exercise: Decoupling (Java)

### Scenario

- We have a leaderboard that shows the rank of a player
- When I won a round of poker, the leader board gets updates if this is new personal high score
- I can query for my position in the leader board 

### Your Task

- Execute the tests - some of them will fail - find the root cause
- Fix the root cause by applying the dependency inversion principle
- Discuss alternative variants

### Hints

- Apparently the leaderboard, within its "update" method, does two things - one of it is not really related to the core business logic, and makes it also hard to test at this point
- If you want to try using a mock framework, have a look into Mockito. A dependency has already been added to the pom.xml, so you can use it right away. The documentation can be found here: http://mockito.org/


