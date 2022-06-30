public class Main{
    public static void main(String[] args){
        BST<Integer> mytree = new BST<Integer>();
        System.out.println("====== CONTAINS ======");
        System.out.println("=== 5 (F): " + mytree.contains(5));
        System.out.println("====== FIND ======");
        System.out.println("=== 6 (null): " + mytree.find(6));
        mytree.insert(7);//7 should be root
        mytree.insert(3);
        mytree.insert(9);
        mytree.insert(1);
        mytree.insert(5);
        mytree.insert(8);
        mytree.insert(15);
        mytree.insert(13);
        mytree.insert(20);

        System.out.println("====== PRINTING MY TREE ======");
        System.out.println("====== ================ ======");
        System.out.println("=== Number of Nodes: "+ mytree.numNodes());
        System.out.println("--- Min Val: "+ mytree.minVal());
        System.out.println("--- Max Val: "+ mytree.maxVal());
        System.out.println("--- Height: " + mytree.height());
        System.out.println("=== Printing Tree Hirearchy : ");
        System.out.println("Array: ");
        Object[] obj;
        obj = mytree.toArray();
        int i = 0;
        for(; i< 9; i++)
            System.out.println(obj[i].toString());
        System.out.println("====== ================ ======");
        System.out.println("====== ================ ======");
        System.out.println("DFT: " + mytree.DFT());
        System.out.println("====== ================ ======");
        System.out.println("BFT: " + mytree.BFT());
        System.out.println("====== ================ ======");
        System.out.println("====== ================ ======");
        System.out.println("====== Nodes at Height 3: ======");
        Object [] ob3 = mytree.nodesOnHeight(3);

        for (int j = 0; j < ob3.length; j++)
            System.out.println(ob3[j].toString());
        System.out.println("====== Nodes at Height 2: ======");
        Object [] ob2 = mytree.nodesOnHeight(2);

        for (int j = 0; j < ob2.length; j++)
            System.out.println(ob2[j].toString());
        System.out.println("====== Nodes at Height 1: ======");
        Object [] ob1 = mytree.nodesOnHeight(1);

        for (int j = 0; j < ob1.length; j++)
            System.out.println(ob1[j].toString());
        System.out.println("====== Nodes at Height 0: ======");
        Object [] ob = mytree.nodesOnHeight(0);

        for (int j = 0; j < ob.length; j++)
            System.out.println(ob[j].toString());
        System.out.println("====== ================ ======");
        System.out.println("====== ================ ======");
        System.out.println("====== CONTAINS ======");
        System.out.println("=== 5 (T): " + mytree.contains(5));
        System.out.println("=== 16 (F): " + mytree.contains(16));
        System.out.println("=== 9 (T): " + mytree.contains(9));
        System.out.println("====== ================ ======");
        System.out.println("====== FIND ======");
        System.out.println("=== 6 (null): " + mytree.find(6));
        System.out.println("=== 13: " + mytree.find(13));
        System.out.println("=== 7: " + mytree.find(7));
    }
}
