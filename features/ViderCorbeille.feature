Feature: Vider la corbeille

  Background: 
   Given Open browser "http://localhost:8088/share/page"
   
   Scenario: User vide la corbeille   	
   	When Je suis dans la page Corbeille
    And Je clique sur le bouton Vider
    And Je clique sur le bouton OK pour confirmer
    Then Je vois la liste est vide
    