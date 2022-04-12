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

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        //cas null
        //cas INFEASIBLE
        //cas 1 node
        ArrayList<Arc> arcs = new ArrayList<>();
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        Label[] labels = new Label[nbNodes];
        for(int i = 0 ; i<nbNodes; i++) {
        	labels[i]=new Label(i);
        }
        labels[data.getOrigin().getId()].setCout(0);
        BinaryHeap<Label> toCheck = new BinaryHeap<Label>();
        toCheck.insert(labels[data.getOrigin().getId()]);
        notifyOriginProcessed(data.getOrigin());///
        
        boolean found = false;
        while(!toCheck.isEmpty() && !found) {
        	Label courant = toCheck.deleteMin();
        	if (labels[data.getDestination().getId()]==courant) {
        		found = true;
        	}
            courant.setMarque(true);
            notifyNodeMarked(graph.getNodes().get(courant.getSommet()));
            for(Arc arc: graph.get(courant.getSommet()).getSuccessors()) {
            	if(!labels[arc.getDestination().getId()].isMarque() && data.isAllowed(arc)) {
            		double cout = courant.getCout();
            		if (data.getMode()==Mode.LENGTH) {
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