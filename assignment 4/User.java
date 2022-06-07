import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>();
    int colour = -1;
    int index;
    int saturationDeg = 0;
    int uncolouredDeg = 0;
    boolean processed = false;

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
    
    public User(String userName, int userID, int i){
        this.userName = userName;
        this.userID = userID;
        this.index = i;
    }

    public Relationship[] getFriends(){
        return friends.toArray(new Relationship[0]);
    }

    public Relationship addFriend(User friend, double friendshipValue){
        if (friend == null) return null;

        //Create new relationship
        Relationship newRelationshipA = new Relationship(this, friend, friendshipValue);
        Relationship newRelationshipB = new Relationship(friend, this, friendshipValue);

        //Test if relationship exists
        Relationship curr[] = this.getFriends();

        for (Relationship relationship : curr)
            if (relationship.equals(newRelationshipA)) return relationship;

        //Add relationship to both users
        this.addFriend(newRelationshipA);
        friend.addFriend(newRelationshipB);

        //Increase degree
        this.uncolouredDeg++;
        friend.uncolouredDeg++;

        return newRelationshipA;
    }

    public void addFriend(Relationship relationship){
        this.friends.add(relationship);
    }
}
