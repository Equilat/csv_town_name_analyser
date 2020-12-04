import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVAnalyser {


    String fichierCsv = "../data/laposte_hexasmal.csv";
    BufferedReader br = null;
    String ligne = "";
    String separateur = ";";
    ArrayList<Commune> communes = new ArrayList<>();
    String[] adjectifs = {"ville", "villers", "villiers", "franche", "blanc", "bonne", "fleury"};

    public CSVAnalyser() {

    }

    public void readFile() {
        try {

            br = new BufferedReader(new FileReader(fichierCsv));
            ligne = br.readLine();
            while ((ligne = br.readLine()) != null) {

                // use comma as separator
                String[] ville = ligne.split(separateur);
                int variableLinguistique = this.analyserNom(ville[5]);
                Commune commune = new Commune(ville[1],ville[2], ville[5], variableLinguistique);
                communes.add(commune);
            }

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
        while(i < adjectifs.length && !found) {
            if(nomCommune.toLowerCase().startsWith(adjectifs[i]) || nomCommune.toLowerCase().endsWith("ac")) {
                ret = 1;
                found = true;
            }
            else if (nomCommune.toLowerCase().endsWith(adjectifs[i])) {
                ret = -1;
                found = true;
            }
            i++;
        }

        return ret;
    }





}
