import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();
    private int vIndex = 0;

    public User addUser(String userName, int ID){
        //Test if user ID exists
        if (this.getUser(ID) != null) return this.getUser(ID);
        //Create new user
        User newUser = new User(userName, ID, vIndex);
        vIndex++;
        //Add user to graph
        users.add(newUser);
        return newUser;
    }

    public User getUser(int userID){
        User[] userArray = users.toArray(new User[0]);

        for (User currUser : userArray)
            if (currUser.userID == userID) return currUser;

        return null;
    }

    public User getUser(String userName){
        User[] userArray = users.toArray(new User[0]);

        for (User currUser : userArray)
            if (currUser.userName.equals(userName)) return currUser;

        return null;
    }

    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue){

        //Get users
        User friendA = getUser(frienteeID);
        User friendB = getUser(friendedID);

        if (friendA == null || friendB == null) return null; //user does not exist

        //Create new relationship
        Relationship newRelationship = new Relationship(friendA, friendB, relationshipValue);

        //Test if relationship exists
        for (Relationship relationshipA : friendA.friends)
            if (relationshipA.equals(newRelationship)) return relationshipA;

        //Add relationship
        friendA.addFriend(friendB, relationshipValue);

        return newRelationship;
    }

    public User[][] clusterUsers(){
        //RESET USER SATURATION AND DEGREE BEFORE STARTING
        for (User u : users) {
            u.processed = false;
            u.saturationDeg = 0;
            u.uncolouredDeg = u.friends.size();
        }

        int[] colours = {0,1,2,3,4,5,6,7,8};
        User v = null;
        int highestColour = 0; //This will be the column count of the 2D array

        /* This will loop through each vertex and assign it a colour based on the
        * Brelaz algorithm. The colours will then be used as indices in the user
        * array to group them together.
        */
        for (int i = 0; i < users.size(); i++) {
            //Find first vertex not processed for initial value
            for (User u : users)
                if (!u.processed) {
                    v = u;
                    break;
                }

            //Find next v to process
            for (User u : users)
                if (!u.processed) {//if it has not been processed
                    if (u.saturationDeg > v.saturationDeg) //and it has a higher saturation degree
                        v = u;
                    else if (u.saturationDeg == v.saturationDeg) //else if they are tied
                        v = (u.uncolouredDeg > v.uncolouredDeg) ? u : v; //one with higher uncoloured degree
                }

            v.processed = true;

            //Find smallest colour
            Relationship[] friends = v.getFriends();
            int j = 0; //index of the smallest colour

            ArrayList<Integer> seenColours = new ArrayList<>();
            for (Relationship friend : friends)
                seenColours.add(friend.friendB.colour); //get colours around v

            //Find the highest colour
            boolean done = false;

            while (!done) {
                done = true;
                for (Integer c : seenColours)
                    if (c == j) {
                        j++;
                        done = false;
                        break;
                    }
            }

            //Update highest colour
            if (highestColour < j) highestColour = j;

            //Assign colour to vertex
            v.colour = colours[j];

            //Update all adjacent vertices
            for (Relationship friend : friends) {
                boolean update = true; //as soon as a matching colour is found, make false
                User friendB = friend.friendB;

                //get friends of adjacent vertices
                Relationship[] adjFriends = friend.friendB.getFriends();

                //Visit vertices adjacent to friend B other than currently processed and see if they have colour[j]
                for (Relationship adjFriend : adjFriends)
                    if (adjFriend.friendB != v && adjFriend.friendB.colour == colours[j]) {
                        update = false;
                        break;
                    }

                //If not, update saturation degree
                if (update) friendB.saturationDeg++;

                //Update uncoloured degree
                friendB.uncolouredDeg--;
            }
        }

        //Find most common colour to be the row size of the 2D array
        int[] rowSizes = rowSize(highestColour);

        //Create array cluster
        User[][] cluster = new User[highestColour+1][];

        //Populate row sizes
        for (int i = 0; i <= highestColour; i++)
            cluster[i] = new User[rowSizes[i]];

        //Sort users by ID
        User[] sorted = countingSort(users);

        //Populate array cluster
        for (User u : sorted) {
            int row = 0;
            if (u != null) {
                while (cluster[u.colour][row] != null) row++; //find first row in current colour that is empty
                cluster[u.colour][row] = u;
            }
        }

        return cluster;
    }

    public Relationship[] minSpanningTree(){
        ArrayList<Relationship> mst = new ArrayList<>();
        int e = 0; //index variable for result
        int i = 0; //index variable for sorted edges

        //Sort edges
        Relationship[] sorted = edgeSort();

        subset[] subsets = new subset[users.size()];
        for (int s = 0; s < users.size(); ++s)
            subsets[s] = new subset();

        for (int v = 0; v < users.size(); ++v)
        {
            subsets[v].parent = users.get(v).index;
            subsets[v].rank = 0;
        }

        while (e < users.size() - 1) {
            //Find smallest edge
            Relationship edge = sorted[i++];

            int x = find(subsets, edge.friendA.index);
            int y = find(subsets, edge.friendB.index);

            if (x != y) {
                mst.add(edge);
                e++;
                union(subsets, x, y);
            }
        }

        return mst.toArray(new Relationship[0]);
    }

    ArrayList<User> userDistance = new ArrayList<>();
    public User[] getUsersAtDistance(User fromUser, int distance){
        if (distance == 0) return new User[]{fromUser};

        for (User u : users)
            u.processed = false;

        traverse(fromUser, distance);
        return userDistance.toArray(new User[0]);
    }

    /* This function finds the colour out of all the vertices that is
    * the most common. This number will be the highest and will be suitable
    * for the row count in the 2D array
     */
    private int[] rowSize(int highest) {
        int[] colourCount = new int[highest+1];

        for (int i = 0; i < highest+1; i++)
            colourCount[i] = 0;

        for (User currUser : users)
            colourCount[currUser.colour]++;

        return colourCount;
    }
    public User[] countingSort(ArrayList<User> u)
    {
        int length = u.size();
        User[] sorted = new User[length+1];

        int max = u.get(0).index;
        for (int i = 1; i < length; i++)
            if (u.get(i).index > max)
                max = u.get(i).index;

        int[] count = new int[max+1];
        for (int i = 0; i < max; ++i) count[i] = 0;

        for (User datum : u) count[datum.index]++;

        for (int i = 1; i <= max; i++)
            count[i] += count[i-1];

        for (int i = length-1; i >= 0; i--) {
            sorted[count[u.get(i).index] - 1] = u.get(i);
            count[u.get(i).index]--;
        }

        return sorted;
    }
    static class subset
    {
        int parent, rank;
    }
    private int find(subset[] subsets, int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }
    private void union(subset[] subsets, int x, int y)
    {
        int xNode = find(subsets, x);
        int yNode = find(subsets, y);

        if (subsets[xNode].rank < subsets[yNode].rank)
            subsets[xNode].parent = yNode;
        else if (subsets[xNode].rank > subsets[yNode].rank)
            subsets[yNode].parent = xNode;
        else {
            subsets[yNode].parent = xNode;
            subsets[xNode].rank++;
        }
    }
    private Relationship[] edgeSort() {
        ArrayList<Relationship> unsorted = new ArrayList<>();

        //Populate array with all edges
        for (User u : users) {
            Relationship[] curr = u.getFriends();

            for (Relationship relationship : curr) {
                boolean add = true;
                //Check if exists
                for (Relationship r : unsorted)
                    if (r.equals(relationship)) {
                        add = false;
                        break;
                    }

                if (add) unsorted.add(relationship);
            }
        }

        Relationship[] sorted = unsorted.toArray(new Relationship[0]);

        int n = sorted.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (sorted[j].friendshipValue > sorted[j + 1].friendshipValue) {
                    Relationship temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }

        return sorted;
    }
    private void traverse(User u, int distance) {
        u.processed = true;
        if (distance == 0) {
            for (User p : userDistance)
                if (p == u) return;
            userDistance.add(u);
        }
        else {
            for (Relationship r : u.getFriends())
                if (!r.friendB.processed)
                    traverse(r.friendB, distance-1);
        }
    }
}
