package org.example.beans;

import org.example.exceptions.NonUniqueNodeIdException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class NodeList {
    private List<Node> list;
    private static NodeList instance;

    private NodeList() {
        list = new ArrayList<Node>();
    }

    // If not synchronized two instances could be concurrently created
    public synchronized static NodeList getInstance() {
        if (instance == null) instance = new NodeList();
        return instance;
    }

    public synchronized List<Node> getList() {
        return new ArrayList<Node>(list);
    }

    // If not synchronized, two nodes with same id could be added
    public synchronized void addNode(Node newNode) throws NonUniqueNodeIdException {
        for (Node node : list) {
            if (node.getId().equals(newNode.getId())) throw new NonUniqueNodeIdException(node, newNode);
        }
        // Thread.sleep(5000);
        list.add(newNode);
    }

    public synchronized void deleteNode(UUID nodeId) {
        for (Node node : list) {
            if (node.getId().equals(nodeId)) {
                list.remove(node);
                break;
            }
        }
    }
}
