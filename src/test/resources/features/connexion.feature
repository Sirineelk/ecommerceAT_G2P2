Feature: Connexion à l’espace My Account

  @ConnexionReussie
  Scenario: Connexion réussie avec login et mot de passe valides
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "Adminadmin123."
    And Je clique sur le bouton login
    Then je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout

@ConnexionMdpEchoue
Scenario: Connexion échouée avec un mot de passe incorrect
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "WrongPassword."
    And Je clique sur le bouton login
    Then Je ne suis pas connecté et je vois un message d'erreur indiquant que les identifiants sont incorrects


  @ConnexionLoginEchoue
  Scenario: Connexion echouée avec un login incorrect
    Given je suis sur la page My Account
    When Je saisis un login "vcxvvx"
    And Je saisis mon mot de passe "Adminadmin123."
    And Je clique sur le bouton login
    Then Je ne suis pas connecté et je vois un message d'erreur indiquant que "wrong@example.com" est incorrect

  @ConnexionEchoueChampsVides
  Scenario: Connexion echouée champs vides
    Given je suis sur la page My Account
    When Je laisse le login et le mot de passe vides
    And Je clique sur le bouton login
    Then Je ne suis pas connecté et je vois des messages indiquant que les champs sont obligatoires


  @RememberMe
  Scenario: Remember Me
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "Adminadmin123."
    And je coche Remember ME
    And Je clique sur le bouton login
    And je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout
    When je clique sur le lien Logout
    Then je suis déconnecté et la page my-account affiche les pavés Login et Register
    And je vois mes identifiants préremplis dans les champs de connexion