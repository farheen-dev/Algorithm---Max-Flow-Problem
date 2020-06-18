
/*
* Student Name : Mohamed Boosary Fathima Farheen
* IIT Student No : 2018323
* UOW Student No : w1742063
*/

/*
*  Source : https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;
import java.util.LinkedList;

public class MaxFlow {

    //Creating Scanner object to take user inputs
    public static Scanner input = new Scanner(System.in);

    //Number of vertices in graph
    static int Vertex;

    //Constructor
    public MaxFlow(int x) {
        Vertex = x;
    }

    // Driver program to test below methods
    public static void main(String[] args) {

        //Getting the vertex no from the user
        System.out.println("Enter number of vertex :");

        //User Input Validations
        inputValidationForVertex();
        int vertex = input.nextInt();

        //Checking if the user entered no of vertex is less than 6
        while (vertex < 6) {
            System.out.println("The no of vertex in the graph should be at least 6 ");
            System.out.println("Enter number of vertex :");
            inputValidationForVertex();
            vertex = input.nextInt();
        }

        //Creating an object of the GraphAdjacencyMatrix class
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(vertex);

        //Intial choice which is user select from the dashboard is set to zero
        int choice = 0;

        while (choice != 7) {
            //using Do while loop for validation
            do {
                // Displaying the menu
                displayMenu();
                // User input validation
                while (!input.hasNextInt()) {
                    System.out.println("Invalid option entered \n");
                    // Displaying the  menu again
                    displayMenu();
                    input.next();//remove the none integer that was previously entered
                    // this is a must otherwise the program will loop through infinitely
                }
                // getting the input from the system and assigning it to a variable named option
                choice = input.nextInt();

                //Performing perticular operation according to the option choose
                switch (choice) {
                    case 1: //Adding edges
                        System.out.println("Enter number of edges :");
                        inputValidationForEdges();
                        int edges = input.nextInt();

                        for (int i = 0; i < edges; i++) {
                            System.out.println("");
                            System.out.println("Enter the edge " + i);
                            System.out.println("");
                            System.out.println("Enter node1 : ");
                            inputValidationForSource();
                            int source = input.nextInt();
                            System.out.println("Enter node2 : ");
                            inputValidationForDestination();
                            int destination = input.nextInt();
                            System.out.println("Enter weight : ");
                            inputValidationForWeight();
                            int weight = input.nextInt();
                            graph.addEdge(source, destination, weight);
                        }
                        break;
                    case 2://deleting edges
                        System.out.println("Enter number of edges need to delete:");
                        inputValidationForEdges();
                        int n = input.nextInt();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Enter node1 : ");
                            inputValidationForSource();
                            int source = input.nextInt();
                            System.out.println("Enter node2 : ");
                            inputValidationForDestination();
                            int destination = input.nextInt();
                            graph.removeEdge(source, destination);
                        }
                        break;
                    case 3://Updating weight
                        System.out.println("Enter number of edges need to update the weight:");
                        inputValidationForUpdateWeight();
                        int b = input.nextInt();
                        for (int i = 0; i < b; i++) {
                            System.out.println("Enter node1 : ");
                            inputValidationForSource();
                            int source = input.nextInt();
                            System.out.println("Enter node2 : ");
                            inputValidationForDestination();
                            int destination = input.nextInt();
                            System.out.println("Enter weight : ");
                            inputValidationForWeight();
                            int weight = input.nextInt();
                            graph.updateEdgeWeight(source, destination, weight);
                        }

                        break;
                    case 4://Printing the graph in matrix representation
                        graph.printGraph();
                        break;
                    case 5://Printing the maximum flow
                        System.out.println("Input Source:");
                        inputValidationForStart();
                        int source = input.nextInt();
                        System.out.println("Input target:");
                        inputValidationForTarget();
                        int target = input.nextInt();
                        MaxFlow m = new MaxFlow(vertex);
                        System.out.println("The maximum possible flow is "
                                + m.fordFulkerson(graph.getGraph(), source, target));
                        break;
                    case 6:// Input size time calculation using files
                        Map<String, Integer> myMap = new HashMap<String, Integer>();
                        myMap.put("txt6.txt", 6);
                        myMap.put("txt12.txt", 12);
                        myMap.put("txt24.txt", 24);
                        myMap.put("txt48.txt", 48);

                        MaxFlow m1 = new MaxFlow(vertex);
                        Scanner s = null;
                        input.nextLine();
                        System.out.println("Enter file Location:");
                        String path = input.nextLine();

                        //Validation if the user entered a wrong path
                        while (!path.equals("txt6.txt") && !path.equals("txt12.txt") && !path.equals("txt24.txt") && !path.equals("txt48.txt"))
                        {
                                System.out.println("Wrong file path name entered");
                                System.out.println("Enter file Location:");
                                path = input.nextLine();
                        }

                        //Validations if the user entered a wrong text file related to entered vertex
                        while(myMap.get(path) != Vertex )
                        {
                            System.out.println("The file type is not valid with the vertex");
                            System.out.println("Enter file Location:");
                            path = input.nextLine();
                            while (!path.equals("txt6.txt") && !path.equals("txt12.txt") && !path.equals("txt24.txt") && !path.equals("txt48.txt"))
                            {
                                System.out.println("Wrong file path name entered");
                                System.out.println("Enter file Location:");
                                path = input.nextLine();
                            }
                        }

                        System.out.println("path is "+path);
                        try {
                            s = new Scanner(new File(path));
                            int startNode = s.nextInt();
                            int endNode = s.nextInt();
                            System.out.println(startNode+" "+endNode);
                            s.close();
                            graph.setMatrix(file(path,vertex));
                            //My watch start here
                            Stopwatch timer = new Stopwatch();
                            System.out.println("The maximum possible flow is "+ m1.fordFulkerson(graph.getGraph(), startNode, endNode));
                            //my watch will end here
                            StdOut.println("Elapsed Time = " + timer.elapsedTime());

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 7://End of the program
                        System.out.println("Exiting the Program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong input entered");

                }

            } while (choice < 1 || choice > 7);

        }

    }

    // Method which is use to travel through the graph
    public boolean bfsGraphTraversal(int residualGraph[][], int s, int t, int parent[])
    {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[Vertex];
        for (int i = 0; i < Vertex; ++i) {
            visited[i] = false;
        }

        /* Create a queue, enqueue source vertex and mark
        source vertex as visited
         */
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        //int count = 0;
        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < Vertex; v++) {
                //count++;
                if (visited[v] == false && residualGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;

                }
            }
        }
        //System.out.println( Arrays.toString(parent));
        //System.out.println("c " + count);

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    public int fordFulkerson(int graph[][], int s, int t)
    {
        int u, v;

        /* Create a residual graph and fill the residual graph
         with given capacities
         */
        int residualGraph[][] = new int[Vertex][Vertex];

        //Initializing the residual graph
        for (u = 0; u < Vertex; u++) {
            for (v = 0; v < Vertex; v++) {
                residualGraph[u][v] = graph[u][v];
            }
        }

        // This array is filled by BFS and to store path
        int parent[] = new int[Vertex];

        // Intial Flow is set to zero
        int max_flow = 0;

        while (bfsGraphTraversal(residualGraph, s, t, parent)) //depends on no of max flow paths
        {

            int path_flow = Integer.MAX_VALUE;
           // System.out.println("pf " + path_flow);

            //finding the minimum capacity along a ma flow path(Augmented path)
            for (v = t; v != s; v = parent[v]) {  // depend on the no of edges of a perticular max flow path
                u = parent[v];
               // System.out.println(u);
                path_flow = Math.min(path_flow, residualGraph[u][v]);

            }

            List<Integer> nodes = new LinkedList<Integer>();

            // int count = 0
            //Updating the residual graph
            for (v = t; v != s; v = parent[v]) {// depend on the no of edges of a perticular max flow path
                u = parent[v];
                nodes.add(u);
                residualGraph[u][v] -= path_flow;
                residualGraph[v][u] += path_flow;
               // count++;
            }
            //System.out.println("c " + count);
            Collections.reverse(nodes);

            //Printing the paths along the flow calculated
            for (Integer node : nodes) {// depend on the no of nodes of a perticular max flow path
                System.out.print(node + " > ");
            }

            //Printing the bottleneck path
            System.out.println(+t + " The Bottleneck capacity of flow path = " + path_flow);

            // Add path flow to overall flow
            max_flow += path_flow;
        }


        // Returning the maximum flow
        return max_flow;
    }

    //Method display the dashboard
    public static void displayMenu()
    {
        System.out.println("\n Dashboard");
        System.out.println(">>");
        System.out.println("1)Add edges");
        System.out.println("2)Delete edges");
        System.out.println("3)Update edge Weight");
        System.out.println("4)Print Graph");
        System.out.println("5)Find MaxFlow");
        System.out.println("6)File input for check time esclaped ");
        System.out.println("7)Exist \n");
        System.out.print("Enter an option:");
    }

    //User Input Validation for vertex
    public static void inputValidationForVertex()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter number of vertex :");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for vertex
    public static void inputValidationForEdges()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter number of Edges :");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for Source
    public static void inputValidationForSource()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter node1 :");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for Destination
    public static void inputValidationForDestination()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter node2 :");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for weight
    public static void inputValidationForWeight()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter Weight :");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for edges for update weight
    public static void inputValidationForUpdateWeight()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Enter number of edges need to update the weight:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for edges for update weight
    public static void inputValidationForStart()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Input Source:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //User Input Validation for edges for update weight
    public static void inputValidationForTarget()
    {

        // User input validation
        while (!input.hasNextInt()) {
            System.out.println("Invalid value entered\n");
            // Enter the no of vertex again
            System.out.println("Input target:");
            input.next();//remove the none integer that was previously entered
            // this is a must otherwise the program will loop through infinitely
        }

    }

    //Method to test the dataset txt file for algorithm analysis
    public static int[][] file(String path ,int vertex)
    {
        try {
            Scanner s = new Scanner(new File(path));
            int rows = s.nextInt();
            int columns = s.nextInt();
            int matrix[][] = new int[vertex][vertex];
            while (s.hasNextInt())
            {
                for (int i = 0; i < matrix.length; i++)
                {
                    for (int co = 0; co < matrix.length; co++)
                    {
                        matrix[i][co] = s.nextInt();
                    }
                }
            }
            s.close();
            return matrix;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    }




}
