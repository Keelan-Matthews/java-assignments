import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // StudentExample();
        GraphDB db = new GraphDB();

        for (int add = 0; add < 11; add++)
        {
            db.addUser(String.valueOf((char) (65 + add)), add);
        }

        /* 1st set of relationships
        // Add relationships between A -> F, G, H, I, J
        for (int i = 5; i < 10; i++)
        {
            db.addFriendship(0, i, 10 + i);
        }

        // Add more relationships
        // J -> D
        db.addFriendship(9, 3, 10);
        db.addFriendship(2, 7, 10);
        db.addFriendship(3, 4, 10);
        db.addFriendship(3, 1, 10);

         */

        db.addFriendship(0, 7, 9);
        db.addFriendship(1, 8, 3);
        db.addFriendship(1, 2, 1);
        db.addFriendship(2, 4, 9);
        db.addFriendship(3, 5, 12);
        db.addFriendship(3, 4, 13);
        db.addFriendship(4, 6, 7);
        db.addFriendship(4, 8, 6);
        db.addFriendship(4, 9, 34);
        db.addFriendship(5, 7, 36);
        db.addFriendship(6, 9, 11);
        db.addFriendship(8, 9, 10);

        // Get users from distances 1 to 10
        for (int get = 1; get <= 10; get++)
        {
            Object[] userArr = null;
            if (db != null)
            {
                User u = db.getUser("A");

                if (u != null)
                    userArr = db.getUsersAtDistance(u, get);
            }

            if (userArr != null)
            {
                System.out.println("List of users at distance " + get);
                for (int print = 0; print < userArr.length; print++)
                {
                    System.out.println(userArr[print]);
                }
            }
        }

        // Graph colouring
        User[][] res = db.clusterUsers();

        if (res != null)
        {
            for (int i = 0; i < res.length; i++)
            {
                String temp = "";
                for (int j = 0; j < res[i].length; j++)
                {
                    temp += res[i][j].userName + " " + res[i][j].colour + ";";
                }
                System.out.println(temp);
            }
        }

        Object[] result = sort(db.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }

        // Second graph - identical with backwards IDs
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Second Graph:");

        db = new GraphDB();

        int id = 10;
        for (int add = 0; add < 10; add++)
        {
            db.addUser(String.valueOf((char) (65 + add)), id--);
        }

        db.addFriendship(10, 3, 19);
        db.addFriendship(9, 2, 3);
        db.addFriendship(9, 8, 1);
        db.addFriendship(8, 6, 120);
        db.addFriendship(7, 6, 32);
        db.addFriendship(7, 5, 31);
        db.addFriendship(5, 3, 1);
        db.addFriendship(4, 6, 12);
        db.addFriendship(2, 6, 17);
        db.addFriendship(2, 1, 0);
        db.addFriendship(4, 1, -10);
        db.addFriendship(6, 1, -1);

        // Get users from distances 1 to 10
        for (int get = 1; get <= 10; get++)
        {
            Object[] userArr = db.getUsersAtDistance(db.getUser("A"), get);
            System.out.println("List of users at distance " + get);
            for (int print = 0; print < userArr.length; print++)
            {
                System.out.println(userArr[print]);
            }
        }

        // Graph colouring
        res = db.clusterUsers();
        for(int i=0; i < res.length; i++)
        {
            String temp = "";
            for(int j=0; j < res[i].length; j++)
            {
                temp += res[i][j].userName + " " + res[i][j].colour + ";";
            }
            System.out.println(temp);
        }

        result = sort(db.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }

        // Test insertIntoArray a bit here
