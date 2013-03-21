A0B36PR2
========

Semestrální práce na Programování 2 - Piškvorky

Zadání:
Hra pro dva hráče na jednom počítači, program zobrazí hrací pole s aktuálně obsazenými pozicemi (rozlišitelné podle hráčů), v každém tahu hráč na výzvu zadává pozici, kterou chce obsadit; program kontroluje, zda požadovaná pozice není obsazená a zda nějaký hráč vyhrál, velikost hracího pole se zadává před začátkem hry; možnost uložení a načtení rozehrané hry; hra bude mít gradické uživatelské rozhraní.

Rozvržení programu:

Třídy částečně převzaté ze semestrální práce z 1. semestru, které neobstarávají grafiku:

Třída Hodnota uchovává hodnotu políčka.
Třída Políčko představuje políčko, které uchovává hodnotu z předchozí třídy. Zároveň kontroluje správný zápis, aby nedošlo k přepsání už plného políčka.
Třída Hrací pole obsahuje metodu zápis, která obstarává korektní zadávání  do vymezeného pole. Dále obsahuje metodu jePět, která po každém tahu zkontroluje, zda nějaký hráč nevyhrál.
Další třída se nazývá Hra a obsahuje metodu hraj, která se stará o chod hry. Dále obsahuje metodu uložHru, která hru ukládá. Má dva konstruktory, z nichž jeden vytváří novou hru, podle zadané velikosti a druhý načítá již uloženou hru.

Nové třídy pro grafické zpracování:

MFrame: bude zobrazovat buď komponentu Menu, nebo PlayField
Menu: hlavní nabídka
PlayField: vlastní hra s možností přechodu do hlavní nabídky



