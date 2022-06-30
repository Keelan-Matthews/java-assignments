import java.text.DecimalFormat;

public class Node {

	private int v1; // this is the first variable
	private int v2; // this is the second variable
	public Node left; // this is the node left of this node
	public Node right; // this is the node right of this node
	public Node up; // this is the node up of this node
	public Node down; // this is the node down of this node
	public Node nextVal; // this is the next value of the current node
	public Node prevVal; // this is the prev value of the current node
	private Function nodeFunction; // this is the function associated with the current node

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}
	
	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Node(Function function, int v1, int v2) {
		this.v1 = v1;
		this.v2 = v2;

		if (function != null) {
			nodeFunction = function.clone();
		}
	}

	public Function setFunction(Function function) {
		Function copy = nodeFunction;
		nodeFunction = function;
		return copy;
	}

	public float getValue() {
		if (nodeFunction == null) return Float.NEGATIVE_INFINITY;
		return nodeFunction.calculate(v1,v2);
	}

	public int[] getVariables() {
		return new int[]{v1,v2};
	}

	public Function getFunction(){
		return nodeFunction;
	}

	public String[] getNodeLinks(){
		String[] outputArray = new String[6];

		if (up == null)
			outputArray[0] = "U[][]{}";
		else
			outputArray[0] = "U["+up.v1+"]["+up.v2+"]{"+floatFormatter(up.getValue())+"}";

		if (down == null)
			outputArray[1] = "D[][]{}";
		else
			outputArray[1] = "D["+down.v1+"]["+down.v2+"]{"+floatFormatter(down.getValue())+"}";

		if (right == null)
			outputArray[2] = "R[][]{}";
		else
			outputArray[2] = "R["+right.v1+"]["+right.v2+"]{"+floatFormatter(right.getValue())+"}";

		if (left == null)
			outputArray[3] = "L[][]{}";
		else
			outputArray[3] = "L["+left.v1+"]["+left.v2+"]{"+floatFormatter(left.getValue())+"}";

		if (prevVal == null)
			outputArray[4] = "P[][]{}";
		else
			outputArray[4] = "P["+prevVal.v1+"]["+prevVal.v2+"]{"+floatFormatter(prevVal.getValue())+"}";

		if (nextVal == null)
			outputArray[5] = "N[][]{}";
		else
			outputArray[5] = "N["+nextVal.v1+"]["+nextVal.v2+"]{"+floatFormatter(nextVal.getValue())+"}";

		return outputArray;
	}

}