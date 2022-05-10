package org.insa.graphs.algorithm.shortestpath;

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
    	double estimatedCost = pointCourant.distanceTo(data.getDestination().getPoint());
    	return new LabelStar(sommet, estimatedCost);
    }

}