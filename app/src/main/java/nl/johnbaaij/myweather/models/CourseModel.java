package nl.johnbaaij.myweather.models;

/**
 * Created by John on 16/06/2017.
 */

public class CourseModel {

    private String vakCode;
    private int aantalECTS;
    private int cijfer;
    private int periode;

    public CourseModel(String vakCode, int aantalECTS, int cijfer, int periode) {
        this.vakCode = vakCode;
        this.aantalECTS = aantalECTS;
        this.cijfer = cijfer;
        this.periode = periode;
    }

    public String getVakCode() {
        return vakCode;
    }

    public String getAantalECTS() {
        return String.valueOf(aantalECTS);
    }
}
// ADD GETTERS AND SETTERS - ONLY IF NEEDED !! }