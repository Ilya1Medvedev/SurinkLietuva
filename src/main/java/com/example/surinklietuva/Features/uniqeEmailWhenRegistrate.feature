# language: lt
Savybė: Naujai paskyrai reikalingas unikalus elektroninis paštas (Backlog item "DIN-13")

  Scenarijus:
    Duota Vartotojas bando užsiregistruoti su elektroniniu paštu "garislav@gmail.com"
    Kai Duomenų bazėje nėra vartotojo su elektroniniu "garislav@gmail.com"
    Tada Vartotojas sėkmingai registruoja paskyrą

  Scenarijus:
    Duota Vartotojas bando užsiregistruoti su elektroniniu paštu "garislav@gmail.com"
    Kai Duomenų bazėje jau yra vartotojas su elektroniniu "garislav@gmail.com"
    Tada Vartotojas gauna pranešimą, kad toks elektroninis paštas jau užimtas