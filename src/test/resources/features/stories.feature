Feature: Requests for stories endpoint
  Want to know if stories work correctly

  @CreateStory
  Scenario Outline: Create Story
    Given I build "POST" request
    And I set story "<name>" name
    And I set story "<description>" description
    And I set story "<story_type>" type
    When I execute "/projects/{project_id}/stories" request
    Then the response status code should be "OK"

    Examples:
      | name        | description        | story_type |  |
      | story1      | 1234567 7890       | feature    |  |
      | story1!#$%  | =-this is a story  | bug        |  |
      | test Story? | story to test "/12 | chore      |  |
      | @story\=-   | story release      | release    |  |