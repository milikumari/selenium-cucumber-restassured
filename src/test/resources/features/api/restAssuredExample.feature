@api @cucumberTest
Feature: validate Rest assured Api calls

  @GET
  Scenario: Verify GET api call
    Given I perform GET operation for "api/users?page=2"
    Then Response should be "success"
    And total number of pages should be "2"

  @POST
  Scenario: Verify POST api call
    Given I Perform POST operation for "api/users" with body
      | name | job    |
      | Sams | Tester |
    Then Response should be "created"
    Then I should see the response body has name as "Sams"

  @PUT
  Scenario: Verify PUT api call
    Given I Perform PUT operation for "api/users/2" with body
      | name | job    |
      | Sams | Tester |
    Then Response should be "success"

  @DELETE
  Scenario: Verify DELETE api call
    Given I Perform DELETE operation for "api/users/2"
    Then Response should be "deleted"