import java.text.DecimalFormat;

public class Interface {

	private Node origin;

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Interface() {
		Origin originFunction = new Origin();
		V1Axis leftFunction = new V1Axis();
		V1Axis rightFunction = new V1Axis();
		V2Axis upFunction = new V2Axis();
		V2Axis downFunction = new V2Axis();

		origin = new Node(originFunction,0,0);

		origin.left = new Node(leftFunction,0,0);
		origin.right = new Node(rightFunction,0,0);
		origin.up = new Node(upFunction,0,0);
		origin.down = new Node(downFunction,0,0);

		origin.prevVal = null;
		origin.nextVal = null;
	}

	public Interface(Node[] arr) {

	}

	public Node getOrigin() {
		return origin;
	}

	public float addPoint(Function function, int v1, int v2) {
	}

	public Node removePoint(int v1, int v2) {
	}

	public Node getPoint(int v1, int v2) {
	}

	public Node[] toArray() {
	}

	public float calculateValue(Function function, int v1, int v2) {
		if (function == null) return Float.NaN;
		return function.calculate(v1,v2);
	}

	public float findMaxValue() {
	}

	public Node findMax() {
	}

	public float findMinValue() {
	}

	public Node findMin() {
	}

	public String printFunctionValues(String functionName) {
	}

	public int removeAllFunctionPoints(String functionName){
	}

	public int countNumberOfPoints(){
	}

	public int[] numPointsPerQuadrant(){
	}

	public void clearAllData(){
	}

	//ADD HELPER FUNCTIONS BELOW
}