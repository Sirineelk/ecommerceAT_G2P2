Feature: Visualisation des information du compte depuis la page My Account

  @AccountDetails
  Scenario: Accès aux informations du compte
    Given je suis sur la page My Account
    When Je saisis un login "Teststartest@gmail.com"
    And Je saisis mon mot de passe "Adminadmin1234."
    And Je clique sur le bouton login
    Then je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout
    When je clique sur le lien Account Details
    Then je suis sur la page Edit Account
    And je vois les informations de compte : nom, prénom et e-mail

  @changementMotDePasseSucces
  Scenario: Changement de mot de passe depuis Account Details avec succes
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "Adminadmin123."
    And Je clique sur le bouton login
    Then je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout
    When je clique sur le lien Account Details
    Then je suis sur la page Edit Account
    Then je saisis first name "sirine"
    And je saisis last name "elk"
    When je saisis mon mot de passe actuel "Adminadmin123."
    And je saisis un nouveau mot de passe "Lolasirine123.."
    And je confirme le mot de passe "Lolasirine123.."
    Then je clique sur le bouton Save Changes
    And je vois un message Account details changed successfully
    When je clique sur le lien Account Details
    Then je suis sur la page Edit Account
    When je saisis mon mot de passe actuel "Lolasirine123.."
    And je saisis un nouveau mot de passe "Adminadmin123."
    And je confirme le mot de passe "Adminadmin123."
    Then je clique sur le bouton Save Changes
    And je vois un message Account details changed successfully


  @changementMotDePasseFailCurrentPassword
  Scenario: Changement de mot de passe cureent password incorrect
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "Adminadmin123."
    And Je clique sur le bouton login
    Then je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout
    When je clique sur le lien Account Details
    Then je suis sur la page Edit Account
    Then je saisis first name "sirine"
    And je saisis last name "elk"
    When je saisis mon mot de passe actuel "INCORRECTPASSWORD."
    And je saisis un nouveau mot de passe "NouveauMotDePasse123."
    And je confirme le mot de passe "NouveauMotDePasse123."
    Then je clique sur le bouton Save Changes
    And je vois un message derreur indiquant que le mot de passe actuel est incorrect.


  @changementMotDePasseFailPassowrdConfirmation
  Scenario: Changement de mot de passe cureent password incorrect
    Given je suis sur la page My Account
    When Je saisis un login "teststartestadmin@gmail.com"
    And Je saisis mon mot de passe "Adminadmin123."
    And Je clique sur le bouton login
    Then je suis connecté et je vois le lien : DashboardOrders, Download, Adresses, Account Details, Logout
    When je clique sur le lien Account Details
    Then je suis sur la page Edit Account
    Then je saisis first name "sirine"
    And je saisis last name "elk"
    When je saisis mon mot de passe actuel "NouveauMotDePasse123."
    And je saisis un nouveau mot de passe "NouveauMotDePasse123!!"
    And je confirme le mot de passe "mot de passe different du nouveau mot de passe"
    Then je clique sur le bouton Save Changes
    And je vois un message derreur indiquant que les deux mots de passe ne correspondent pas.