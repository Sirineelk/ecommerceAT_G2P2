Feature: consulter le panier

@RecapitulatifPanier
Scenario: RG1 - Vérifier le récapitulatif du panier
Given je suis sur la page de l'article "Android Quick Start Guide"
  Then je vérifie la présence de la photographie
  And j'indique le nombre d'exemplaire voulu "5"
  When je clique sur le bouton d'ajout au panier
  And je clique sur le bouton View Basket
  Then je suis redirigé vers la page du panier
  And je vérifie le récapitulatif du panier pour "Android Quick Start Guide" avec quantité "5", prix unitaire "450.00" et total "2250.00"


  @ModificationArticle
  Scenario: RG2 - Modifier la quantité d’un article
    Given je suis sur la page de l'article "Android Quick Start Guide"
    Then je vérifie la présence de la photographie
    And j'indique le nombre d'exemplaire voulu "5"
    When je clique sur le bouton d'ajout au panier
    And je clique sur le bouton View Basket
    Then je suis redirigé vers la page du panier
    Given je suis sur la page panier avec "5" exemplaires de "Android Quick Start Guide"
    When je modifie la quantité à "3"
    And je clique sur le bouton Update Basket
    Then la quantité affichée devient "3"


    @SuppressionArticle
    Scenario: RG2 - Supprimer un article du panier
      Given je suis sur la page de l'article "Android Quick Start Guide"
      Then je vérifie la présence de la photographie
      And j'indique le nombre d'exemplaire voulu "1"
      When je clique sur le bouton d'ajout au panier
      And je clique sur le bouton View Basket
      Then je suis redirigé vers la page du panier
      Given je suis sur la page panier avec un article
      When je clique sur le bouton supprimer l'article
      Then un message de suppression est affiché

  @RecapitulatifPanierBasketTotals
  Scenario: RG3 - Vérifier le récapitulatif Basket Totals
    Given je suis sur la page de l'article "Android Quick Start Guide"
    Then je vérifie la présence de la photographie
    And j'indique le nombre d'exemplaire voulu "5"
    When je clique sur le bouton d'ajout au panier
    And je clique sur le bouton View Basket
    Then je suis redirigé vers la page du panier
    Then je vérifie le récapitulatif Basket Totals avec subtotal "2250.00", taxe "45.00" et total "2295.00"

  @ProceedToCheckout
  Scenario: RG4 - Passer au paiement
    Given je suis sur la page de l'article "Android Quick Start Guide"
    Then je vérifie la présence de la photographie
    And j'indique le nombre d'exemplaire voulu "5"
    When je clique sur le bouton d'ajout au panier
    And je clique sur le bouton View Basket
    Then je suis redirigé vers la page du panier
    When je clique sur le bouton Proceed to Checkout
    Then je suis redirigé vers la page de checkout