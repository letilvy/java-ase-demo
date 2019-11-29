# Exercise: Decoupling (Java)

### Scenario

- We have a Bets class which represents the bets on a poker table
- When I want to place a bet, the "bet" method is called with my bet amount
- We can ask the "Bets" class if all bets are even, i.e. if every player has bet the same amount
  - This information can e.g. be used to decide whether the players now have to show their cards (showdown)
- There is a table limit that I am not allowed to exceed for my bet, i.e. I am not allowed to bet more than 100 USD

### Your Task

- Execute the tests - some of them will fail - find the root cause
- Fix the root cause by applying the dependency inversion principle
- Discuss alternative variants

### Hints

- Apparently the Bets, within its "bet" method, does two things - one of it is not really related to the core business logic, and makes it also hard to test at this point
- If you want to try using a mock framework, have a look into Mockito. A dependency has already been added to the pom.xml, so you can use it right away. The documentation can be found here: http://mockito.org/
