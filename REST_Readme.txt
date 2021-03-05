
The REST API Documentation(basic):
1. Get people
HTTP GET http://localhost:8081/people

2. To get the details about the format of the arguments, one can use the API
HTTP GET http://localhost:8081/profile/people

3. Create/Add a person using:
HTTP POST http://localhost:8081/people
{"firstName": "Boris", "lastName":"Johnson", "age": "60", "favColour":"Blue" }

3. Search for people with given first name.
HTTP GET http://localhost:8081/people/search/findByFirstName?firstName=Boris

4. Search for people with given last name.
HTTP GET http://localhost:8082/people/search/findByLastName?lastName=Johnson

5. Search for people with given age
HTTP GET http://localhost:8082/people/search/findByAge?age=60

6. Update/Replace Person data(Colour change to yellow)
HTTP PUT http://localhost:8081/people/1
{"firstName": "Boris", "lastName":"Johnson", "favColour":"Yellow" }

7. Update specific attributes only of a person ( age=70)
HTTP PATCH http://localhost:8081/people/1
{ "age": "70" }
