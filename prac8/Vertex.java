public class Vertex {
    private String vName;
    private Edge[] edges;
    private int num = 0;
    private int TSNum = 0;
    public Vertex(String vName, int numedges){
        this.vName = vName;
        edges = new Edge[numedges];
    }

    public String getVName(){
        return vName;
    }

    public Edge[] getEdges(){
        return edges;
    }

    public boolean addEdge(Edge e){
        if(e == null)
            return false;

        for(int i=0; i < edges.length; i++)
        {
            if(edges[i] == null)
            {
                edges[i] = e;
                return true;
            }
        }
        return false;
    }

    //Do not change the above functions
    //Implement the functions below 
    public int getNum() {return num;}
    public void setNum(int num) {this.num = num;}
    public int getTSNum() {return TSNum;}
    public void setTSNum(int TSNum) {this.TSNum = TSNum;}
}
