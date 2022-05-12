public class Main
{
    //------------------------------------------------------------------------[main]
    public static void main( String[] args )
    {
        avlTest();
    }

    //---------------------------------------------------------------------[avlTest]
    private static void avlTest()
    {
        ThreadedAvlTree<Integer> tree = new ThreadedAvlTree<>();
        tree.convertAVLtoThreaded(null);
        test_1( tree );

        AvlTree<Integer> normalTree = new AvlTree<>();
        test_2( normalTree );

        ThreadedAvlTree<Integer> tree2 = new ThreadedAvlTree<>();
        tree2.convertAVLtoThreaded(normalTree.root);
        test_3( tree2 );
    }

    //----------------------------------------------------------------------[test_1]
    private static void test_1( ThreadedAvlTree<Integer> tree )
    {
        avlInsert_1( tree );
        alvRemove_1( tree );
        System.out.println("Height of the Threaded AVL Tree: " + tree.getHeight(tree.root)); // Height: 2
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printBST( tree.root );

        // EXPECTED TREE - PRINTED SIDEWAYS (FROM LEFT TO RIGHT)
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //         [15]{0}
        // [8]{2}
        //                 [7]{0}
        //         [5]{1}
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    //----------------------------------------------------------------------[test_2]
    private static void test_2( AvlTree<Integer> tree )
    {
        avlInsert_2( tree );
        alvRemove_2( tree );
        System.out.println("Height of the AVL Tree: " + tree.getHeight(tree.root)); // Height: 2
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printBST( tree.root );

        // EXPECTED TREE - PRINTED SIDEWAYS (FROM LEFT TO RIGHT)
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //                 [24]{0}
        //         [16]{1}
        //                 [11]{0}
        // [4]{2}
        //         [3]{1}
        //                 [2]{0}
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    private static void test_3( ThreadedAvlTree<Integer> tree )
    {
        avlCompare_1( tree );
        avlInsert_3( tree );
        alvRemove_3( tree );
        System.out.println("Height of the Threaded AVL Tree: " + tree.getHeight(tree.root)); // Height: 2
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printBST( tree.root );

        // EXPECTED TREE - PRINTED SIDEWAYS (FROM LEFT TO RIGHT)
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //                 [99]{0}
        //         [85]{1}
        // [42]{2}
        //                 [11]{0}
        //         [3]{1}
        //                 [2]{0}
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        System.out.println("--------- CONGRATS!! You've reached completetion!!! :D ---------");
    }


    //-----------------------------------------------------------------[avlCompare_1]
    private static void avlCompare_1( ThreadedAvlTree<Integer> tree )
    {
        testStart( "Comparing the AVL tree to the Threaded AVL tree" );

        assertEquals( tree.root.data, 4 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.right.data, 4 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.right.data, 16 );
        assertEquals( tree.root.right.left.data, 11 );
        assertEquals( tree.root.right.left.right.data, 16 );
        assertEquals( tree.root.right.right.data, 24 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlInsert_1]
    private static void avlInsert_1( ThreadedAvlTree<Integer> tree )
    {
        testStart( "Inserting in Threaded AVL tree 1" );

        tree.root = tree.insert( tree.root, 5 );
        tree.root = tree.insert( tree.root, 8 );
        tree.root = tree.insert( tree.root, 2 );
        tree.root = tree.insert( tree.root, 15 );
        tree.root = tree.insert( tree.root, 25 );
        tree.root = tree.insert( tree.root, 4 );
        tree.root = tree.insert( tree.root, 16 );
        tree.root = tree.insert( tree.root, 7 );

        assertEquals( tree.root.data, 5 );
        assertEquals( tree.root.left.data, 2 );
        assertEquals( tree.root.left.right.data, 4 );
        assertEquals( tree.root.left.right.right.data, 5 );
        assertEquals( tree.root.right.data, 15 );
        assertEquals( tree.root.right.left.data, 8 );
        assertEquals( tree.root.right.left.right.data, 15 );
        assertEquals( tree.root.right.left.left.data, 7 );
        assertEquals( tree.root.right.left.left.right.data, 8 );
        assertEquals( tree.root.right.right.data, 25 );
        assertEquals( tree.root.right.right.left.data, 16 );        
        assertEquals( tree.root.right.right.left.right.data, 25 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlInsert_2]
    private static void avlInsert_2( AvlTree<Integer> tree )
    {
        testStart( "Inserting in an AVL tree" );

        tree.root = tree.insert( tree.root, 8 );
        tree.root = tree.insert( tree.root, 4 );
        tree.root = tree.insert( tree.root, 9 );
        tree.root = tree.insert( tree.root, 11 );
        tree.root = tree.insert( tree.root, 24 );
        tree.root = tree.insert( tree.root, 6 );
        tree.root = tree.insert( tree.root, 1 );
        tree.root = tree.insert( tree.root, 2 );
        tree.root = tree.insert( tree.root, 16 );
        tree.root = tree.insert( tree.root, 3 );
        tree.root = tree.insert( tree.root, 34 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 4 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.left.data, 1 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.left.right.data, 6 );
        assertEquals( tree.root.right.data, 11 );
        assertEquals( tree.root.right.right.data, 24 );
        assertEquals( tree.root.right.right.right.data, 34 );
        assertEquals( tree.root.right.right.left.data, 16 );
        assertEquals( tree.root.right.left.data, 9 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlInsert_3]
    private static void avlInsert_3( ThreadedAvlTree<Integer> tree )
    {
        testStart( "Inserting in Threaded AVL tree 2" );

        tree.root = tree.insert( tree.root, 85 );
        tree.root = tree.insert( tree.root, 42 );
        tree.root = tree.insert( tree.root, 99 );
        tree.root = tree.insert( tree.root, 16 );
        tree.root = tree.insert( tree.root, 24 );

        assertEquals( tree.root.data, 4 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.right.data, 4 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.right.data, 42 );
        assertEquals( tree.root.right.right.data, 85 );
        assertEquals( tree.root.right.right.right.data, 99 );
        assertEquals( tree.root.right.left.data, 16 );
        assertEquals( tree.root.right.left.left.data, 11 );
        assertEquals( tree.root.right.left.left.right.data, 16 );
        assertEquals( tree.root.right.left.right.data, 24 );
        assertEquals( tree.root.right.left.right.right.data, 42 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlRemove_1]
    private static void alvRemove_1( ThreadedAvlTree<Integer> tree )
    {
        testStart( "Left-Right: Removing '4'" );

        tree.root = tree.removeNode( tree.root, 4 );

        assertEquals( tree.root.data, 15 );
        assertEquals( tree.root.left.data, 5 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 5 );
        assertEquals( tree.root.left.right.data, 8 );
        assertEquals( tree.root.left.right.right.data, 15 );
        assertEquals( tree.root.left.right.left.data, 7 );
        assertEquals( tree.root.left.right.left.right.data, 8 );
        assertEquals( tree.root.right.data, 25 );
        assertEquals( tree.root.right.left.data, 16 );
        assertEquals( tree.root.right.left.right.data, 25 );

        testEnd();

        testStart( "Right-Left: Removing '16'" );

        tree.root = tree.removeNode( tree.root, 16 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 5 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 5 );
        assertEquals( tree.root.left.right.data, 7 );
        assertEquals( tree.root.left.right.right.data, 8 );
        assertEquals( tree.root.right.data, 15 );
        assertEquals( tree.root.right.right.data, 25 );

        testEnd();

        testStart( "Left-Left: Removing '2'" );

        tree.root = tree.removeNode( tree.root, 2 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 5 );
        assertEquals( tree.root.left.right.data, 7 );
        assertEquals( tree.root.left.right.right.data, 8 );
        assertEquals( tree.root.right.data, 15 );
        assertEquals( tree.root.right.right.data, 25 );

        testEnd();

        testStart( "Right-Right: Removing '25'" );

        tree.root = tree.removeNode( tree.root, 25 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 5 );
        assertEquals( tree.root.left.right.data, 7 );
        assertEquals( tree.root.left.right.right.data, 8 );
        assertEquals( tree.root.right.data, 15 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlRemove_2]
    private static void alvRemove_2( AvlTree<Integer> tree )
    {
        testStart( "Right-Left: Removing '9'" );

        tree.root = tree.removeNode( tree.root, 9 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.right.data, 24 );
        assertEquals( tree.root.right.left.data, 11 );
        assertEquals( tree.root.right.left.right.data, 16 );
        assertEquals( tree.root.right.right.data, 34 );

        testEnd();

        testStart( "Left-Right: Removing '6'" );

        tree.root = tree.removeNode( tree.root, 6 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 2 );
        assertEquals( tree.root.left.left.data, 1 );
        assertEquals( tree.root.left.right.data, 4 );
        assertEquals( tree.root.left.right.left.data, 3 );

        testEnd();

        testStart( "Left-Left: Removing '1'" );

        tree.root = tree.removeNode( tree.root, 1 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.right.data, 4 );

        testEnd();

        testStart( "Right-Right: Removing '34'" );

        tree.root = tree.removeNode( tree.root, 34 );

        assertEquals( tree.root.data, 8 );
        assertEquals( tree.root.right.data, 16 );
        assertEquals( tree.root.right.left.data, 11 );
        assertEquals( tree.root.right.right.data, 24 );

        testEnd();

        testStart( "Root: Removing '8'" );

        tree.root = tree.removeNode( tree.root, 8 );

        assertEquals( tree.root.data, 4 );
        assertEquals( tree.root.right.data, 16 );
        assertEquals( tree.root.right.right.data, 24 );

        testEnd();
    }

    //-----------------------------------------------------------------[avlRemove_3]
    private static void alvRemove_3( ThreadedAvlTree<Integer> tree )
    {
        testStart( "Right-Left: Removing '4'" );

        tree.root = tree.removeNode( tree.root, 4 );

        assertEquals( tree.root.data, 42 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.left.right.data, 16 );
        assertEquals( tree.root.left.right.left.data, 11 );
        assertEquals( tree.root.left.right.left.right.data, 16 );
        assertEquals( tree.root.left.right.right.data, 24 );
        assertEquals( tree.root.left.right.right.right.data, 42 );

        testEnd();

        testStart( "Left-Right: Removing '16'" );

        tree.root = tree.removeNode( tree.root, 16 );

        assertEquals( tree.root.data, 42 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.left.right.data, 11 );
        assertEquals( tree.root.left.right.right.data, 24 );
        assertEquals( tree.root.left.right.right.right.data, 42 );
        assertEquals( tree.root.right.data, 85 );
        assertEquals( tree.root.right.right.data, 99 );

        testEnd();

        testStart( "Left-Left: Removing '24'" );

        tree.root = tree.removeNode( tree.root, 24 );

        assertEquals( tree.root.data, 42 );
        assertEquals( tree.root.left.data, 3 );
        assertEquals( tree.root.left.left.data, 2 );
        assertEquals( tree.root.left.left.right.data, 3 );
        assertEquals( tree.root.left.right.data, 11 );
        assertEquals( tree.root.left.right.right.data, 42 );
        assertEquals( tree.root.right.data, 85 );
        assertEquals( tree.root.right.right.data, 99 );

        testEnd();
    }

    /*==============================================================================
                                    MISC FUNCTIONS
    ==============================================================================*/
    public static Integer testCounter = 0;
    public static Integer testsPassed = 0;
    public static String currentTestCaseName = "";

    //-------------------------------------------------------------------[testStart]
// Taken and edited from provided main
    public static void testStart( String name )
    {
        currentTestCaseName = name;
        testCounter = 0;
        testsPassed = 0;

        System.out.println( "============================================================" );
        System.out.println( "TEST: " + currentTestCaseName );
        System.out.println( "============================================================" );
    }

    //---------------------------------------------------------------------[testEnd]
// Taken and edited from provided main
    public static void testEnd()
    {
        System.out.println( "------------------------------------------------------------" );
        System.out.println( "TEST SUMMARY:" );
        if ( testCounter.equals( testsPassed ) )
        {
            System.out.println( "PASS" );
        }
        else
        {
            System.out.println( "FAIL" );
        }
        System.out.println(
                "Passed " + testsPassed + "/" + testCounter + " assertions" );

        System.out.println( "------------------------------------------------------------" );
        System.out.println();

        testCounter = 0;
        testsPassed = 0;
        currentTestCaseName = "";
    }

    //----------------------------------------------------------------[assertEquals]
// Taken and edited from provided main
    public static void assertEquals( Object actual, Object expected )
    {
        testCounter++;

        if ( !actual.equals( expected ) )
        {
            System.out.println( "Assert " + testCounter + ": FAIL" );
            System.out.println(
                    "\tExpected " + expected.toString() + ". Received " + actual.toString() );
        }
        else
        {
            System.out.println( "Assert " + testCounter + ": PASS" );
            testsPassed++;
        }
    }

    //----------------------------------------------------------------[printTree]
    public static void printnode(Node<Integer> x, int h)
    {
        for (int i = 0; i < h; i++)
            System.out.print("        ");

        System.out.println("[" + x.data + "]{"+ getHeight(x) +"}");
    }

    public static void printBST(Node<Integer> node)
    {
        showR(node, 0);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void showR(Node<Integer> node, int h)
    {
        if (node == null)
            return;

        if (!node.rightThread)
            showR(node.right, h+1);

        printnode(node, h);

        showR(node.left, h+1);
    }

    public static int getHeight(Node<Integer> N) 
    {
        if (N == null)
            return 0;

        return N.height;
    }
}