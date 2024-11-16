// Дадена е низа од научни списанија, при што за секое списание се знае местото кое го зафаќа на полица по широчина и височина. 
// Дополнително, се знае должината на полиците на кои треба да се сместат списанијата. Кога со списанија ќе пополни едно ниво од полиците, се минува на следното ниво полици, 
// при што висината на една полица зависи од највисокото списание поставено на полицата. Да се одреди минималната можна височина на полиците, 
// откако на неа ќе бидат сместени сите списанија од низата

package labVezbi;

class Spisanie {
    int visina;
    int sirina;

    Spisanie(int visina, int sirina) {
        this.visina = visina;
        this.sirina = sirina;
    }
}

public class zad9 {
    public static void main(String[] args) {

        Spisanie[] spisanija = {
            new Spisanie(7, 5),
            new Spisanie(4, 3),
            new Spisanie(2, 7),
            new Spisanie(8, 5),
            new Spisanie(6, 4)
        };

        int shelfWidth = 10;

        int minHeight = minHeight(spisanija, shelfWidth);
        System.out.println("Minimalna visina na policite e: " + minHeight);
    }
        
    public static int minHeight(Spisanie[] spisanija, int shelfWidth) {

        int currWidth = 0;       
        int currHeight = 0;      
        int maxHeight = 0;        

        for (int i = 0; i < spisanija.length; i++) {
            Spisanie journal = spisanija[i];
            
            
            if (currWidth + journal.sirina <= shelfWidth) {

                currWidth += journal.sirina;
                currHeight = Math.max(currHeight, journal.visina);

            } else {
            
                maxHeight += currHeight;
        
                currWidth = journal.sirina;
                currHeight = journal.visina;
            }
        }
        
        maxHeight += currHeight;

        return maxHeight;
    }
    
}
