Feature: Registration in transaction management system
  Customer should be able to register in Bank Account when all required fields are filled properly
  
  Scenario Outline: Customer registration with valid details
  Given Customer provides <detail> details
  When Customer submit the details
  Then Registration <status>
  
  Examples:
   |detail  | status     |
   |new     | successful |
   |existing| failure    |
   
    Scenario Outline: Customer provide invalid details
  Given Customer provide invalid <field>
  When Customer submit the details
  Then Error message with bad request shown
  
  Examples:
   |field   |
   |password|    
   |pan     |
   |email   |