package org.insa.graphes.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;

import org.junit.Test;
import org.junit.Assume;

public class DijkstraTest {

    
/*
         // Create a graph reader.
        final GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

        // Read the graph.
        final Graph graph = reader.read();

        // Create a PathReader.
        final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));

        //Read the path.
        final Path path = pathReader.readPath(graph);
*/
        
        //carte : haute garonne
        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
        
        //path : insa-aeroport distance
        final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_aeroport_length.path";

        //test chemin inexistant dijkstra
        @Test
        public void testCheminImpossibleDij () {
        	
        }
        //test chemin inexistant Astar
        @Test
        public void testCheminImpossibleAst () {
        	
        }
        
        //test chemin impratiquable pour voitures dijkstra
        @Test
        public void testNoVoitureDij () {
        	
        }
        //test chemin impratiquable pour voitures Astar
        @Test
        public void testNoVoitureAst () {
        	
        }
        
        //test depart et destination identiques dijkstra
        @Test
        public void testDistanceNulleDij () {
        	
        }
        //test depart et destination identiques Astar
        @Test
        public void testDistanceNulleAst () {
        	
        }
        
        //execution bellmanford en temps pour reference
        //execution dijkstra en temps
        //execution Astar en temps
        //test validite du chemin trouvé avec dijkstra par rapport au chemin précalculé
        @Test
        public void testCheminValideTempsDij () {
        	
        }
        //test validite du chemin trouvé avec Astar par rapport au chemin précalculé
        @Test
        public void testCheminValideTempsAst () {
        	
        }
        //test temps bellmanford vs dijkstra
        @Test
        public void testTempsDij () {
        	
        }
        //test temps bellmanford vs Astar
        @Test
        public void testTempsAst () {
        	
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


