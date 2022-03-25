import java.text.DecimalFormat;

public class Interface {

	private Node origin;

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below
	int numNodes = 0;

	public Interface() {
		Function originFunction = new Origin();
		origin = new Node(originFunction, 0, 0);
		origin.right = origin.left = origin.down = origin.up = null;
		origin.prevVal = origin.nextVal = null;
	}

	public Interface(Node[] arr) {
		Function originFunction = new Origin();
		origin = new Node(originFunction, 0, 0);
		origin.right = origin.left = origin.down = origin.up = null;
		origin.prevVal = origin.nextVal = null;

		for (int i = 0; i < arr.length; i++)
			if (arr[i] != null)
				addPoint(arr[i].getFunction(),arr[i].getVariables()[0], arr[i].getVariables()[1]);
	}

	public Node getOrigin() {
		return origin;
	}

	public float addPoint(Function function, int v1, int v2) {
		if (v1 == 0 || v2 == 0) return Float.NaN;

		Node newNode = new Node(function, v1, v2);
		Node nodeV1 = origin;
		numNodes++;

		if (v1 > 0)
			nodeV1 = moveRight(nodeV1, v1);
		else
			nodeV1 = moveLeft(nodeV1, v1);

		Node nodeV2 = nodeV1;
		Node nodeLinked = null;
		if (v2 > 0)
			nodeLinked = moveUp(nodeV2, v2, newNode);
		else
			nodeLinked = moveDown(nodeV2, v2, newNode);

		if (nodeLinked.nextVal == null) {
			//Get node on y axis
			Node yAxis = origin;
			Function yAxisNode = new V1Axis();
			Node yNode = new Node(yAxisNode, 0, v2);

			if (v2 > 0) {
				while (yAxis.up != null && yAxis.up.getVariables()[1] < v2)
					yAxis = yAxis.up;

				if (yAxis.up == null) {
					yAxis.up = yNode;
					yNode.down = yAxis;

					yAxis = yAxis.up;
				} else if (yAxis.up.getVariables()[1] > v2) {
					//Insert node in between doubly linked list
					Node nextNode = yAxis.up;

					yNode.up = nextNode;
					nextNode.down = yNode;
					yNode.down = yAxis;
					yAxis.up = yNode;

					yAxis = yAxis.up;
				} else {
					yAxis = yAxis.up;
				}
			} else {
				while (yAxis.down != null && yAxis.down.getVariables()[1] > v2)
					yAxis = yAxis.down;

				if (yAxis.down == null) {
					yAxis.down = yNode;
					yNode.up = yAxis;

					yAxis = yAxis.down;
				} else if (yAxis.down.getVariables()[1] < v2) {
					//Insert node in between doubly linked list
					Node nextNode = yAxis.down;

					yNode.down = nextNode;
					nextNode.up = yNode;
					yNode.up = yAxis;
					yAxis.down = yNode;

					yAxis = yAxis.down;
				} else {
					yAxis = yAxis.down;
				}
			}

			//Connect yAxis to new node
			if (v1 > 0) {
				while (yAxis.right != null && yAxis.right.getVariables()[0] < v1)
					yAxis = yAxis.right;

				if (yAxis.right != null) {
					nodeLinked.right = yAxis.right;
					yAxis.right.left = nodeLinked;
				}

				yAxis.right = nodeLinked;
				nodeLinked.left = yAxis;

			} else {
				while (yAxis.left != null && yAxis.left.getVariables()[0] > v1)
					yAxis = yAxis.left;

				if (yAxis.left != null) {
					nodeLinked.left = yAxis.left;
					yAxis.left.right = nodeLinked;
				}

				yAxis.left = nodeLinked;
				nodeLinked.right = yAxis;

			}
		}

		return newNode.getValue();
	}

