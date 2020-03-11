package com.he.tree;

import com.he.iterator.IteratorBFS;

import java.lang.reflect.InvocationTargetException;
import com.he.iterator.Iterator;

public class Tree {

    public static final Class<? extends Iterator<Node>> defaultIterator = (Class<? extends Iterator<Node>>) IteratorBFS.class;

    public static Iterator<Node> newInstance(Class<? extends Iterator<Node>> iteratorClass, Node root) {
        try {
            return iteratorClass.getConstructor(Node.class).newInstance(root);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    private final Node root;
    private Class<? extends Iterator<Node>> iteratorClass;

    public Tree(Node root) {
        this(root, defaultIterator);
    }

    public Tree(Node root, Class<? extends Iterator<Node>> iteratorClass) {
        this.root = root;
        this.iteratorClass = iteratorClass;
    }

    public Node getRoot() {
        return root;
    }

    public Class<? extends Iterator<Node>> getIteratorClass() {
        return iteratorClass;
    }

    public void setIteratorClass(Class<? extends Iterator<Node>> iteratorClass) {
        this.iteratorClass = iteratorClass;
    }

    public Iterator<Node> iterator() {
        return newInstance(iteratorClass, root);
    }

}
