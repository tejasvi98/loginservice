Feature: Login to Transaction management system
user can login the system using the credential


Scenario: User login with correct crediantial
  Given user enters the valid credential
  When performing login
  Then login is successful
  
  
  Scenario: login failure with bad crediantial
  Given user enters the invalid credential
  When performing login
  Then error message displayed
  
  Scenario: logged in user trying to validate itself
  Given user provides token
  When performing validity
  Then validity status is shown