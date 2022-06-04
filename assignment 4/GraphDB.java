import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();

    public User addUser(String userName, int ID){
        //Test if user ID exists
        if (this.getUser(ID) != null) return this.getUser(ID);
        //Create new user
        User newUser = new User(userName, ID);
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
        
    }

    public User[][] clusterUsers(){
        
    }

    public Relationship[] minSpanningTree(){
        
    }

    public User[] getUsersAtDistance(User fromUser, int distance){
        
    }

}
