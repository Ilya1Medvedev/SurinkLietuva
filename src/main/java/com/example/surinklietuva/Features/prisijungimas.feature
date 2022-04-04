# language: lt
Savybė: Prisijungimas(DIN-21,DIN-22)

  Scenarijus: Prisijungimas galimas su tinkamais duomenimis
    Duota Vartotojas prisijungęs su paštu "test@gmail.com" ir slaptažodžiu "Test123!"
    Kai Vartotojas bando prisijungti su paštu "test@gmail.com" ir slaptažodžiu "Test123!"
    Tada Vartotojas prisijungęs

  Scenarijus: Prisijungimas galimas su netinkamais duomenimis
    Duota Vartotojas prisijungęs su paštu "test.gmail.com" ir slaptažodžiu "test123"
    Kai Vartotojas bando prisijungti su paštu "test.gmail.com" ir slaptažodžiu "test123"
    Tada Vartotojas gauna klaidos pranešimą

