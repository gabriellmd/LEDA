package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   @Override
   public void fillWithoutRebalance(T[] array) {
	   
      quickInsert(array, 0, array.length - 1);
   }

   
   private void quickInsert(T[] array, int ini, int fim) {
	   
      if (ini < fim) {
         int pivot = partition(array, ini, fim);
         insertWithoutRebalance(array[pivot]);
         quickInsert(array, ini, pivot - 1);
         quickInsert(array, pivot + 1, fim);
      }
   }
   

   private int partition(T[] array, int ini, int fim) {
	   
      int i = ini + 1;
      int f = fim;
      
      while (i <= f) {
         if (array[i].compareTo(array[ini]) <= 0) {
            i++;
         } else if (array[f].compareTo(array[ini]) > 0) {
            f--;
         } else {
            swap(array, i, f);
            i++;
            f--;
         }
      }
      swap(array, ini, f);
      return f;
   }
   
   
   private void swap(T[] array, int x, int y) {
	   
	      T aux = array[x];
	      array[x] = array[y];
	      array[y] = aux;
	   }

   
   private void insertWithoutRebalance(T element) {
	   
      insertWithNodeWithoutRebalance(root, element);
   }
   

   @SuppressWarnings("unchecked")
   private void insertWithNodeWithoutRebalance(BSTNode<T> node, T element) {
	   
      if (node.isEmpty()) {
    	  
         node.setData(element);
         node.setLeft(this.newNode(null, node));
         node.setRight(this.newNode(null, node));
   
      } else {
         if ((element.compareTo(node.getData()) < 0)) {
            insertWithNodeWithoutRebalance((BSTNode<T>) node.getLeft(), element);
            
         } else if ((element.compareTo(node.getData()) > 0)) {
            insertWithNodeWithoutRebalance((BSTNode<T>) node.getRight(), element);
         }

      }

   }

  
   @Override
   // AUXILIARY
   protected void rebalance(BSTNode<T> node) {

      if (node != null && !node.isEmpty()) {
         int balance = calculateBalance(node);
         if (Math.abs(balance) > 1) {
            BSTNode<T> aux = null;

            boolean incrementedRotation = false;
            if (balance < -1) {// Pende para a direita - > R
               if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {// pende para a esquerda -> L
                  node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
                  this.RLcounter++;
                  incrementedRotation = true;
               }
               aux = Util.leftRotation(node);
               if (!incrementedRotation) {
                  this.RRcounter++;
               }
            } else if (balance > 1) {// pende para a esquerda -> L
               if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {// pende para a direita -> R
                  node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
                  this.LRcounter++;
                  incrementedRotation = true;
               }
               aux = Util.rightRotation(node);
               if (!incrementedRotation) {
                  this.LLcounter++;
               }
            }
            if (this.root.equals(node)) {
               this.root = aux;
            } else {
               if (aux.getParent().getLeft().equals(node)) { 
                  aux.getParent().setLeft(aux);
               } else {
                  aux.getParent().setRight(aux);
               }
            }
         }
      }
   }

}
