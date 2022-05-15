package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import org.insa.graphs.model.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    public Label newLabel(int sommet, ShortestPathData data) {
    	Graph graph = data.getGraph();
    	Point pointCourant = graph.getNodes().get(sommet).getPoint();
    	double estimatedCost;
    	if (data.getMode()==Mode.LENGTH) {
    		estimatedCost = pointCourant.distanceTo(data.getDestination().getPoint());
    	}else {
    		estimatedCost = pointCourant.distanceTo(data.getDestination().getPoint())/(graph.getGraphInformation().getMaximumSpeed()/3.6);
    	}
    	return new LabelStar(sommet, estimatedCost);
    }

}