	public Node removePoint(int v1, int v2) {
		if (v1 == 0 || v2 == 0) return null;

		Node nodeV1 = origin;
		numNodes--;

		Node deleteNode = null;
		if (v1 > 0) {
			while (nodeV1.right != null && nodeV1.right.getVariables()[0] != v1)
				nodeV1 = nodeV1.right;

			if (nodeV1.right == null) return null;

			deleteNode = nodeV1.right;
		}
		else {
			while (nodeV1.left != null && nodeV1.left.getVariables()[0] != v1)
				nodeV1 = nodeV1.left;

			if (nodeV1.left == null) return null;

			deleteNode = nodeV1.left;
		}

		if (v2 > 0) {
			while (deleteNode.up != null && deleteNode.up.getVariables()[1] != v2)
				deleteNode = deleteNode.up;

			if (deleteNode.up == null) return null;

			deleteNode = deleteNode.up;

			if (deleteNode.prevVal != null) {
				deleteNode.down.up = deleteNode.prevVal;
				deleteNode.prevVal.down = deleteNode.down;

				if (deleteNode.up != null) {
					deleteNode.up.down = deleteNode.prevVal;
					deleteNode.prevVal.up = deleteNode.up;
				}
				else
					deleteNode.prevVal.nextVal = null;
			}
			else {
				//Unlink vertically
				deleteNode.down.up = deleteNode.up;

				if (deleteNode.up != null)
					deleteNode.up.down = deleteNode.down;
			}
		}
		else {
			while (deleteNode.down != null && deleteNode.down.getVariables()[1] != v2)
				deleteNode = deleteNode.down;

			if (deleteNode.down == null) return null;

			deleteNode = deleteNode.down;

			if (deleteNode.prevVal != null) {
				deleteNode.up.down = deleteNode.prevVal;
				deleteNode.prevVal.up = deleteNode.up;

				if (deleteNode.down != null) {
					deleteNode.down.up = deleteNode.prevVal;
					deleteNode.prevVal.down = deleteNode.down;
				}
				else
					deleteNode.prevVal.nextVal = null;
			}
			else {
				deleteNode.up.down = deleteNode.down;

				if (deleteNode.down != null)
					deleteNode.down.up = deleteNode.up;
			}
		}

		//Unlink horizontally
		if (v1 > 0) {
			if (deleteNode.prevVal != null) {
				deleteNode.left.right = deleteNode.prevVal;
				deleteNode.prevVal.left = deleteNode.left;

				if (deleteNode.right != null) {
					deleteNode.right.left = deleteNode.prevVal;
					deleteNode.prevVal.right = deleteNode.right;
				}

				deleteNode.prevVal.nextVal = null;
			}
			else {
				deleteNode.left.right = deleteNode.right;

				if (deleteNode.right != null)
					deleteNode.right.left = deleteNode.left;
			}

			//See if axis is still necessary
			Node nodeV1Check = origin;
			while (nodeV1Check.getVariables()[0] != v1)
				nodeV1Check = nodeV1Check.right;

			if (nodeV1Check.up == null && nodeV1Check.down == null) {
				nodeV1Check.left.right = nodeV1Check.right;

				if (nodeV1Check.right != null)
					nodeV1Check.right.left = nodeV1Check.left;
			}
		}else {
			if (deleteNode.prevVal != null) {
				deleteNode.right.left = deleteNode.prevVal;
				deleteNode.prevVal.right = deleteNode.right;

				if (deleteNode.left != null) {
					deleteNode.left.right = deleteNode.prevVal;
					deleteNode.prevVal.left = deleteNode.left;
				}

				deleteNode.prevVal.nextVal = null;
			}
			else {
				deleteNode.right.left = deleteNode.left;

				if (deleteNode.left != null)
					deleteNode.left.right = deleteNode.right;
			}

			//See if axis is still necessary
			Node nodeV1Check = origin;
			while (nodeV1Check.getVariables()[0] != v1)
				nodeV1Check = nodeV1Check.left;

			if (nodeV1Check.up == null && nodeV1Check.down == null) {
				nodeV1Check.right.left = nodeV1Check.left;

				if (nodeV1Check.left != null)
					nodeV1Check.left.right = nodeV1Check.right;
			}
		}

		//See if y axis node is still useful
		if (v2 > 0) {
			Node nodeV2Check = origin;
			while (nodeV2Check.getVariables()[1] != v2)
				nodeV2Check = nodeV2Check.up;

			if (nodeV2Check.right == null && nodeV2Check.left == null) {
				nodeV2Check.down.up = nodeV2Check.up;

				if (nodeV2Check.up != null)
					nodeV2Check.up.down = nodeV2Check.down;
			}
		}
		else {
			Node nodeV2Check = origin;
			while (nodeV2Check.getVariables()[1] != v2)
				nodeV2Check = nodeV2Check.down;

			if (nodeV2Check.right == null && nodeV2Check.left == null) {
				nodeV2Check.up.down = nodeV2Check.down;

				if (nodeV2Check.down != null)
					nodeV2Check.down.up = nodeV2Check.up;
			}
		}

		deleteNode.up = deleteNode.down = deleteNode.left = deleteNode.right = deleteNode.prevVal = null;
		return deleteNode;
	}

