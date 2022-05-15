package org.insa.graphs.algorithm.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
import org.junit.Before;
import org.junit.Ignore;

public class DijkstraTest {

    	private Graph graph, graphJap;
    	private Path path;
    	private ShortestPathAlgorithm algorithme;
    	private Node origine, destination;
    	private ArcInspector arcFilter;
    	private ShortestPathData data;
    	private ShortestPathSolution solution, solutionAutre;

    	@Before
        public void initialisation() throws IOException {
	        //carte : haute garonne
	        String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
	        GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	        graph = reader.read();
	        
	        
	        /*
	        //carte : Japon (cause des problemes d'espace memoire sur les bureaux distants)
	        String mapNameJap = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/japan.mapgr";
	        GraphReader readerJap = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapNameJap))));
	        graphJap = readerJap.read();
	        */
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
				Assert.fail();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test chemin inexistant Astar
        @Test
        public void testCheminImpossibleAst() {
        	origine = graph.get(74415);
	    	destination = graph.get(77397);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	try {
	    		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	    	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test depart et destination identiques dijkstra
        @Test
        public void testDistNulleDij() {
        	origine = graph.get(74415);
        	destination = graph.get(74415);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test depart et destination identiques Astar
        @Test
        public void testDistNulleAst() {
        	origine = graph.get(74415);
        	destination = graph.get(74415);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(0);
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertTrue(solution.getStatus()== Status.INFEASIBLE);
        }
        
        //test validite du chemin trouvé en temps avec dijkstra par rapport au chemin précalculé
        @Test
        public void testTempsDijPrecalcTemps() {
        	//execution dijkstra en temps
        	origine = graph.get(10991);
        	destination = graph.get(89149);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(2); //critere en temps
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	//récupération chemin de référence
        	//path : insa-aeroport
	        final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_time.path";
	        try {
	        	final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
				path = pathReader.readPath(graph);
			} catch (IOException e) {
				Assert.fail();
			}
	        //System.out.println(solution.getSolvingTime()); //temps de calcul
        	Assert.assertEquals(solution.getPath().getArcs(), path.getArcs());
        }
        
        //test validite du chemin trouvé en temps avec Astar par rapport au chemin précalculé
        @Test
        public void testTempsAstPrecalc() {
        	//execution Astar en temps
        	origine = graph.get(10991);
        	destination = graph.get(89149);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(2); //critere en temps
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	//récupération chemin de référence
        	//path : insa-aeroport
	        final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_time.path";
	        try {
	        	final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
				path = pathReader.readPath(graph);
			} catch (IOException e) {
				Assert.fail();
			}
	        //System.out.println(solution.getSolvingTime()); //temps de calcul
        	Assert.assertEquals(solution.getPath().getArcs(), path.getArcs());
        }
        
        
        //test temps bellmanford vs dijkstra
        @Test
        public void testTempsDijBellman() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(2);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution bellmanford en temps pour reference
	    	try {
	    		algorithme = new BellmanFordAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	    	//execution dijkstra en temps
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())>0); //verifions que bellman est plus lent que dijkstra
        }
        
        //test temps bellmanford vs Astar
        @Test
        public void testTempsAstBellman() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(2);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution bellmanford en temps pour reference
	    	try {
	    		algorithme = new BellmanFordAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	    	//execution Astar en temps
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())>0); //verifions que bellman est plus lent que AStar
        }
        
        //test validite du chemin trouvé en distance avec dijkstra par rapport au chemin précalculé
        @Test
        public void testDistDijPrecalc() {
        	//execution dijkstra en distance
        	origine = graph.get(10991);
        	destination = graph.get(89149);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(1); //critere en distance
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	//récupération chemin de référence
        	//path : insa-aeroport
	        final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_length.path";
	        try {
	        	final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
				path = pathReader.readPath(graph);
			} catch (IOException e) {
				Assert.fail();
			}
	        //System.out.println(solution.getSolvingTime()); //temps de calcul
        	Assert.assertEquals(solution.getPath().getArcs(), path.getArcs());
        }
        
