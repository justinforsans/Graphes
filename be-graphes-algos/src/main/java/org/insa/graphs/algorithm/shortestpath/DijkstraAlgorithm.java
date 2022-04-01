package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @SuppressWarnings("unused")
	@Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        Label[] labels = new Label[nbNodes];
        for(int i = 0 ; i<nbNodes; i++) {
        	labels[i]=new Label(i);
        }
        
        return solution;
    }

}