	public Node getPoint(int v1, int v2) {
		if (v1 == 0 || v2 == 0) return null;

		Node nodeV1 = origin;
		Node searchNode = null;
		if (v1 > 0) {
			while (nodeV1.right != null && nodeV1.right.getVariables()[0] != v1)
				nodeV1 = nodeV1.right;

			if (nodeV1.right == null) return null;
			else
				searchNode = nodeV1.right;
		}
		else {
			while (nodeV1.left != null && nodeV1.left.getVariables()[0] != v1)
				nodeV1 = nodeV1.left;

			if (nodeV1.left == null) return null;
			else
				searchNode = nodeV1.left;
		}

		if (v2 > 0) {
			while (searchNode.up != null && searchNode.up.getVariables()[1] != v2)
				searchNode = searchNode.up;

			if (searchNode.up == null) return null;
			else
				searchNode = searchNode.up;

			return searchNode;
		}
		else {
			while (searchNode.down != null && searchNode.down.getVariables()[1] != v2)
				searchNode = searchNode.down;

			if (searchNode.down == null) return null;
			else
				searchNode = searchNode.down;

			return searchNode;
		}
	}

	public Node[] toArray() {
		if (numNodes == 0) return null;

		Node[] array = new Node[numNodes];
		int i = 0;
		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.right != null)
			nodeV1 = nodeV1.right;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the top
			while (nodeV2.up != null)
				nodeV2 = nodeV2.up;