        //test validite du chemin trouvé en distance avec Astar par rapport au chemin précalculé
        @Test
        public void testDistAstPrecalc() {
        	//execution Astar en distance
        	origine = graph.get(10991);
        	destination = graph.get(89149);
        	arcFilter = ArcInspectorFactory.getAllFilters().get(1); //critere en distance
        	data = new ShortestPathData(graph, origine, destination, arcFilter);
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	//récupération chemin de référence
        	//path : insa-aeroport
	        final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_length.path";
	        try {
	        	final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
				path = pathReader.readPath(graph);
			} catch (IOException e) {
				Assert.fail();
			}
	        //System.out.println(solution.getSolvingTime()); //temps de calcul
        	Assert.assertEquals(solution.getPath().getArcs(), path.getArcs());        	
        }
        
        //test distance bellmanford vs dijkstra
        @Test
        public void testDistDijBellman() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(1);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution bellmanford en temps pour reference
	    	try {
	    		algorithme = new BellmanFordAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	    	//execution dijkstra en temps
        	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())>0); //verifions que bellman est plus lent que dijkstra
        }
        
        //test distance bellmanford vs Astar
        @Test
        public void testDistAstBellman() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(1);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution bellmanford en temps pour reference
	    	try {
	    		algorithme = new BellmanFordAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	    	//execution Astar en temps
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())>0); //verifions que bellman est plus lent que AStar
        }
       
        //test temps Dijkstra vs Astar
        @Test
        public void testTempsDijAst() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(2);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution chemin en temps avec dijkstra
	    	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	        //execution chemin en temps avec Astar
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	//Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())<0); //verifions que Dijkstra est plus lent que AStar
        }
        
        //test Distance Dijkstra vs Astar
        @Test
        public void testDistDijAst() {
        	origine = graph.get(10991);
	    	destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(1);
	    	data = new ShortestPathData(graph, origine, destination, arcFilter);
	    	//execution chemin en temps avec dijkstra
	    	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	        //execution chemin en temps avec Astar
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	//Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())<0); //verifions que Dijkstra est plus lent que AStar
        }
        
        //test temps long Dijkstra vs Astar (possible probleme de memoire insuffisante)
        @Test @Ignore
        public void testTempsDijAstLong() {
        	//TODO : chercher des coordonees sur la carte
        	//origine = graph.get(10991);
	    	//destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(2);
	    	data = new ShortestPathData(graphJap, origine, destination, arcFilter);
	    	//execution chemin très long en temps avec dijkstra
	    	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	        //execution chemin très long en temps avec Astar
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())<0); //verifions que Dijkstra est plus lent que AStar
        }
        
        //test Distance long Dijkstra vs Astar (possible probleme de memoire insuffisante)
        @Test @Ignore
        public void testDistDijAstLong() {
        	//TODO : chercher des coordonees sur la carte
        	//origine = graph.get(10991);
	    	//destination = graph.get(81149);
	    	arcFilter = ArcInspectorFactory.getAllFilters().get(1);
	    	data = new ShortestPathData(graphJap, origine, destination, arcFilter);
	    	//execution chemin très long en temps avec dijkstra
	    	try {
        		algorithme = new DijkstraAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solution = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
	        //execution chemin très long en temps avec Astar
        	try {
        		algorithme = new AStarAlgorithm(null);
				algorithme = (ShortestPathAlgorithm) AlgorithmFactory.createAlgorithm(algorithme.getClass(), data);
				solutionAutre = algorithme.run();
			} catch (Exception e) {
				Assert.fail();
			}
        	Assert.assertEquals(solutionAutre.getPath().getArcs(), solution.getPath().getArcs()); //verifions que les chemins sont identiques
        	Assert.assertTrue(solutionAutre.getSolvingTime().compareTo(solution.getSolvingTime())<0); //verifions que Dijkstra est plus lent que AStar
        }
}


