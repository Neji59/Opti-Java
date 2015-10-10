
public class Methode {
	
	public static void methodeGreedy(String url, int nbVille){
		Outils otls = new Outils();		
		
		Double[][] matriceCoord = Parser.parseFileToCoordMatrice(url, nbVille);
		Double[][] matriceDist = otls.calcDistMatrice(matriceCoord, 52);
		
		Parcour parcourFinal = new Parcour();
		
		for (int i = 0; i < nbVille; i++){
			Parcour parcourCourant = new Parcour();
			int villeCour = i;
			parcourCourant.ajoutPremiereVille(villeCour);
			while(parcourCourant.getNbVille()!= nbVille){
				int villePlusProche= 0;
				do {
					villePlusProche++;
				} while(villePlusProche==villeCour || parcourCourant.contient(villePlusProche));
				
				for(int j = 0; j < nbVille; j++){
					if(j!=villeCour && !parcourCourant.contient(j)){
						if(matriceDist[villeCour][j] < matriceDist[villeCour][villePlusProche]){
							villePlusProche = j;
						}
					}
				}
				parcourCourant.ajouterVille(villePlusProche, matriceDist);
				villeCour = villePlusProche;
			}
			parcourCourant.setDistTotal(parcourCourant.getDistTotal()+matriceDist[i][villeCour]);
			if(parcourFinal.getParcour().isEmpty()){
				parcourFinal = parcourCourant;
			}
			else{
				if(parcourCourant.getDistTotal() < parcourFinal.getDistTotal()){
					parcourFinal = parcourCourant;
				}
			}
		}
		
		otls.aficherParcour(parcourFinal);
	}
	
	public static void methodeDescendante(String url, int nbVille){
		
		Outils otls = new Outils();
		
		Double[][] matriceCoord = Parser.parseFileToCoordMatrice(url, nbVille);
		Double[][] matriceDist = otls.calcDistMatrice(matriceCoord, 52);
		
		Integer[] solution = otls.genSolutionInitial(nbVille);
		Integer[] solutionCourante = new Integer[nbVille];
		//Tableau des indices des voisins. 
		Integer[][] voisin = otls.generateurVoisin(nbVille);
		
		Double dist = otls.calcDistance(solution, matriceDist, nbVille);
		boolean amelioration = true;
		
		while(amelioration){
			amelioration = false;
			solutionCourante = solution;
			
			for(int i = 0; i < (((nbVille-1)*nbVille)/2); i++){
				
				//On parcour le voisin.
				Integer[] solVoisin = solutionCourante;
				int tmp = solVoisin[voisin[i][0]-1];
				solVoisin[voisin[i][0]-1] = solVoisin[voisin[i][1]-1];
				solVoisin[voisin[i][1]-1] = tmp;
				
				if(otls.calcDistance(solVoisin, matriceDist, nbVille) < otls.calcDistance(solutionCourante, matriceDist, nbVille)){
					amelioration = true;
					solution = solVoisin;
				}
			}
		}
		
		otls.afficherResultat(solution, nbVille, matriceDist);
	}
}
