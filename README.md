# Bowling

## Description

This simple program is calculating points scored in a bowling game based of a list of rounds given from a REST endpoint.
When the gamePoints have been calculated a second REST Post endpoint will be called containing a JSON payload which contains the game results and a token given from when the first REST endpoint. 
The result will be displayed in the browser

## Getting Started

### Dependencies

* java 15 
* grailsVersion=5.0.0.M1
* groovyVersion=3.0.7

### Executing program

To run the program localhost run this command
* ./gradlew bootRun
To run a the unit test run this command
* ./gradlew clean check

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.1
    * Initial Release