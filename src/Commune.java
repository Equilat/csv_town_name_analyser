public class Commune {

    String nom;
    String codePostal;
    String coordonnees;
    int variableLinguistique;

    public Commune(String nom, String codePostal, String coordonnees, int variableLinguistique) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.coordonnees = coordonnees;
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

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public int getVariableLinguistique() {
        return variableLinguistique;
    }

    public void setVariableLinguistique(int variableLinguistique) {
        this.variableLinguistique = variableLinguistique;
    }
}
