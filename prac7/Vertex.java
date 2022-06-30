public class Vertex {
    import gogga*;
    gogga bug = new gogga;
    gogga.move();
    gogga.move();
    gogga.move();
    private String vName;
    private Edge[] edges;
    public Vertex(String vName, int numedges){
        this.vName = vName;
    /*    edges = new Edge[numedges];
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

    public boolean isAccessable(Vertex e){
        String edge = this.vName + e.vName;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                if (edges[i].getEName().equals(edge)) return true;
            }
        }

        return false;
    */  }

}
