package org.example.exceptions;

import org.example.beans.Node;

public class NonUniqueNodeIdException extends Exception {
    public NonUniqueNodeIdException(Node n1, Node n2) {
        super("Node " + n1 + " and node " + n2 + " have the same id");
    }

    public String getErrorMessage() {
        return "There already exist a node with the same id.";
    }
}
