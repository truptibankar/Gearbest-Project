@login
Feature: Login functionality

  @tag1
  Scenario: Create new user
  Given User opened browser
  And User navigates to the url
  When User clicked on register button
  And User entered email id "truptibankar12@gmail.com"
  And User entered password "Trupti@95"
  And User entered confirmed password
  And User clicked on register button
  Then Usee new account created
  

