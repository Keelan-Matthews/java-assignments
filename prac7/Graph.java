import static java.lang.Float.POSITIVE_INFINITY;

public class Graph {
    private Vertex[] vertices;
    public Graph(int numVertex){
        vertices = new Vertex[numVertex];
    }

    public boolean addVertex(String nName, int numVertices){
        int openPos = -1;
        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] == null){
                if(openPos == -1)
                    openPos = i;
            } else {
                if(vertices[i].getVName().equals(nName)){
                    return false;
                }
            }
        }
        if(openPos == -1)
            return false;

        vertices[openPos] = new Vertex(nName, numVertices);
        return true;
    }

    public Vertex getVertex(String nName){
        for(int i=0; i < vertices.length; i++){
            if(vertices[i] != null && vertices[i].getVName().equals(nName)){
                return vertices[i];
            }
        }
        return null;
    }

    public boolean addEdge(String pointA, String pointB, float value, String vName){
        Vertex pA = getVertex(pointA);
        Vertex pB = getVertex(pointB);
        if(pA == null || pB == null)
            return false;

        Edge v = new Edge(value, vName);
        v.pointA = pA;
        v.pointB = pB;
        pA.addEdge(v);
        return true;
    }

    //Do not change the above functions
    //Implement the functions below

    public boolean isAccessable(Vertex vertexFrom, Vertex vertexTo){
        return vertexFrom.isAccessable(vertexTo);
    }

    public float shortestDistance(Vertex vertexFrom, Vertex vertexTo){
        if (!isAccessable(vertexFrom, vertexTo)) return Float.POSITIVE_INFINITY;

        for (int i = 0; i < vertexFrom.getEdges().length; i++) {

        }
    }

    public Vertex[] shortestPath(Vertex vertexFrom, Vertex vertexTo){
    }

    public boolean containsCycle(Vertex startingVertex){
    }

    public void listCycles(){
    }

    public int countEdges(){
    }

    public String DFT(Vertex startVertex){
    }

    String[] getPath(Vertex vertexFrom, Vertex vertexTo) {
        Edge[] fromEdge = vertexFrom.getEdges();
        String[] fromVertex = new String[fromEdge.length];

        for (int i = 0; i < fromEdge.length; i++){
            fromVertex[i] = fromEdge[i].getEName().substring(1,1);
        }

        for (int i = 0; i < fromEdge.length; i++){
            if (fromVertex[i].equals(vertexTo.getVName()))
                return fromVertex;
            else
                return getPath(fromVertex[i], )
        }

    }
}
