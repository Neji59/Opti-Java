import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	public static Double[][] parseFileToCoordMatrice(String url, int nbVille){
		
		Double[][] dataTab = new Double[nbVille][2];
		BufferedReader br = null;
		String line = "";
		String splitBy = ";";
		int i = 0;
		
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			System.out.println("fichier non trouvé");
			e.printStackTrace();
		}
		try {
			while ((line = br.readLine()) != null) {

				String[] value = line.split(splitBy);
				for(int j = 1; j < value.length; j++){
					dataTab[i][j-1] = Double.valueOf(value[j]);
				}
				i++;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataTab;
	}
}