
/**
 * Name: Keelan Matthews
 * Student Number: 21549967
 */



public class OrganisingList {

    public ListNode root = null;
    public OrganisingList() {}

    public static int sumRec(ListNode node) {
        if (node.next == null)
            return node.key;
        else
            return (sumRec(node.next) + node.key);
    }

    public static int dataRec(ListNode node) {
        if (node.next == null) {
            node.data = node.key;
            return node.key;
        }
        else {
            node.data = (node.key * sumRec(node)) - dataRec(node.next);
            return node.data;
        }
    }

    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    public Integer getData(Integer key) {
        ListNode nextNode = root;
        Integer data;

        if(root == null) {
            return null;
        }
        else {
            int pos = 0;
            while (nextNode != null && !nextNode.key.equals(key)) {
                nextNode = nextNode.next;
                pos++;
            }
            if (nextNode.key.equals(key)) {
                data = nextNode.data;

                if (pos == 0) {
                    calculateData();
                    return data;
                }

                //Swap with predecessor
                ListNode prevNode = root;
                ListNode currNode = root;

                for(int i = 0; i <= pos-2; i++)
                    currNode = currNode.next;

                for(int i = 0; i <= pos-3; i++)
                    prevNode = prevNode.next;

                prevNode.next = nextNode;
                currNode.next = nextNode.next;
                nextNode.next = currNode;


                calculateData();
                return data;
            }
            return null;
        }
    }

    public void delete(Integer key) {
        if (root == null) return;

        ListNode prev = null;
        ListNode curr = root;

        //Delete at the start
        if (root.key.equals(key)) {
            root = root.next;
            return;
        }

        while (curr.next != null && !curr.key.equals(key)) {
            prev = curr;
            curr = curr.next;
        }

        if (!curr.key.equals(key)) return;

        //Delete at the end
        if (curr.next == null)
            curr = null;
        else
            prev.next = curr.next; //Delete in the middle

        calculateData();
    }

    public void insert(Integer key) {
        ListNode newNode = new ListNode(key);
        newNode.next = null;
        ListNode nextNode = root;

        if(root == null) {
            root = newNode;
        }
        else {
            while (nextNode.next != null && !nextNode.key.equals(key)) {
                nextNode = nextNode.next;
            }

            if (!nextNode.key.equals(key)) {
                nextNode.next = newNode;
                calculateData();
            }
        }
    }

    public Boolean contains(Integer key) {
        if (root == null) return false;
        else {
            ListNode nextNode = root;
            while (nextNode != null) {
                if (nextNode.key.equals(key)) return true;
                nextNode = nextNode.next;
            }
            return false;
        }
    }

    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }

}