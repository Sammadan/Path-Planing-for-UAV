import java.util.Scanner;

public class algo {
    
    private static final int NO_PARENT = -1;
    
    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private static void dijkstra(double[][] adjacencyMatrix,
                                 int startVertex)
    {
        int nVertices = adjacencyMatrix[0].length;
        
        // shortestDistances[i] will hold the
        // shortest distance from src to i
        double[] shortestDistances = new double[nVertices];
        
        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];
        
        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
        
        // Distance of source vertex from
        // itself is always 0
        shortestDistances[(int)startVertex] = 0;
        
        // Parent array to store shortest
        // path tree
        double[] parents = new double[nVertices];
        
        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;
        
        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {
            
            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            double nearestVertex = -1;
            double shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            
            // Mark the picked vertex as
            // processed
            added[(int)nearestVertex] = true;
            
            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (double vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                double edgeDistance = adjacencyMatrix[(int)nearestVertex][(int)vertexIndex];
                
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <
                        shortestDistances[(int)vertexIndex]))
                {
                    parents[(int)vertexIndex] = nearestVertex;
                    shortestDistances[(int)vertexIndex] = shortestDistance +
                    edgeDistance;
                }
            }
        }
        
        printSolution(startVertex, shortestDistances, parents);
    }
    
    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static void printSolution(double startVertex,
                                      double[] distances,
                                      double[] parents)
    {
        double nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");
        
        for (double vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[(int)vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }
    
    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(double currentVertex,
                                  double[] parents)
    {
        
        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[(int)currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
    
    
    public static class coordinate
    {
        double latitude;
        double longitude;
        
        coordinate(){
            latitude=0;
            longitude=0;
        }
    }
    
    public static void swapCoordinates(coordinate a,coordinate b){
        coordinate temp = new coordinate();
        
        temp.latitude = a.latitude;
        temp.longitude = a.longitude;
        
        a.latitude = b.latitude;
        a.longitude = b.longitude;
        
        b.latitude = temp.latitude;
        b.longitude = temp.longitude;
        
    }
    
    public static void sortingCoordinates(coordinate arr[]){
        // Sorting according to Latitude. : Bubble sorting in Decending Order
        
        int n = arr.length;
        
        for(int i = 0 ; i<n-1 ; i++){
            for(int j = 0 ; j<n-i-1 ; j++){
                if(arr[j].latitude < arr[i].latitude)
                    swapCoordinates(arr[i] , arr[j]);
            }
        }
    }
    
    public static double distance(coordinate a , coordinate b){
        double x = a.latitude - b.latitude;
        double y = a.longitude - b.longitude;
        x = x*x;
        y = y*y;
        double ans = x + y;
        ans = Math.sqrt(ans);
        return ans;
    }
    
    public static double[][] formGraph(coordinate arr[]){
        
        int n = arr.length;
        
        double edges[][] = new double[n][n];
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i != j){
                    double a = distance(arr[i] , arr[j]);
                    edges[i][j] = a;
                    edges[j][i] = a;
                }
                    
            }
        }
        
        return edges;
        
    }
    
    public static void main(String args[]) {
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter number of Locations");
        
        int n = s.nextInt();
        
        coordinate arr[] = new coordinate[n];
        
        for(int i = 0 ; i<n ; i++) arr[i] = new coordinate();
        
        System.out.println(arr[0].latitude + " " + arr[1].longitude);
        
        for(int i = 0 ; i < n ; i++){
            System.out.println("Enter the latitude of " + i + " th location");
            arr[i].latitude = s.nextDouble();
            System.out.println("Enter the longitude of " + i + " th location");
            arr[i].longitude = s.nextInt();
            
        }
        
        
        double edges[][] = formGraph(arr);
        System.out.println();
        // Printing the edges Matrix.
        System.out.println("Printing the Graph Matrix:");
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<n ; j++){
                System.out.print(edges[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println();
        // Printing the numbers assigned to the coordinates.
        System.out.println("The following number represent the Corresponding Coordinates :");
        for(double i=0; i<n ; i++){
            System.out.println(i + "   Latitude: " + arr[(int)i].latitude + "   Longitude: " + arr[(int)i].longitude);
        }
        
        System.out.println();
        // Appling Dijkstra Algo.
        dijkstra(edges, 0);
        
    }
}
