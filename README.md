# Demo: Refactoring

- Refactor a partially implement of String calculator

### Requirment: String calculator
See descript at: http://osherove.com/tdd-kata-1/

- [x] Take up to two numbers, separated by commas, and will return their sum. For example “” or “1” or “1,2”
- [x] Allow the Add method to handle an unknown amount of numbers
- [x] Allow the Add method to handle new lines between numbers. For example “1\n2,3” (will equal 6)
- [x] Support different delimiters. For example “//;\n1;2” return 3
- [ ] Calling Add with a negative number will throw an exception “negatives not allowed”
- [ ] Numbers bigger than 1000 should be ignored
- [ ] Delimiters can be of any length. For example “//###\n1###2###3” return 6
- [ ] Allow multiple delimiters
