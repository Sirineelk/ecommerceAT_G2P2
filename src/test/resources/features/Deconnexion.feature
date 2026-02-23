Feature: Deconnexion

@Deconnexion
Scenario: Deconnexion
  Given je suis sur la page My Account
  When Je saisis un login "teststartestadmin@gmail.com"
  And Je saisis mon mot de passe "Adminadmin123."
  And Je clique sur le bouton login
  When je clique sur le lien Logout
  Then je suis déconnecté et la page my-account affiche les pavés Login et Register

