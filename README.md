# Projekt Lytehouse
### Umsetzung des MVC Patterns:
Unsere MVC Komponenten sind in 4 packages aufgeteilt. In den
Package model befindet sich zu jeder Spielkomponente ein Model
(z.B. `BBall`). Damit ist es uns Möglich verschiedene Objekte, welche
alle nötigen Informationen speichern, zu erstellen. Alle ModelKlassen
erben von `BObject`, da jede Spielkompontente Eigenschaften wie X-Y-Koordinaten hat.
Die Klasse `MainModel` enthält Listen mit allen Objekten die in der View gerendert werden sollen.
Über diese Klasse kann also auf die Informationen von allen Objekten zugegriffen werden. 

## Wichtige Zahlen:
  Dimension intern: 560 x 840
  Dimension Lighthouse: 28 x 14
  Translation: 1lx = 20cx , 1ly = 60cy
  
  Paddle: (8lx,1ly) = (160cx, 60cy)
  Block : (4lx,1ly) = (80cx, 60cy)
  Ball : (2lx, 1ly) = (40cx, 60cy)