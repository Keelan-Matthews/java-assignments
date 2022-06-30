public class Main {
    public static void main(String[] args) 
    {
	BTree<Integer> hive = new BTree<Integer>(2); // A B-Tree with order 4 (2*m)
	System.out.println("=== My super hive ===");
	System.out.println("Inserting bees...");
	hive.insert(84);
	hive.insert(64);
	hive.insert(69);
	hive.insert(26);
	hive.insert(44);
	hive.insert(8);
	hive.insert(6);
	hive.insert(63);
	hive.insert(70);
	hive.insert(30);
	hive.insert(2);
	hive.insert(45);
	hive.insert(39);
	hive.insert(29);
	hive.insert(20);
	hive.insert(53);
	hive.insert(10);
	hive.insert(11);
	hive.insert(71);
	hive.insert(75);
	hive.insert(77);

	
	
	System.out.println("========== Print hive: ");
	hive.print();
	System.out.println("========== Traverse hive: ");
	hive.traverse();

	// System.out.println("Search the constucted tree for 80: ");
    // 	BTreeNode result = hive.search(80);
	// if (result != null)
	// 	System.out.println("Found in node " + result);
	// else
	// 	System.out.println("Not found!");
	// System.out.println("Search the constucted tree for 100: ");
    // 	result = hive.search(100);
	// if (result != null)
	// 	System.out.println("Found in node " + result);
	// else
	// 	System.out.println("Not found!");

	// System.out.println("Traversal of the constucted tree is : ");
	// 	hive.traverse();
	// System.out.println("Structure of the constucted tree is : ");
	// 	hive.print();

	/* Expected Output:
	Search the constucted tree for 80:
	Found in node [70,80,90]
	Search the constucted tree for 100:
	Not found!
	Traversal of the constucted tree is :
	 10 20 30 40 50 60 70 80 90
	Structure of the constucted tree is :
	Level 1 [ 40]
	Level 2 [ 20]
	Level 3 [ 10]
	Level 3 [ 30]
	Level 2 [ 60]
	Level 3 [ 50]
	Level 3 [ 70 80 90]
	*/
    }


    
}