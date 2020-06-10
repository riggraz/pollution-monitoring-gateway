package org.example.beans;

import org.example.exceptions.NonUniqueNodeIdException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.UUID;

@XmlRootElement
public class NodeList {
    private ArrayList<Node> list;
    private static NodeList instance;

    private NodeList() {
        list = new ArrayList<Node>();
    }

    public synchronized static NodeList getInstance() {
        if (instance == null) instance = new NodeList();
        return instance;
    }

    public synchronized ArrayList<Node> getList() {
        return new ArrayList<Node>(list);
    }

    public synchronized void addNode(Node newNode) throws NonUniqueNodeIdException {
        for (Node node : getList()) {
            if (node.getId().equals(newNode.getId())) throw new NonUniqueNodeIdException(node, newNode);
        }
        list.add(newNode);
    }

    public synchronized void deleteNode(UUID nodeId) {
        for (Node node : getList()) {
            if (node.getId().equals(nodeId)) list.remove(node);
        }
    }
}
