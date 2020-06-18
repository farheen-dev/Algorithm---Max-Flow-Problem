
public class GraphAdjacencyMatrix {

    int vertex; //no of vertex in graph
    int matrix[][]; //2d array for representation of the graph

    //Constructor
    public GraphAdjacencyMatrix(int vertex)
    {
        this.vertex = vertex;
        this.matrix = new int[vertex][vertex];
    }

    //Method to return the 2d array (matrix) graph
    public   int[][] getGraph()
    {
        return this.matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    //Method to add edges to the graph
    public void addEdge(int source, int destination, int weight)
    {
        //add edge
        this.matrix[source][destination] = weight;
    }

    //Method to update the edge weight/capacity of links
    public void updateEdgeWeight(int source, int destination, int weight)
    {
        //update weight of the edges
        this.matrix[source][destination] = weight;
    }

    //Method to remove edges from the graph
    public void removeEdge(int source, int destination)
    {
        matrix[source][destination] = 0;
    }

    //Method to print the create graph as Adjacency Matrix
    public void printGraph()
    {
        System.out.println("Graph: (Adjacency Matrix)");

        //Using for loop to print the Adjacency Matrix
        for (int i = 0; i < vertex; i++)
        {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        //Using for loop to print the what are the edges that a connect to a particular vertex
        for (int i = 0; i < vertex; i++)
        {
            System.out.print("Vertex " + i + " is connected to :");
            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j] > 0) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }


}