//        User[] testInsert = new User[20];
//        insertIntoArray(testInsert, new User("Shrek", 300));
//        insertIntoArray(testInsert, new User("donkey", 50));
//        insertIntoArray(testInsert, new User("Feona", 180));
//        insertIntoArray(testInsert, new User("Draak", 12000));
//        insertIntoArray(testInsert, new User("Gingerbread man", 5));
//        insertIntoArray(testInsert, new User("Farquad", 70));
//        insertIntoArray(testInsert, new User("Puss in boots", 7));
//        insertIntoArray(testInsert, new User("Sunflower", 1));
//        insertIntoArray(testInsert, new User("Three blind mice", 3));
//        insertIntoArray(testInsert, new User("Harold", 90));
//        insertIntoArray(testInsert, new User("Harold frog", 1));
//        insertIntoArray(testInsert, new User("Prince charming", 80));
//        insertIntoArray(testInsert, new User("Arthur", 60));
//        insertIntoArray(testInsert, new User("3 Piglets", 180));

        return;
    }

    @SuppressWarnings("unchecked")
    public static void StudentExample(){
        GraphDB graphDB = new GraphDB();
        String[] userNames = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        for(int i=0; i < userNames.length; i++){
            graphDB.addUser(userNames[i], i);
        }

        Tuple<String, String, Integer>[] arr = new Tuple[]{new Tuple<>("F", "G", 1),
                new Tuple<>("A", "B", 2),
                new Tuple<>("G", "C", 3),
                new Tuple<>("E", "B", 6),
                new Tuple<>("C", "F", 7),
                new Tuple<>("D", "E", 8),
                new Tuple<>("B", "F", 9),
                new Tuple<>("A", "D", 15),
                new Tuple<>("E", "F", 15),
                new Tuple<>("A", "C", 23)};

        for(Tuple<String, String, Integer> tuple: arr){
            graphDB.addFriendship(getUserId(tuple.t, graphDB), getUserId(tuple.u, graphDB), tuple.s);
        }

        Object[] result = sort(graphDB.minSpanningTree());
        for(Object relationship: result){
            System.out.println((Relationship)relationship);
        }

        User[][] res = graphDB.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }

        Object[] userArr = sort(graphDB.getUsersAtDistance(graphDB.getUser("A"), 2));
        for(Object user: userArr){
            System.out.println((User)user);
        }

        coloring();
    }

    public static int getUserId(String userName, GraphDB graphDB){
        return graphDB.getUser(userName).userID;
    }

    public static void coloring(){
        GraphDB graphDB = new GraphDB();
        String[] uName = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for(int i=0; i < uName.length; i++){
            graphDB.addUser(uName[i], i);
        }

        String[][] edges = new String[][]{{"a","e"}, {"a", "f"}, {"a", "g"}, {"b", "e"}, {"b", "c"}, {"b", "h"}, {"c", "g"}, {"d", "f"}, {"d", "g"}, {"f", "g"}, {"f", "h"}, {"g", "h"}};
        for(String[] edge : edges){
            graphDB.addFriendship(getUserId(edge[0], graphDB), getUserId(edge[1], graphDB), 0);
        }

        User[][] res = graphDB.clusterUsers();
        for(int i=0; i < res.length; i++){
            String temp = "";
            for(int j=0; j < res[i].length; j++){
                temp += res[i][j].toString() + ";";
            }
            System.out.println(temp);
        }
    }

    private static <T> Object[] sort(T[] sort){
        ArrayList<T> result = new ArrayList<>();
        for(T relationship: sort){
            result.add(relationship);
        }

        ArrayList<T> temp = new ArrayList<>();
        while(!result.isEmpty()){
            int maxVal = Integer.MIN_VALUE;
            T maxRelationship = result.get(0);
            for(T relationship: result){
                if(relationship.toString().hashCode() > maxVal){
                    maxVal = relationship.toString().hashCode();
                    maxRelationship = relationship;
                }
            }
            temp.add(maxRelationship);
            result.remove(maxRelationship);
        }

        return temp.toArray();
    }
}

class Tuple<T,U,S>{
    public T t;
    public U u;
    public S s;

    public Tuple(T t, U u, S s){
        this.s = s;
        this.t = t;
        this.u = u;
    }
}

//    List of users at distance 1
//        H[7]
//        List of users at distance 2
//        F[5]
//        List of users at distance 3
//        D[3]
//        List of users at distance 4
//        E[4]
//        List of users at distance 5
//        C[2]
//        G[6]
//        I[8]
//        J[9]
//        List of users at distance 6
//        B[1]
//        J[9]
//        List of users at distance 7
//        I[8]
//        List of users at distance 8
//        J[9]
//        List of users at distance 9
//        G[6]
//        List of users at distance 10
//        A 1;B 1;E 1;F 1;K 1;
//        C 2;D 2;G 2;H 2;I 2;
//        J 3;
//        E[4]-(6.0)->I[8]
//        E[4]-(7.0)->G[6]
//        B[1]-(1.0)->C[2]
//        I[8]-(10.0)->J[9]
//        B[1]-(3.0)->I[8]
//        A[0]-(9.0)->H[7]
//        F[5]-(36.0)->H[7]
//        D[3]-(12.0)->F[5]
//        D[3]-(13.0)->E[4]
//        ------------------------------------------------------------------------------
//        Second Graph:
//        List of users at distance 1
//        H[3]
//        List of users at distance 2
//        F[5]
//        List of users at distance 3
//        D[7]
//        List of users at distance 4
//        E[6]
//        List of users at distance 5
//        C[8]
//        G[4]
//        I[2]
//        J[1]
//        List of users at distance 6
//        B[9]
//        J[1]
//        List of users at distance 7
//        I[2]
//        List of users at distance 8
//        J[1]
//        List of users at distance 9
//        G[4]
//        List of users at distance 10
//        F 1;E 1;B 1;A 1;
//        I 2;H 2;G 2;D 2;C 2;
//        J 3;
//        J[1]-(0.0)->I[2]
//        F[5]-(31.0)->D[7]
//        E[6]-(32.0)->D[7]
//        C[8]-(1.0)->B[9]
//        H[3]-(1.0)->F[5]
//        I[2]-(3.0)->B[9]
//        H[3]-(19.0)->A[10]
//        J[1]-(-10.0)->G[4]
//        J[1]-(-1.0)->E[6]