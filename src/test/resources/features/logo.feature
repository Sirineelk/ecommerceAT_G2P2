@us3
Feature: Vérification du logo du site "AT"

  En tant qu'Utilisateur
  Je souhaite voir le logo du site "AT"
  Afin de naviguer vers la page d'accueil facilement et confirmer sa présence sur toutes les pages


  @rg1
  Scenario Outline: Le logo redirige vers la page d'accueil
    Given je suis sur la page "<page>"
    When je clique sur le logo
    Then je suis redirigé vers la page d'accueil
    Examples:
      | page               |
      | accueil            |
      | My Account         |
      | Produits           |


  @rg2
  Scenario Outline: Le logo est présent sur toutes les pages
    Given je suis sur la page "<page>"
    Then le logo doit être visible

    Examples:
      | page               |
      | accueil            |
      | My Account         |
      | Produits           |