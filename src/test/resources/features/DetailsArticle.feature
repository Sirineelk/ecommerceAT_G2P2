Feature: Details d'un article

  @DetailsArticle
Scenario: Vérifier la présentation complète d’un article
Given je suis sur l'article "Android Quick Start Guide"
Then je vérifie la présence de la photographie
And je vérifie la présence du descriptif
And je vérifie la présence du prix
And je vérifie la présence du nombre d'exemplaires


  @AjoutPanier
  Scenario: RG2 et RG3 - Ajouter un article au panier et vérifier le panier
    Given je suis sur la page de l'article "Android Quick Start Guide"
    Then je vérifie la présence de la photographie
    And j'indique le nombre d'exemplaire voulu "4"
    When je clique sur le bouton d'ajout au panier
    Then le message d'ajout au panier s'affiche avec "4" et "Android Quick Start Guide"
    And je clique sur le bouton View Basket
    Then je suis redirigé vers la page du panier
    And je vérifie que le panier contient "4" exemplaires de "Android Quick Start Guide"