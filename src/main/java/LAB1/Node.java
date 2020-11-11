package LAB1;

import java.util.Objects;

public class Node {
    int element;
    Node parent;
    Node leftChild;
    Node rightChild;


    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (rightChild != null) {
            rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(element).append("\n");
        if (leftChild != null) {
            leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    public String print() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return element == node.element &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(leftChild, node.leftChild) &&
                Objects.equals(rightChild, node.rightChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, parent, leftChild, rightChild);
    }
}