package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    public Label newLabel(int sommet, ShortestPathData data) { //renvoie un nouveau label, permet l'override  dans le cas de Astar
    	return new Label(sommet);
    }
    
    @Override
    protected ShortestPathSolution doRun() {
    	
        final ShortestPathData data = getInputData(); //recuperation donnees
        ShortestPathSolution solution = null;  //initialisation solution
        boolean found = false;  //solution pas encore trouvee
        
        //cas null
        //cas INFEASIBLE
        //cas 1 node
        
        ArrayList<Arc> arcs = new ArrayList<>();
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        
        Label[] labels = new Label[nbNodes]; //initialisation des labels 
        for(int i = 0 ; i<nbNodes; i++) {
        	labels[i]=newLabel(i, data);
        }
        
        BinaryHeap<Label> toCheck = new BinaryHeap<Label>(); //initialisation de l'arbre binaire a traiter
        labels[data.getOrigin().getId()].setCout(0); //cout nul dans le cas de l'origine
        toCheck.insert(labels[data.getOrigin().getId()]);  //insertion de l'origine dans l'arbre
        notifyOriginProcessed(data.getOrigin());  //signalement du traitement de l'origine
        
        while(!toCheck.isEmpty() && !found) { //tant que l'arbre n'est pas vide et qu'on n'a pas trouvé de solution. double condition pour le cas ou aucun chemin n'existe
        	Label courant = toCheck.deleteMin();  //node à traiter
        	
        	if (labels[data.getDestination().getId()]==courant) { //cas ou le node est la destination
        		found = true;
        	}
        	
            courant.setMarque(true); //on marque le node que l'on traite
            notifyNodeMarked(graph.getNodes().get(courant.getSommet()));  //et on signale son traitement
            
            for(Arc arc: graph.get(courant.getSommet()).getSuccessors()) {  //on va traiter tous les arcs en contact avec le node
            	if(!labels[arc.getDestination().getId()].isMarque() && data.isAllowed(arc)) {  //on verifie que l'arc ne debouche pas sur un node marqué. is allowed verifie que l'arc est accessible au moyen de transport choisi
            		double cout = courant.getCout();
            		if (data.getMode()==Mode.LENGTH) { //on actualise le cout en distance ou en temps
            			cout+=arc.getLength();
            		}else {
            			cout+=arc.getMinimumTravelTime();
            		}
            			notifyNodeReached(arc.getDestination());
            	try {
            		toCheck.remove(labels[arc.getDestination().getId()]);
            	}catch(ElementNotFoundException e){}
            	if(cout<labels[arc.getDestination().getId()].getCout()) {
        			labels[arc.getDestination().getId()].setCout(cout);
        			labels[arc.getDestination().getId()].setPere(arc);
        		}
            	toCheck.insert(labels[arc.getDestination().getId()]);
            	}
            }
        }
        
        // pas de départ, ou pas de chemin trouve
        if ( data.getOrigin() == null || !found) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {
            // The destination has been found, notify the observers.
        	notifyDestinationReached(data.getDestination());

	        Label label = labels[data.getDestination().getId()];
	        while(label.getPere()!=null) {
	        	arcs.add(label.getPere());
	        	label=labels[label.getPere().getOrigin().getId()];
	        }
	        Collections.reverse(arcs);
	        Path path = new Path(graph, arcs);
	        solution= new ShortestPathSolution(data,Status.OPTIMAL,path);
        }
        return solution;
    }

}