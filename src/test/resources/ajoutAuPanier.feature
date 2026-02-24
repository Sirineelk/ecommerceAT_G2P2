Feature: Ajout des articles dans le panier depuis la page "Shop"

  @AjoutAuPanier
  Scenario: Accès au panier après un ajout
    Given je suis sur la page "Shop"
    And j'ai ajouté un article au panier
    When je clique sur le lien "VIEW BASKET" qui apparaît sous l'article
    Then je suis redirigé vers la page "Basket"

  @FiltreParTheme
  Scenario: Filtre des articles par thème
    Given je suis sur la page "Shop"
    When je filtre les articles par theme
    Then la vue affiche les articles du thème choisi

  @FiltreParPrix
  Scenario: Filtre sur le prix des articles
    Given je suis sur la page "Shop"
    When je filtre les articles par prix
    Then la vue affiche les articles dans l'ordre correspondant

  @RedirectionAuPanier
  Scenario: Vérification de la redirection vers le panier sur toutes les pages
    Given je suis sur la page "Shop"
    When je clique sur l'icône du panier située en entête
    Then je suis redirigé vers la page "Basket"


  @VerificationElementsArticle
  Scenario: Vérification des éléments de l'article et redirection vers les détails
    Given je suis sur la page "Shop"
    Then une photo descriptive, un libellé et le prix affiché au-dessus
    And un lien actif sur un de ces element redirige vers le détail de l'article

  @ArticleHorsStock
  Scenario: Affichage article hors stock
    Given je suis sur la page "Shop"
    And l'article sélectionné n'est plus en stock
    Then le bouton "READ MORE" apparaît