package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }
   
   @Override
   public T[] toArray() {
      ArrayList<T> resp = new ArrayList<T>();
      for (T elem : this.heap) {
         resp.add(elem);
      }
      return (T[]) resp.toArray(new Comparable[0]);
   }
  
   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
	  
       int l = this.left(position);
	   int r = this.right(position);
	   
	   int largest = position;
	   if(l < this.size() && this.comparator.compare(this.heap[l], this.heap[position]) > 0){
		   largest = l;
	   }  
	   if(r < this.size() && this.comparator.compare(this.heap[r], this.heap[largest]) > 0){
		   largest = r;
	   }
	   if(largest != position){
		   Util.swap(this.heap, position, largest);
		   heapify(largest);
	   }
      
   }
   
   	/**
	 * Inserts a new element into the heap and maintains the invariant.
	 */
   @Override
   public void insert(T element) {
      // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
      if (index == heap.length - 1) {
         heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
      }
      //============================ MEXER SO ABAIXO ====================================//
      
      if(element != null){
    	  this.index++;
    	  if(!this.isEmpty()){
    	  
	      this.getHeap()[this.index] = element;
	      int i = this.index;
	      while(i > 0 && this.comparator.compare(this.getHeap()[parent(i)], element) < 0){
	    	Util.swap(this.getHeap(), i, this.parent(i));
	    	i = parent(i);
	      }
      }
      else{
    	  this.getHeap()[this.index] = element;
      	}
      }
   }

   @Override
   public void buildHeap(T[] array) {
	  
	  this.heap = array;
	  this.index = array.length - 1;
	  
	  for(int i = array.length / 2; i >= 0; i--){
		  this.heapify(i);
	  }
   }

   /**
	 * Removes and returns the root element of the heap. The method returns null
	 * if the heap is empty. If the heap is min-heap the element is the minimum
	 * of the heap; otherwise, the heap is max-heap and the element is the
	 * maximum of the heap.
	 */
   @Override
   public T extractRootElement() {
	   
	  T retorno;
	  if(this.isEmpty()) 
		  retorno =  null;
	  else{
		  retorno = this.getHeap()[0];
		  Util.swap(this.getHeap(), 0, this.index);
		  this.index--;
		  this.heapify(0);
	  }
	  return retorno;
   }

   @Override
   public T rootElement() {
	
	   T retorno;
	   if(this.isEmpty())
		   retorno = null;
	   else
		   retorno = this.getHeap()[0];
	   
	   return retorno;
   }

   /**
	 * Sorts the elements of an array by using the heap concept. The method must
	 * build a new heap using the array received as a parameter. The method
	 * returns a copy (another array) containing only the not null elements of
	 * the internal array. At the end, the internal array should be empty. This
	 * method simply extracts the elements of the head and puts them into an
	 * auxiliary array that is returned at the end (in ascending order).
	 */
   @Override
   public T[] heapsort(T[] array) {
	   
	   Comparator<T> comparator = getComparator();
	   this.setComparator((o1, o2) -> o2.compareTo(o1));
	   
	   this.buildHeap(array);
	   
	   T[] arrAux = (T[]) (new Comparable[this.size()]);

		for (int index = 0; index < arrAux.length; index++) {
			arrAux[index] = this.extractRootElement();
		}
		this.setComparator(comparator);
	   
	  return arrAux;
   }

   @Override
   public int size() {
	   
	   return this.index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
