Feature: 
  In order to use the system
  As a registered user
  I want to be able to Login
  

  Scenario: scenario one
    Given Open Page "https://test.animana.com/web2/login"
    And Login with username="testnl40" and password="Test#12345"
    When location="Hilversum" is selected
    Then new contact icon is present on the page
    When selecting patient category from drop down box and searching for "Diensthond"
    Then search should displays only one result

  Scenario: scenario two
    Given Open Page "https://test.animana.com/web2/login"
    And Login with username="testnl40" and password="Test#12345"
    When location="Hilversum" is selected
    Then new contact icon is present on the page
    When selecting patient category from drop down box and searching for "Diensthond"
    Then search should displays only one result

  Scenario: negative scenario1
    Given Open Page "https://test.animana.com/web2/login"
    And Login with username="testnl40" and password="Test#12345"
    When location="Hilversum" is selected
    Then new contact icon is present on the page
    When selecting patient category from drop down box and searching for "Diensthond"
    Then search should displays only one result
