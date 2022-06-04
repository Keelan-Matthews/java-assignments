import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>();

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
    
    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
    }

    public Relationship[] getFriends(){
        return friends.toArray(new Relationship[0]);
    }

    public Relationship addFriend(User friend, double friendshipValue){
        if (friend == null) return null;

        //Create new relationship
        Relationship newRelationship = new Relationship(this, friend, friendshipValue);

        //Test if relationship exists
        Relationship curr[] = this.getFriends();

        for (Relationship relationship : curr)
            if (relationship.equals(newRelationship)) return relationship;

        //Add relationship to both users
        this.addFriend(newRelationship);
        friend.addFriend(newRelationship);

        return newRelationship;
    }

    public void addFriend(Relationship relationship){
        this.friends.add(relationship);
    }
}
