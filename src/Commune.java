public class Commune {

    String nom;
    String codePostal;
    String latitude;
    String longitude;
    int variableLinguistique;

    public Commune(String nom, String codePostal, String latitude, String longitude, int variableLinguistique) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.latitude =  latitude;
        this.longitude = longitude;
        this.variableLinguistique = variableLinguistique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getVariableLinguistique() {
        return variableLinguistique;
    }

    public void setVariableLinguistique(int variableLinguistique) {
        this.variableLinguistique = variableLinguistique;
    }
}
