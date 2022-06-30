public class Main {
    public static void main(String[] args) {
        System.out.println("===== CREATING MY TREE =====");
        System.out.println("= Some of these are duplicate values.");
        System.out.println("= If something funny happens to your tree, check you aren't inserting duplicate values");
        SplayTree<Integer> mytree = new SplayTree<>();
        mytree.insert(8);
        mytree.insert(6);
        mytree.insert(11);
        mytree.insert(3);
        mytree.insert(7);
        mytree.insert(10);
        mytree.insert(15);
        mytree.insert(2);
        mytree.insert(4);
        mytree.insert(12);
        mytree.insert(15);
        mytree.insert(25);
        mytree.insert(2);
        mytree.insert(5);
        mytree.insert(14);
        System.out.println("= mytree should look like: ");
        System.out.println("            8           ");
        System.out.println("          x   x         ");
        System.out.println("        6      11       ");
        System.out.println("      x   x   x   x     ");
        System.out.println("    3      7 10    15   ");
        System.out.println("  x   x          x   x  ");
        System.out.println(" 2     4       12     25");
        System.out.println("        x        x      ");
        System.out.println("         5        14    ");
        System.out.println("=============================");
        System.out.println("mytree looks like (inorder): ");
        str = "";//reset str
        inorder(mytree.root);//change str value with helper
        str = str.substring(0, str.length()-1);
        System.out.println(str);
        System.out.println("=========================");
        System.out.println("=========================");
        System.out.println("root = "+ mytree.root.key);
        System.out.println("===== FINDING NODES =====");
        System.out.println("Find 5 (true): "+ mytree.contains(5));
        System.out.println("Find 25 (true): "+ mytree.contains(25));
        System.out.println("Find 8 (true): "+ mytree.contains(8));
        System.out.println("Find 17 (false): "+ mytree.contains(17));
        System.out.println("Find 100 (false): "+ mytree.contains(100));
        System.out.println("Find 1 (false): "+ mytree.contains(1));
        System.out.println("================================");
        System.out.println("===== FINDING PREDECESSORS =====");
        System.out.println("A predecessor of a given key is defined as the largest key in the tree which is smaller than the given key");
        System.out.println("If you are getting the wrong values, you are finding the parent, not the predecessor");
        System.out.println("Find 1 predecessor (null): "+ mytree.findPredecessor(1));
        System.out.println("Find 2 predecessor (null): "+ mytree.findPredecessor(2));
        System.out.println("Find 3 predecessor (2): "+ mytree.findPredecessor(3));
        System.out.println("Find 4 predecessor (3): "+ mytree.findPredecessor(4));
        System.out.println("Find 5 predecessor (4): "+ mytree.findPredecessor(5));
        System.out.println("Find 6 predecessor (5): "+ mytree.findPredecessor(6));
        System.out.println("Find 7 predecessor (6): "+ mytree.findPredecessor(7));
        System.out.println("Find 8 predecessor (7): "+ mytree.findPredecessor(8));
        System.out.println("Find 10 predecessor (8): "+ mytree.findPredecessor(10));
        System.out.println("Find 11 predecessor (10): "+ mytree.findPredecessor(11));
        System.out.println("Find 12 predecessor (11): "+ mytree.findPredecessor(12));
        System.out.println("Find 14 predecessor (12): "+ mytree.findPredecessor(14));
        System.out.println("Find 15 predecessor (14): "+ mytree.findPredecessor(15));
        System.out.println("Find 25 predecessor (15): "+ mytree.findPredecessor(25));
        System.out.println("Find 100 predecessor (null): "+ mytree.findPredecessor(100));
        System.out.println("Find 17 predecessor (null): "+ mytree.findPredecessor(17));
        System.out.println("Find 200 predecessor (null): "+ mytree.findPredecessor(200));
        System.out.println("===========================");
        System.out.println("===== ACCESSING NODES =====");
        mytree.access(100);
        mytree.access(8);
        mytree.access(5);
        mytree.access(8);
        mytree.access(14);
        mytree.access(6);
        mytree.access(6);
        mytree.access(12);
        System.out.println("=== The order of access is: 100,8,5,8,14,6,6,12");
        System.out.println("= mytree should look like: ");
        System.out.println("                  12            ");
        System.out.println("                 x  x           ");
        System.out.println("               6     14         ");
        System.out.println("             x  x      x        ");
        System.out.println("           5     11     15      ");
        System.out.println("         x      x         x     ");
        System.out.println("       4       8           25   ");
        System.out.println("     x       x   x           x  ");
        System.out.println("   3       7      10         100");
        System.out.println(" x                              ");
        System.out.println("2                               ");
        System.out.println("===============================");
        System.out.println("mytree looks like (inorder): ");
        str = "";//reset str
        inorder(mytree.root);//change str value with helper
        str = str.substring(0, str.length()-1);
        System.out.println(str);
        System.out.println("=========================");
        System.out.println("================================");
        System.out.println("===== FINDING PREDECESSORS =====");
        System.out.println("Find 1 predecessor (null): "+ mytree.findPredecessor(1));
        System.out.println("Find 2 predecessor (null): "+ mytree.findPredecessor(2));
        System.out.println("Find 3 predecessor (2): "+ mytree.findPredecessor(3));
        System.out.println("Find 4 predecessor (3): "+ mytree.findPredecessor(4));
        System.out.println("Find 5 predecessor (4): "+ mytree.findPredecessor(5));
        System.out.println("Find 6 predecessor (5): "+ mytree.findPredecessor(6));
        System.out.println("Find 7 predecessor (6): "+ mytree.findPredecessor(7));
        System.out.println("Find 8 predecessor (7): "+ mytree.findPredecessor(8));
        System.out.println("Find 10 predecessor (8): "+ mytree.findPredecessor(10));
        System.out.println("Find 11 predecessor (10): "+ mytree.findPredecessor(11));
        System.out.println("Find 12 predecessor (11): "+ mytree.findPredecessor(12));
        System.out.println("Find 14 predecessor (12): "+ mytree.findPredecessor(14));
        System.out.println("Find 15 predecessor (14): "+ mytree.findPredecessor(15));
        System.out.println("Find 25 predecessor (15): "+ mytree.findPredecessor(25));
        System.out.println("Find 100 predecessor (25): "+ mytree.findPredecessor(100));
        System.out.println("Find 17 predecessor (null): "+ mytree.findPredecessor(17));
        System.out.println("Find 200 predecessor (null): "+ mytree.findPredecessor(200));
        System.out.println("===========================");
        System.out.println("======= All Done! =======");
    }
    public static String str = "";
    public static void inorder(TreeNode<Integer> n){
        if(n != null){
            inorder(n.left);
            str += n.toString() + ";";
            inorder(n.right);
        }
    }
}
