Feature: Requests for projects endpoint
  description

  @CreateProject
  Scenario Outline: Get a Project
    Given I build "POST" request
    And I set project "<name>" name
    And I set project "<description>" description
    And I set project "<project_type>" type
    And I set "<enable_tasks>" enable
    When I execute "/projects" request
    Then the response status code should be "OK"

    Examples:
      | name          | description  | project_type | enable_tasks |
      | testproj1     | testing      | public       | true         |
      | testproj1!#$% | check chars  | private      | false        |
      | test projc?   | check spaces | public       | true         |