			//Traverse down y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					if (nodeV2.prevVal == null && i < numNodes) {
						array[i++] = nodeV2;
					}
					else {
						Node sameNode = nodeV2;

						while (sameNode != null  && i < numNodes) {
							array[i++] = nodeV2;
							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.down;
			}

			nodeV1 = nodeV1.left;
		}

		return array;
	}

	public float calculateValue(Function function, int v1, int v2) {
		if (function == null) return Float.NaN;
		return function.calculate(v1,v2);
	}

	public float findMaxValue() {
		if (numNodes == 0) return Float.NaN;
		float maxValue = 0;

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getValue() > maxValue) {
						maxValue = nodeV2.getValue();

						if (nodeV2.prevVal != null) {
							Node sameNode = nodeV2;

							while (sameNode != null) {
								if (sameNode.getValue() > maxValue)
									maxValue = sameNode.getValue();

								sameNode = sameNode.prevVal;
							}
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getValue() > maxValue)
								maxValue = sameNode.getValue();

							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			nodeV1 = nodeV1.right;
		}

		return maxValue;
	}

	public Node findMax() {
		if (numNodes == 0) return null;
		boolean found = false;
		float maxValue = findMaxValue();
		Node maxNode = origin;

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = nodeV1;

		while (nodeV1 != null && nodeV2.getValue() != maxValue) {
			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getValue() == maxValue) {
						found = true;
						maxNode = nodeV2;

						if (nodeV2.prevVal != null) {
							Node sameNode = nodeV2;

							while (sameNode != null) {
								if (sameNode.getValue() == maxValue)
									maxNode = nodeV2;

								sameNode = sameNode.prevVal;
							}
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getValue() == maxValue)
								maxNode = nodeV2;

							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			if (found)
				return maxNode;
			else {
				nodeV1 = nodeV1.right;
				nodeV2 = nodeV1;
			}
		}

		return maxNode;
	}

	public float findMinValue() {
		if (numNodes == 0) return Float.NaN;
		float minValue = findMaxValue();

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getValue() < minValue) {
						minValue = nodeV2.getValue();

						if (nodeV2.prevVal != null) {
							Node sameNode = nodeV2;

							while (sameNode != null) {
								if (sameNode.getValue() < minValue)
									minValue = sameNode.getValue();

								sameNode = sameNode.prevVal;
							}
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getValue() < minValue)
								minValue = sameNode.getValue();

							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			nodeV1 = nodeV1.right;
		}

		return minValue;
	}

	public Node findMin() {
		if (numNodes == 0) return null;
		float minValue = findMinValue();
		Node minNode = origin;
		boolean found = false;

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = nodeV1;

		while (nodeV1 != null && nodeV2.getValue() != minValue) {
			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getValue() == minValue) {
						found = true;
						minNode = nodeV2;

						if (nodeV2.prevVal != null) {
							Node sameNode = nodeV2;

							while (sameNode != null) {
								if (sameNode.getValue() == minValue)
									minNode = nodeV2;

								sameNode = sameNode.prevVal;
							}
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getValue() == minValue)
								minNode = nodeV2;

							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			if (found)
				return minNode;
			else {
				nodeV1 = nodeV1.right;
				nodeV2 = nodeV1;
			}
		}

		return minNode;
	}

	public String printFunctionValues(String functionName) {
		if (numNodes == 0) return "";

		String output = "";

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getFunction().getFunctionName().compareTo(functionName) == 0) {
						if (nodeV2.prevVal == null) {
							output = output + floatFormatter(nodeV2.getValue()) + ";";
						} else {
							Node sameNode = nodeV2;

							while (sameNode != null) {
								if (sameNode.getFunction().getFunctionName().compareTo(functionName) == 0)
									output = output + floatFormatter(sameNode.getValue()) + ";";
								sameNode = sameNode.prevVal;
							}
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getFunction().getFunctionName().compareTo(functionName) == 0)
								output = output + floatFormatter(sameNode.getValue()) + ";";
							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			nodeV1 = nodeV1.right;
		}

		if (output != "")
			output = output.substring(0,output.length() - 1);
		return output;
	}

	public int removeAllFunctionPoints(String functionName){
		if (numNodes == 0) return 0;

		int numRemoved = 0;

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {
				if (nodeV2.getVariables()[0] != 0 && nodeV2.getVariables()[1] != 0) {
					//If function is the head
					if (nodeV2.getFunction().getFunctionName().compareTo(functionName) == 0) {
						if (nodeV2.prevVal == null) {
							numRemoved++;
							numNodes--;

							if (nodeV2.down != null)
								nodeV2.down.up = nodeV2.up;

							if (nodeV2.up != null)
								nodeV2.up.down = nodeV2.down;

							if (nodeV2.left != null)
								nodeV2.left.right = nodeV2.right;

							if (nodeV2.right != null)
								nodeV2.right.left = nodeV2.left;
						}
						else {
							numRemoved++;
							numNodes--;

							if (nodeV2.left != null) {
								nodeV2.prevVal.left = nodeV2.left;
								nodeV2.left.right = nodeV2.prevVal;
							}

							if (nodeV2.right != null) {
								nodeV2.prevVal.right = nodeV2.right;
								nodeV2.right.left = nodeV2.prevVal;
							}


							if (nodeV2.up != null) {
								nodeV2.prevVal.up = nodeV2.up;
								nodeV2.up.down = nodeV2.prevVal;
							}

							if (nodeV2.down != null) {
								nodeV2.prevVal.down = nodeV2.down;
								nodeV2.down.up = nodeV2.prevVal;
							}

							nodeV2.prevVal.nextVal = null;
						}
					}
					else if (nodeV2.prevVal != null) { //function isn't head, still need to check other nodes
						Node sameNode = nodeV2;

						while (sameNode != null) {
							if (sameNode.getFunction().getFunctionName().compareTo(functionName) == 0) {
								if (sameNode.prevVal != null)
									sameNode.prevVal.nextVal = sameNode.nextVal;
								sameNode.nextVal.prevVal = sameNode.prevVal;
								numRemoved++;
								numNodes--;
							}

							sameNode = sameNode.prevVal;
						}
					}
				}

				nodeV2 = nodeV2.up;
			}

			nodeV1 = nodeV1.right;
		}
		return numRemoved;
	}

	public int countNumberOfPoints(){
		return numNodes;
	}

	public int[] numPointsPerQuadrant(){
		int[] indexArray = new int[4];
		int q1 = 0;
		int q2 = 0;
		int q3 = 0;
		int q4 = 0;

		Node nodeV1 = origin;

		//Go to end of x axis
		while (nodeV1.left != null)
			nodeV1 = nodeV1.left;

		Node nodeV2 = null;

		while (nodeV1 != null) {
			nodeV2 = nodeV1;

			//Get nodeV2 to the bottom
			while (nodeV2.down != null)
				nodeV2 = nodeV2.down;

			//Traverse up y axis
			while (nodeV2 != null) {

				if (nodeV2.getVariables()[0] < 0 && nodeV2.getVariables()[1] < 0)
					q3++;
				else if (nodeV2.getVariables()[0] < 0 && nodeV2.getVariables()[1] > 0)
					q2++;
				else if (nodeV2.getVariables()[0] > 0 && nodeV2.getVariables()[1] < 0)
					q4++;
				else if (nodeV2.getVariables()[0] > 0 && nodeV2.getVariables()[1] > 0)
					q1++;

				//Check prev values
				if (nodeV2.prevVal != null) {
					Node sameNode = nodeV2;

					while (sameNode != null) {
						if (nodeV2.getVariables()[0] < 0 && nodeV2.getVariables()[1] < 0)
							q3++;
						else if (nodeV2.getVariables()[0] < 0 && nodeV2.getVariables()[1] > 0)
							q2++;
						else if (nodeV2.getVariables()[0] > 0 && nodeV2.getVariables()[1] < 0)
							q4++;
						else if (nodeV2.getVariables()[0] > 0 && nodeV2.getVariables()[1] > 0)
							q1++;

						sameNode = sameNode.nextVal;
					}
				}

				nodeV2 = nodeV2.up;
			}

			nodeV1 = nodeV1.right;
		}

		indexArray[0] = q1;
		indexArray[1] = q2;
		indexArray[2] = q3;
		indexArray[3] = q4;

		return indexArray;
	}

	public void clearAllData(){
		if (origin.right != null) {
			origin.right.left = null;
			origin.right = null;
		}

		if (origin.left != null) {
			origin.left.right = null;
			origin.left = null;
		}

		if (origin.up != null) {
			origin.up.down = null;
			origin.up = null;
		}

		if (origin.down != null) {
			origin.down.up = null;
			origin.down = null;
		}

		numNodes = 0;
	}

	//ADD HELPER FUNCTIONS BELOW
	public Node moveRight(Node node, int var) {
		while (node.right != null && node.right.getVariables()[0] < var)
			node = node.right;

		Function xAxis = new V1Axis();
		Node xNode = new Node(xAxis, var, 0);

		if (node.right == null) {
			node.right = xNode;
			xNode.left = node;

			node = node.right;
		}
		else if (node.right.getVariables()[0] > var) {
			//Insert node in between doubly linked list
			Node nextNode = node.right;

			xNode.right = nextNode;
			nextNode.left = xNode;
			xNode.left = node;
			node.right = xNode;

			node = node.right;
		}
		else if (node.right.getVariables()[0] == var) {
			node = node.right;
		}

		return node;
	}

	public Node moveLeft(Node node, int var) {
		while (node.left != null && node.left.getVariables()[0] > var)
			node = node.left;

		Function xAxis = new V1Axis();
		Node xNode = new Node(xAxis, var, 0);

		if (node.left == null) {
			node.left = xNode;
			xNode.right = node;

			node = node.left;
		}
		else if (node.left.getVariables()[0] < var) {
			//Insert node in between doubly linked list
			Node nextNode = node.left;

			xNode.left = nextNode;
			nextNode.right = xNode;
			xNode.right = node;
			node.left = xNode;

			node = node.left;
		}
		else if (node.left.getVariables()[0] == var) {
			node = node.left;
		}

		return node;
	}

	public Node moveUp(Node node, int var, Node newNode) {
		while (node.up != null && node.up.getVariables()[1] < var)
			node = node.up;

		if (node.up == null) {
			node.up = newNode;
			newNode.down = node;

			node = node.up;
		}
		else if (node.up.getVariables()[1] > var) {
			//Insert node in between doubly linked list
			Node nextNode = node.up;

			newNode.up = nextNode;
			nextNode.down = newNode;
			newNode.down = node;
			node.up = newNode;

			node = node.up;
		}
		else if (node.up.getVariables()[1] == var) {
			node = node.up;
			addToList(node, newNode);
		}

		return newNode;
	}

	public Node moveDown(Node node, int var, Node newNode) {
		while (node.down != null && node.down.getVariables()[1] > var)
			node = node.down;

		if (node.down == null) {
			node.down = newNode;
			newNode.up = node;

			node = node.down;
		}
		else if (node.down.getVariables()[1] < var) {
			//Insert node in between doubly linked list
			Node nextNode = node.down;

			newNode.down = nextNode;
			nextNode.up = newNode;
			newNode.up = node;
			node.down = newNode;

			node = node.down;
		}
		else if (node.down.getVariables()[1] == var) {
			node = node.down;
			addToList(node, newNode);
		}

		return newNode;
	}

	public void addToList(Node node, Node newNode) {
		String walkerName = node.getFunction().getFunctionName();
		String newNodeName = newNode.getFunction().getFunctionName();

		//Order in ascending by function
		if (walkerName.compareTo(newNodeName) < 0) { //Attach to prevVal
			while (node.prevVal != null && walkerName.compareTo(newNodeName) < 0) {
				node = node.prevVal;
				walkerName = node.getFunction().getFunctionName();
			}

			if (node.prevVal == null) {
				newNode.nextVal = node;
				node.prevVal = newNode;
			}
			else {
				newNode.prevVal = node.prevVal;
				newNode.nextVal = node;
				node.prevVal = newNode;
				newNode.prevVal.nextVal = newNode;
			}

			newNode.left = null;
			newNode.up = null;
			newNode.right = null;
			newNode.down = null;
		}
		else { //Attach to beginning, make new head
			newNode.prevVal = node;
			newNode.nextVal = null;
			node.nextVal = newNode;

			if (node.left != null) {
				node.left.right = newNode;
				newNode.left = node.left;
				node.left = null;
			}

			if (node.right != null) {
				node.right.left = newNode;
				newNode.right = node.right;
				node.right = null;
			}

			if (node.up != null) {
				node.up.down = newNode;
				newNode.up = node.up;
				node.up = null;
			}

			if (node.down != null) {
				node.down.up = newNode;
				newNode.down = node.down;
				node.down = null;
			}
		}
	}

}