package LAB1;

public class AVLTree {

    private Node root;

    private Node addElement(int element, Node node) {
        if (element < node.element) {
            if (node.leftChild == null) {
                node.leftChild = new Node();
                node.leftChild.parent = node;
                node.leftChild.element = element;
                return node.leftChild;
            } else {
                return addElement(element, node.leftChild);
            }
        } else if (element > node.element) {
            if (node.rightChild == null) {
                node.rightChild = new Node();
                node.rightChild.parent = node;
                node.rightChild.element = element;
                return node.rightChild;
            } else {
                return addElement(element, node.rightChild);
            }
        }
        return null;
    }


    public AVLTree addElement(int element) {
        if (root == null) {
            root = new Node();
            root.element = element;
            return this;
        }
        Node addedElement = addElement(element, root);
        balanceTree(addedElement.parent);
        return this;
    }

    private void balanceTree(Node node) {
        if (node == null)
            return;

        int balanceFactor = balanceFactor(node);
        if (balanceFactor == -2) {
            int balanceFactorLeftChild = balanceFactor(node.leftChild);
            if (balanceFactorLeftChild == 0 || balanceFactorLeftChild == -1) {
                rotateRR(node.leftChild);
            } else {
                rotateLR(node.leftChild);
            }
        } else if (balanceFactor == 2) {
            int balanceFactorRightChild = balanceFactor(node.rightChild);
            if (balanceFactorRightChild == 0 || balanceFactorRightChild == 1) {
                rotateLL(node.rightChild);
            } else {
                rotateRL(node.rightChild);
            }
        }
        balanceTree(node.parent);
    }

    private void rotateLL(Node node) {
        Node parent = node.parent;
        parent.rightChild = node.leftChild;
        if (parent.rightChild != null) {
            parent.rightChild.parent = parent;
        }
        node.leftChild = parent;
        node.parent = parent.parent;
        parent.parent = node;
        if (node.parent == null) {
            root = node;
        }
        if (node.parent != null) {
            if (parent.equals(node.parent.leftChild)) {
                node.parent.leftChild = node;
            } else {
                node.parent.rightChild = node;
            }
        }

    }

    private void rotateRR(Node node) {
        Node parent = node.parent;
        parent.leftChild = node.rightChild;
        node.rightChild = parent;
        if (parent.leftChild != null) {
            parent.leftChild.parent = parent;
        }
        node.parent = parent.parent;
        parent.parent = node;
        if (node.parent == null) {
            root = node;
        }
        if (node.parent != null) {
            if (parent.equals(node.parent.leftChild)) {
                node.parent.leftChild = node;
            } else {
                node.parent.rightChild = node;
            }
        }
    }


    private void rotateLR(Node node) {
        Node rotationNode = node.rightChild;
        rotateLL(rotationNode);
        rotateRR(rotationNode);
    }

    private void rotateRL(Node node) {
        Node rotationNode = node.leftChild;
        rotateRR(rotationNode);
        rotateLL(rotationNode);
    }


    public static int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.rightChild) - height(node.leftChild);
    }

    private static int height(Node node) {
        if (node == null) {
            return 0;
        }
        int maxHeight=Math.max(height(node.leftChild), height(node.rightChild));
        return maxHeight+1;
    }


    public String printTree() {
        if (root == null) {
            return "└──";
        }
        return root.print();
    }

    @Override
    public String toString() {
        if (root == null){
            return "[]";
        }
        return "[" + root.toString() + "]";
    }

    private boolean searchForElement(Node root, Node node) {
        if (root == node) {
            return true;
        }
        boolean found = false;
        if (root.getLeftChild() != null) {
            found = searchForElement(root.getLeftChild(), node);
        }
        if (!found && root.getRightChild() != null) {
            found = searchForElement(root.getRightChild(), node);
        }
        return found;
    }

}