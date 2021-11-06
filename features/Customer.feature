#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Customer Feature
  I want to test API Customer

  @post
  Scenario Outline: Validate post a customer success
    When I post a customer
      | first_name   | last_name   | phone   | email   | birthday   | sex   | province_code   | district_code   | country_code   | address   | note   |
      | <first_name> | <last_name> | <phone> | <email> | <birthday> | <sex> | <province_code> | <district_code> | <country_code> | <address> | <note> |
    Then I verify post customer success

    Examples: 
      | first_name | last_name | phone      | email           | birthday   | sex    | province_code | district_code   | country_code | address        | note        |
      | yến        | nguyễn    | 0963555788 | yennth4@sapo.vn | 1635872400 | female | Dak-Lak       | Huyen-Krong-Ana | VN           | địa chỉ cụ thể | KH vui tính |
