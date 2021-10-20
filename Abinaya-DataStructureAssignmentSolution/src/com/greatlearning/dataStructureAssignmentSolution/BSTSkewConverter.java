package com.greatlearning.dataStructureAssignmentSolution;

class Node{
	int val;
	Node leftnode, rightnode;

	Node(int item){
		val = item;
		leftnode = rightnode = null;
	}
}

public class BSTSkewConverter {
	public static Node node;
	static Node prevNode = null;
	static Node headNode = null;


	static void BTToSkewed(Node root,int order){

		if(root == null)
			return;


		if(order > 0)
			BTToSkewed(root.rightnode, order);

		else
			BTToSkewed(root.leftnode, order);

		Node rightNode = root.rightnode;
		Node leftNode = root.leftnode;


		if(headNode == null){
			headNode = root;
			root.leftnode = null;
			prevNode = root;
		}
		else{
			prevNode.rightnode = root;
			root.leftnode = null;
			prevNode = root;
		}

		if (order > 0)
			BTToSkewed(leftNode, order);

		else
			BTToSkewed(rightNode, order);

	}

	static void traverseRightSkewed(Node root){
		if(root == null)
			return;

		System.out.print(root.val + " ");
		traverseRightSkewed(root.rightnode);       
	}


	public static void main (String[] args){

		BSTSkewConverter tree = new BSTSkewConverter();
		tree.node = new Node(50);
		tree.node.leftnode = new Node(30);
		tree.node.rightnode = new Node(60);
		tree.node.leftnode.leftnode = new Node(10);
		tree.node.rightnode.leftnode= new Node(40);

		int order = 0;
		BTToSkewed(node, order);
		traverseRightSkewed(headNode);
	}
}
