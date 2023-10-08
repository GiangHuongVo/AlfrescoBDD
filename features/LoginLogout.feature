Feature: Login and Logout

  Background: 
    Given Open browser "http://localhost:8088/share/page"

  @tclogin
  Scenario Outline: User logs in
    When Je saisis "<username>" dans le champ Username
    And Je saisis "<password>" dans le champ Password
    And Je clique sur le bouton Connexion
    Then Il affiche Tableau de bord de

    Examples: 
      | username  | password |
      | admin     | 12345678 |
      | giangtest |    12345 |

  @tclogout
  Scenario Outline: User logs out
    Given the user is logged in "<username>" et "<password>"
    When Je clique sur le link de header Deconnexion
    Then Je vois le bouton Connexion

    Examples: 
      | username  | password |
      | admin     | 12345678 |
      | giangtest |    12345 |
