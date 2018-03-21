package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

   // TODO Do not forget: you must override the methods insert and remove
   // conveniently.

   // AUXILIARY
   protected int calculateBalance(BSTNode<T> node) {

      int retorno = 0;
      if (!node.isEmpty())
         retorno = this.heightRec((BSTNode<T>) node.getLeft(), -1) - this.heightRec((BSTNode<T>) node.getRight(), -1);

      return retorno;
   }

   private void leftRotation(BSTNode<T> node) {

      BSTNode<T> aux = (BSTNode<T>) node.getRight();

      node.setRight(aux.getLeft());
      aux.getLeft().setParent(node);
      aux.setLeft(node);
      aux.setParent(node.getParent());
      node.setParent(aux);

      if (node.equals(this.getRoot())) {
         root = aux;
      } else {

         if (aux.getData().compareTo(aux.getParent().getData()) < 0) {
            aux.getParent().setLeft(aux);
         } else {
            aux.getParent().setRight(aux);
         }
      }

   }

   private void rightRotation(BSTNode<T> node) {

      BSTNode<T> aux = (BSTNode<T>) node.getLeft();

      node.setLeft(aux.getRight());
      aux.getRight().setParent(node);
      aux.setRight(node);
      aux.setParent(node.getParent());
      node.setParent(aux);

      if (node.equals(this.getRoot())) {
         root = aux;
      } else {

         if (aux.getData().compareTo(aux.getParent().getData()) > 0) {
            aux.getParent().setRight(aux);
         } else {
            aux.getParent().setLeft(aux);
         }
      }

   }

   // AUXILIARY
   protected void rebalance(BSTNode<T> node) {

      int balance = calculateBalance(node);
      if (balance < -1) {
         if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
            rightRotation((BSTNode<T>) node.getRight());
         }
         leftRotation(node);
      } else if (balance > 1) {
         if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
            leftRotation((BSTNode<T>) node.getLeft());
         }
         rightRotation(node);
      }
   }

   /**
    * /////////////////////////////////////////////
    * 
    * 
    * // AUXILIARY protected void rebalance(BSTNode<T> node) {
    * 
    * int balance = this.calculateBalance(node); // esta pendendo para a
    * esquerda if (balance < -1) { if (calculateBalance((BSTNode<T>)
    * node.getLeft()) >= 1) { Util.leftRotation((BSTNode<T>) node.getLeft()); }
    * Util.rightRotation(node);
    * 
    * // esta pendendo para a direita } else if (balance > 1) { if
    * (calculateBalance((BSTNode<T>) node.getRight()) <= -1) {
    * Util.rightRotation((BSTNode<T>) node.getRight()); }
    * Util.leftRotation(node); } }
    **/

   // AUXILIARY
   protected void rebalanceUp(BSTNode<T> node) {

      BSTNode<T> parent = (BSTNode<T>) node.getParent();
      while (parent != null) {
         this.rebalance(parent);
         parent = (BSTNode<T>) parent.getParent();
      }
   }

   @Override
   public void insert(T element) {

      if (this.isEmpty())
         root = newNode(element, null);

      else
         insertR(element, root);

   }

   private void insertR(T element, BSTNode<T> node) {

      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new BSTNode.Builder().build());
         node.setRight(new BSTNode.Builder().build());

         node.getLeft().setParent(node);
         node.getRight().setParent(node);
      } else {
         if (element.compareTo(node.getData()) < 0) {
            insertR(element, (BSTNode<T>) node.getLeft());
         } else if (element.compareTo(node.getData()) > 0) {
            insertR(element, (BSTNode<T>) node.getRight());
         }
         this.rebalance(node);
      }
   }

   @Override
   public void remove(T element) {

      BSTNode<T> node = search(element);

      if (!node.isEmpty()) {
         if (node.isLeaf()) {
            node.setData(null);
            this.rebalanceUp(node);
         } else if (hasOnlyOneChild(node)) {
            if (node.getParent() != null) { // node != root
               if (!node.getParent().getLeft().equals(node)) {
                  if (!node.getLeft().isEmpty()) {
                     node.getParent().setRight(node.getLeft());
                     node.getLeft().setParent(node.getParent());
                  } else {
                     node.getParent().setRight(node.getRight());
                     node.getRight().setParent(node.getParent());
                  }
               } else {
                  if (!node.getLeft().isEmpty()) {
                     node.getParent().setLeft(node.getLeft());
                     node.getLeft().setParent(node.getParent());
                  } else {
                     node.getParent().setLeft(node.getRight());
                     node.getRight().setParent(node.getParent());
                  }
               }
            } else {
               if (node.getLeft().isEmpty()) {
                  root = (BSTNode<T>) node.getRight();
               } else {
                  root = (BSTNode<T>) node.getLeft();
               }
               root.setParent(null);
            }
            this.rebalanceUp(node);

         } else {
            T sucessor = sucessor(node.getData()).getData();
            remove(sucessor);
            node.setData(sucessor);
         }
      }
   }

}
