@example_scenarios
Feature: Letu.ru - scenarios

  @test
  Scenario: Test search field by one item
    Given Open home page
    And Open brands page
    And Select "Л'Этуаль" from search drop list
   	Then Verify search results as expected
   	
  @wip
  Scenario: Test search field by all items
    Given Go to home page
    And Open brands page
    And Select each item from search field and verify it's page opened