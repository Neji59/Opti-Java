import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Outils {

	public Double[][] calcDistMatrice(Double[][] matriceCoord, int nbVille){
		
		Double[][] distMatrice = new Double[nbVille][nbVille];
		
		for(int i = 0; i < nbVille; i++){
			for(int j = 0; j < nbVille; j++){
				if(i == j){
					distMatrice[i][j] = 0.0;
				}
				distMatrice[i][j] = calcDist(matriceCoord[i][0],matriceCoord[j][0],matriceCoord[i][1],matriceCoord[j][1]);
				distMatrice[j][i] = calcDist(matriceCoord[i][0],matriceCoord[j][0],matriceCoord[i][1],matriceCoord[j][1]);
			}
		}
		return distMatrice;
	}
	
	public Double calcDist(Double x1, Double x2, Double y1, Double y2){
		return Math.sqrt((((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))));
	}
	
	public boolean isVilleToutesVisite(Boolean[] visite){
		for(Boolean tmp : visite){
			if(!tmp){
				return false;
			}
		}
		return true;
	}
	
	public void aficherParcour(Parcour parcour){
		for(Integer i : parcour.getParcour()){
			System.out.print(i+1+" --> ");
		}
		System.out.println("");
		System.out.println("distance total : "+parcour.getDistTotal());
	}
	
	//Generation de la méthode aléatoire pour la méthode descendante. 
	/* TODO Ameliorer cette fonction !!!!! 
	 *      génération aléatoire. 
	 */
	public Integer[] genSolutionInitial(int nbVille){
		Set<Integer> solutionInitial = new HashSet<Integer>();
		while(solutionInitial.size() < 52){
			solutionInitial.add(Math.random());
		}
		
		for(Integer tmp : solutionInitial){
			System.out.print(tmp+" ");
		}
		return solutionInitial.toArray(new Integer[nbVille]);
		
	}
	
	//Generateur de voisin pour la méthode de descente
	// ATTENTION ! On fonctionne avec les indices !!! 
	public Integer[][] generateurVoisin(int nbVille){
		Integer[][] voisin = new Integer[((nbVille-1)*nbVille)/2][2];
		int indiceCourant = 0;
		for (int i = 1; i < nbVille;i++){
			for(int j = i+1; j <= nbVille; j++){
				voisin[indiceCourant][0]=i;
				voisin[indiceCourant][1]=j;
				indiceCourant++;
			}
		}
		return voisin;
	}
	
	//Calcul la distance d'une solution.
	public Double calcDistance(Integer[] solution, Double[][] matriceDist, int nbVille){
		Double resultat = 0.0;
		for(int i = 0; i < nbVille; i++){
			resultat += matriceDist[solution[i]][solution[(i+1)%52]];
		}
		return resultat;
	}
	
	//Affichage Resultat
	public void afficherResultat(Integer[] solution, int nbVille, Double[][] matriceDist){
		System.out.println("La solution est la suivante : ");
		for(int i = 0; i < nbVille; i++){
			System.out.print((solution[i]+1)+" --> ");
		}
		System.out.println("La distance associe est : "+calcDistance(solution, matriceDist, nbVille)+" ! ");
	}
}
