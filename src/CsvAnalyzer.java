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
    String[] adjectifs = {"ville", "villers", "villiers", "franche", "blanc", "bonne", "fleury"};
    String[] suffixesOc = {"ac"};
    String[] suffixesOil = {};

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

    public int analyserNom(String nomCommune) {
        int i = 0;
        int ret = 0; //-1 Oïl, 0 Unknown, 1 Oc
        boolean found = false;
        while (i < adjectifs.length && !found) {
            if (nomCommune.toLowerCase().startsWith(adjectifs[i])) {
                ret = 1;
                found = true;
            } else if (nomCommune.toLowerCase().endsWith(adjectifs[i])) {
                ret = -1;
                found = true;
            }
            i++;
        }
        i = 0;
        while (i < suffixesOc.length && !found) {
            if (nomCommune.toLowerCase().endsWith(suffixesOc[i])) {
                ret = 1;
                found = true;
            }
            i++;
        }
        i = 0;
        while (i < suffixesOil.length && !found) {
            if (nomCommune.toLowerCase().endsWith(suffixesOil[i])) {
                ret = -1;
                found = true;
            }
            i++;
        }

        return ret;
    }

    public void generateCsv() {
        File csvFile = new File(fichierCsvSortie);
        try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFile));){
            csvWriter.println("nom, code_postal, latitude, longitude, variable_linguistique");
            for(Commune item : communes){
                csvWriter.println(item.getNom()+","+item.getCodePostal()+","+item.getLatitude()+","+item.getLongitude()+","+item.getVariableLinguistique());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
