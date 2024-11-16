// Да се креира хиерархија на класи за работа со аптеки. За секоја аптека се чува податок за бројот лекови кои моментално 
// се во аптеката и низа од динамички алоцирани лекови (секој лек е string во кој се наоѓа името на лекот). 
// Основната моментална заработувачка на аптеката е по 300 за секој лек. Аптеките може да бидат градски или клинички.
// За градските аптеки дополнително се чува и податок дали работи 24 часа. Моменталната заработувачка на градските аптеки 
// се добива од основната моментална заработувачка која, ако аптеката работи 24 часа, се зголемува за 30%.
// За клиничките аптеки дополнително се чува и податок за тоа колку клиники опслужува. Моменталната заработувачка на клиничките аптеки се добива од 
// основната моментална заработувачка која се зголемува за 20% за секоја клиника која ја опслужува.
// За сите класи дополнително да се напишат констуктор со параметри за класата, функција која ја пресметува заработката на соодветната аптека и 
// функција за печатење на сите информации од соодветната класа.

package labVezbi;

class Apteka {
    protected int brLekovi;
    protected String [] lekovi;

    public Apteka(int brLekovi, String [] lekovi) {
        this.brLekovi = brLekovi;
        this.lekovi = lekovi;
    }

    public double presmetajZarabotka() {
        return brLekovi * 300;
    }

    public void printInfo() {

        System.out.println("Broj na lekovi: " + brLekovi);
        System.out.println("Lekovi: ");

        for(int i = 0; i < brLekovi; i++) {
            System.out.print(lekovi[i] + " ");
        }
        System.out.println();

        System.out.println("Zarabotka: " + presmetajZarabotka());
    }

}

class GradskaApteka extends Apteka {
    private boolean raboti;

    public GradskaApteka(int brLekovi, String [] lekovi, boolean raboti) {
        super(brLekovi, lekovi);
        this.raboti = raboti;
    }

    public double presmetajZarabotka() {

        if(raboti){
            return super.presmetajZarabotka() + super.presmetajZarabotka() * 0.3;
        }
        return super.presmetajZarabotka();
    }

    public void printInfo() {

        super.printInfo();

        System.out.println((raboti ? "Da" : "Ne") + " raboti 24 chasa");
    }

}

class KlinickaApteka extends Apteka {
    private int brKliniki;

    public KlinickaApteka (int brLekovi, String [] lekovi, int brKliniki) {
        super(brLekovi, lekovi);
        this.brKliniki = brKliniki;
    }

    public double presmetajZarabotka() {

        double osnovnaZarabotka = super.presmetajZarabotka();
        double momentalnaZarabotka = osnovnaZarabotka;

        if (brKliniki == 0){
            return momentalnaZarabotka;
        }

        for(int i = 0; i < brKliniki; i++) {
            momentalnaZarabotka +=osnovnaZarabotka * 0.2;
        }

        return momentalnaZarabotka;
        
    }

    public void printInfo() {

        super.printInfo();

        System.out.println("Broj na kliniki: " + brKliniki);
    }


}

public class zad1 {
    public static void main(String[] args) {
        String[] lekovi1 = {"Paracetamol", "Aspirin", "Amoksicilin"};
        String[] lekovi2 = {"Ibuprofen", "Ceftriakson", "Metronidazol"};

        GradskaApteka gradskaApteka = new GradskaApteka(3, lekovi1, true);
        KlinickaApteka klinickaApteka = new KlinickaApteka(3, lekovi2, 10);

        System.out.println("Gradska apteka:");
        gradskaApteka.printInfo();

        System.out.println("\nKlinichka apteka:");
        klinickaApteka.printInfo();
    }
}
