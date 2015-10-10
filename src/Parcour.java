import java.util.ArrayList;
import java.util.List;

public class Parcour {
	
	private List<Integer> parcour;
	private Double distTotal;
	
	public Parcour() {
		parcour = new ArrayList<Integer>();
		distTotal=0.0;
	}
	
	public void ajoutPremiereVille(int numVille){
		parcour.add(numVille);
		distTotal=0.0;
	}
	
	public void ajouterVille(int numVille, Double[][] matriceDist){
		if(parcour.isEmpty()){
			this.ajoutPremiereVille(numVille);
		}
		else {
			parcour.add(numVille);
			distTotal = distTotal + matriceDist[parcour.get(parcour.size()-1)][parcour.get(parcour.size()-2)];
		}
	}

	public boolean contient(int numVille){
		return parcour.contains(numVille);
	}
	
	public List<Integer> getParcour() {
		return parcour;
	}

	public void setParcour(List<Integer> parcour) {
		this.parcour = parcour;
	}

	public int getNbVille() {
		return parcour.size();
	}

	public Double getDistTotal() {
		return distTotal;
	}

	public void setDistTotal(Double distTotal) {
		this.distTotal = distTotal;
	}
}
