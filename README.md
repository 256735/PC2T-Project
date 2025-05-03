# PC2T-Project 2025

---

## Funkce aplikace

- Přidání nového studenta (TLI/KB)
- Automatické přiřazení ID studentovi
- Zadávání známek s kontrolou platnosti (1–5)
- Výpočet průměrné známky studenta
- Výpis informací o studentovi podle ID
- Seřazený výpis všech studentů podle příjmení, zvlášť pro každý obor
- Výpis počtu studentů v jednotlivých skupinách
- Výpočet průměrných známek pro obory TLI a KB
- Dovednosti dle oboru:
  - TLI – převod jména a příjmení do Morseovy abecedy
  - KB – výpis SHA-256 hashe jména a příjmení
- Mazání studentů dle ID (včetně odstranění z databáze)
- Ukládání a načítání dat z TXT souboru
- Ukládání a načítání dat ze SQLite databáze
  - Automaticky při spuštění a ukončení programu
- Ošetření všech vstupních chyb:
  - ID a rok narození musí být celé číslo
  - Známka musí být v rozsahu 1 až 5
  - Jméno a příjmení pouze písmena, první písmeno velké
  - Obor musí být platný (TLI / KB)

---

## Struktura projektu

- `Main.java` – hlavní třída s menu
- `Student.java` – abstraktní třída pro studenta
- `Student_TLI.java`, `Student_KB.java` – oborově specifické třídy
- `MyDatabase.java` – logika databáze v paměti
- `DBFile.java` – ukládání/načítání TXT
- `DBSql.java` – ukládání/načítání SQLite
- `StudentComparator.java` – porovnávání studentů podle příjmení a jména

---

## Databáze

Aplikace využívá databázi SQLite. Soubor `students.db` se automaticky vytvoří v adresáři projektu. Při spuštění se načítají data, při ukončení se vše automaticky ukládá.

---

## Spuštění aplikace

1. Otevřete projekt v Eclipse
2. Přidejte SQLite JDBC knihovnu jako externí JAR (nebo použijte Maven)
3. Spusťte `Main.java`
4. Ovládání probíhá v textové konzoli pomocí menu

---

## Budoucí kroky pro vylepšení

- Drobné zpřesnění formátování vstupů
- Zpřehlednění ovládání a výstupů v terminálu
- Přidání kontroly duplicitních ID při načítání ze souboru
- Možnost exportu dat do CSV

---

## Autoři

- David Kaláb + Dominik Hejda
