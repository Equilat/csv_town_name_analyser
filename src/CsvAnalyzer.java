import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvAnalyzer {


    Path currentRelativePath = Paths.get("");
    String fichierCsvEntree = currentRelativePath.toAbsolutePath().toString() + "/data/communes_departements_nettoyes.csv ";
    String fichierCsvSortie = currentRelativePath.toAbsolutePath().toString() + "/data/output/donnees_linguistiques_generees.csv";
    BufferedReader br = null;
    String ligne = "";
    String separateur = ",";
    ArrayList<Commune> communes = new ArrayList<>();
    String[] suffixesOc = {"ac", "acq", "at"};
    String[] suffixesOil = {"ay", "é", "ey", "y", "ecies", "eries", "iers", "ez"};
    double nombreCommunes = 0;
    double compteurOC = 0;
    double compteurOil = 0;
    double compteurInconnu = 0;
    double pourcentageOc = 0;
    double pourcentageOil = 0;

    /**
     * Constructeur
     */
    public CsvAnalyzer() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        readFile();
    }

    public void readFile() {
        try {

            br = new BufferedReader(new FileReader(fichierCsvEntree));
            ligne = br.readLine();
            while ((ligne = br.readLine()) != null) {

                // use comma as separator
                String[] ville = ligne.split(separateur);
                int variableLinguistique = this.analyserNom(ville[5]);
                Commune commune = new Commune(ville[5], ville[1], ville[2], ville[3], variableLinguistique);
                communes.add(commune);
            }
            nombreCommunes = communes.size();
            compteurInconnu = nombreCommunes - (compteurOC + compteurOil);
            pourcentageOc = Math.round(((compteurOC / nombreCommunes) * 100.0)*((10^2)*1.0))/((10^2)*1.0);
            pourcentageOil = Math.round(((compteurOil / nombreCommunes) * 100.0)*((10^2)*1.0))/((10^2)*1.0);
            generateCsv();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Analyse le nom de la commune sur la base des deux listes de suffixes (oc et oïl) et retourne sa variable linguistique
     * @param nomCommune nom de la commune
     * @return la variable linguistique (-1 si oïl, 0 si inconnu, 1 si oc)
     */
    public int analyserNom(String nomCommune) {
        int i = 0;
        int ret = 0; //-1 Oïl, 0 Unknown, 1 Oc
        boolean found = false;

        while (i < suffixesOc.length && !found) {
            if (nomCommune.toLowerCase().endsWith(suffixesOc[i])) {
                ret = 1;
                compteurOC++;
                found = true;
            }
            i++;
        }
        i = 0;
        while (i < suffixesOil.length && !found) {
            if (nomCommune.toLowerCase().endsWith(suffixesOil[i])) {
                ret = -1;
                compteurOil++;
                found = true;
            }
            i++;
        }

        return ret;
    }

    /**
     * Génère le fichier CSV et retourne des données statistiques sur le pourcentage de communes par racine de nom (% oc, % oïl, % inconnu)
     */
    public void generateCsv() {
        File csvFile = new File(fichierCsvSortie);
        try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFile));){
            csvWriter.println("nom, code_postal, latitude, longitude, variable_linguistique");
            for(Commune item : communes){
                csvWriter.println(item.getNom()+","+item.getCodePostal()+","+item.getLatitude()+","+item.getLongitude()+","+item.getVariableLinguistique());
            }
            System.out.println("Nombre de communes : " + nombreCommunes);
            System.out.println("Nombre de communes oc : " + compteurOC + " soit " + pourcentageOc + "% des communes");
            System.out.println("Nombre de communes oil : " + compteurOil + " soit " + pourcentageOil + "% des communes");
            System.out.println("Nombre de communes inconnues : "+ compteurInconnu);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
