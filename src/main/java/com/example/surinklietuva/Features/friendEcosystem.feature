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

  Scenarijus: Pridėti draugą
    Duota Vartotojas turi draugą "Andrew Perkins"
    Kai Vartotojas bando pridėti draugą nepasirinkus norimo vartotojo iš sąrašo
    Tada Vartotojas gauna pranešimą "Jūsų pasirinkimas yra tuščias.".

  Scenarijus: Pridėti draugą
    Duota Vartotojas turi draugą "Andrew Perkins"
    Kai Vartotojas bando pridėti draugą iš tuščio sąrašo
    Tada Vartotojas gauna pranešimą "Jūsų pasirinkimas yra tuščias.".