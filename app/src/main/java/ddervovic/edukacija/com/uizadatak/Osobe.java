package ddervovic.edukacija.com.uizadatak;

/**
 * Created by Denis Dervovic on 19.6.2015..
 */
public class Osobe {
    private String ime;
    private String sms;
    private String broj;
    private int slika;

    public Osobe(String ime, String sms, String broj, int slika) {
        this.ime = ime;
        this.sms = sms;
        this.broj = broj;
        this.slika = slika;
    }

    public String getIme() {
        return ime;
    }


    public String getSms() {
        return sms;
    }


    public String getBroj() {
        return broj;
    }


    public int getSlika() {
        return slika;
    }





}
