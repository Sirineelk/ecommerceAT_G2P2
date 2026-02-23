Feature: Shop Page

@R1
  Scenario: Vérifier que la page Shop contient la liste des articles en vente
    Given Je suis sur la page Shop
    Then La liste des produits est affichée

  @R2
  Scenario: Vérifier que chaque article possède le bon bouton selon son stock
    Given Je suis sur la page Shop
    When je vois l'article "Android Quick Start Guide"
    Then je vois le bouton Add to Basket pour cet article
    When je vois l'article "Android Quick Start Guide"
    And je clique sur le bouton Add to Basket "Android Quick Start Guide"
    And je regarde le nombre d'articles dans le stock
    Then je vois le bouton Read more pour cet article


    @R3
    Scenario Outline: Vérifier que l'icone du panier redirige vers la page du panier depuis toutes les pages
      Given Je suis sur la page "<page>"
      When je clique sur l'icone du panier
      Then je suis redirigé vers la page du panier
      Examples:
        | page               |
        | accueil            |
        | test-cases         |
        | my-account         |
        | shop               |

  @R4
  Scenario: Filtrer les articles par prix
    Given Je suis sur la page Shop
    When Je sélectionne une plage de prix
    And Je clique sur le bouton Filter
    Then Les articles affichés correspondent à la plage de prix sélectionnée

@R5
  Scenario: Filtrer les articles par thème
    Given Je suis sur la page Shop
    When Je sélectionne un thème
    Then Les articles affichés appartiennent au thème sélectionné

@R6
  Scenario: Vérifier qu’un article contient une image, un libellé et un prix cliquables
    Given Je suis sur la page Shop
    When je vois l'article "Android Quick Start Guide"
    Then L'article contient une image descriptive
    And L'article contient un libellé
    And L'article contient un prix affiché
    And L'image est cliquable
    And Le libellé est cliquable
    And Le prix est cliquable
    And Je suis redirigé vers la page de détail de l'article


