# language: lt
Savybė: draugų ekosistema

  Scenarijus: Pridėti draugą
    Duota Vartotojas turi draugą "Andrew Perkins"
    Kai Vartotojas bando pridėti draugą "Andrew Perkins"
    Tada Vartotojas gauna pranešimą "Jūsų draugų sąraše toks žmogus jau egzistuoja.".

  Scenarijus: Pridėti draugą
    Duota Vartotojas turi draugą "Andrew Perkins"
    Ir Vartotojas turi draugą "John Wick"
    Kai Vartotojas bando pridėti draugą "Myles Mapkins"
    Tada Vartotojas sėkmingai pridėjo draugą.

  Scenarijus: Peržiūrėti draugo profilį
    Duota Vartotojas turi draugą "Andrew Perkins"
    Kai Vartotojas spaudžią peržiūrėti draugo "Andrew Perkins" profilio informacją.
    Tada Vartotojui rodoma "Andrew Perkins" informacija.