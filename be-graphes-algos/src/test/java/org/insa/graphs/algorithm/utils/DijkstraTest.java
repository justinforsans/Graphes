package org.insa.graphs.algorithm.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.AlgorithmFactory;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.ShortestPathAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;

public class DijkstraTest {

    	private Graph graph;
    	private Path path;
    	private ShortestPathAlgorithm algorithme;
    	private Node origine, destination;
    	private ArcInspector arcFilter;
    	private ShortestPathData data;
    	private ShortestPathSolution solution;

    	@Before
        public void initialisation() throws Exception{
	        //carte : haute garonne
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	        // Read the graph.
	        graph = reader.read();
	        //path : insa-aeroport distance
	        final String pathInsaAeroDist = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_length.path";
	        // Create a PathReader.
	        final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathInsaAeroDist))));
	        //Read the path.
	        path = pathReader.readPath(graph);
        }
        
        //test chemin inexistant dijkstra
        @Test
        public void testCheminImpossibleDij () {
        	origine = graph.get(74415);
        	destination = graph.get(77397);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test chemin inexistant Astar
        @Test
        public void testCheminImpossibleAst () {
        	origine = graph.get(74415);
	    	destination = graph.get(77397);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	try {
	    		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test depart et destination identiques dijkstra
        @Test
        public void testDistanceNulleDij () {
        	origine = graph.get(74415);
        	destination = graph.get(74415);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        //test depart et destination identiques Astar
        @Test
        public void testDistanceNulleAst () {
        	origine = graph.get(74415);
        	destination = graph.get(74415);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test validite du chemin trouvé avec dijkstra par rapport au chemin précalculé
        @Test
        public void testCheminValideTempsDij () {
        	//execution dijkstra en temps
        	//execution Astar en temps
        
        	
        }
        
        //test validite du chemin trouvé avec Astar par rapport au chemin précalculé
        @Test
        public void testCheminValideTempsAst () {
        	
        }
        //test temps bellmanford vs dijkstra
        @Test
        public void testTempsDij () {
        	//execution bellmanford en temps pour reference
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	try {
	    		algorithme = new BellmanFordAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
         
        }
        
        //test temps bellmanford vs Astar
        @Test
        public void testTempsAst () {
        	  //execution bellmanford en temps pour reference
            {
            	origine = graph.get(10991);
    	    	destination = graph.get(81149);
    	    	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
    	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
    	    	try {
    	    		algorithme = new BellmanFordAlgorithm(null);
    				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
    				solution = algorithme.run();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }
        	
        }
        
        //execution bellmanford en distance pour reference
        //execution dijkstra en distance
        //execution Astar en distance
        //test validite du chemin trouvé avec dijkstra par rapport au chemin précalculé
        @Test
        public void testCheminValideDistanceDij () {
        	
        }
        //test validite du chemin trouvé avec Astar par rapport au chemin précalculé
        @Test
        public void testCheminValideDistanceAst () {
        	
        }
        //test distance bellmanford vs dijkstra
        @Test
        public void testDistanceDij() {
        	
        }
        //test distance bellmanford vs Astar
        @Test
        public void testDistanceAst () {
        	
        }
        
        //execution chemin très long en temps avec dijkstra
        //execution chemin très long en temps avec Astar
        //test duree Dijkstra vs Astar
        @Test
        public void testLongTemps () {
        	
        }
        //execution chemin très long en distance avec dijkstra
        //execution chemin très long en distance avec Astar
        //test distance Dijkstra vs Astar
        @Test
        public void testLongueDistance () {
        	
        }
        
        
}


