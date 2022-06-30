public class Main {
    public static void main(String[] args) throws Exception {
        ExampleA();
        ExampleB();
    }
    public static void ExampleA() {
        Graph g = new Graph(5);
        g.addVertex("0", 2);
        g.addVertex("1", 1);
        g.addVertex("2", 1);
        g.addVertex("3", 1);
        g.addVertex("4", 0);
        g.addEdge("0", "1", 1, "01");
        g.addEdge("0", "2", 10, "02");
        g.addEdge("1", "3", 2, "13");
        g.addEdge("2", "3", -10, "23");
        g.addEdge("3", "4", 3, "34");
        System.out.println(g.shortestDistance(g.getVertex("0"), g.getVertex("4")));
        for (Vertex v : g.shortestPath(g.getVertex("0"), g.getVertex("4"))) {
            System.out.print(v.getVName() + ";");
        }
        System.out.println();
    }
    public static void ExampleB() {
        Graph g = new Graph(5);
        g.addVertex("0", 1);
        g.addVertex("1", 1);
        g.addVertex("2", 2);
        g.addVertex("3", 1);
        g.addVertex("4", 1);
        g.addEdge("0", "2", 3, "02");
        g.addEdge("1", "0", 3, "10");
        g.addEdge("2", "3", 2, "23");
        g.addEdge("2", "4", 3, "24");
        g.addEdge("3", "1", 3, "31");
        g.addEdge("4", "3", 3, "43");
        g.listCycles();
        System.out.println(g.DFT(g.getVertex("0")));
    }
    /*
     * Expected output:
     * 3.0
     * 0;2;3;4;
     * 0-2-3-1-0
     * 0;2;3;1;4
     */
}