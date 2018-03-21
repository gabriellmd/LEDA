package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {

      if (array == null)
         return;
      if (array.length == 0)
         return;
      if (leftIndex > rightIndex)
         return;
      if (leftIndex < 0 || rightIndex < 0)
         return;
      if (rightIndex >= array.length)
         return;

      //Achando o maior e o menor elemento
      int maiorElemento = array[leftIndex];
      int menorElemento = array[leftIndex];
      for (int i = leftIndex; i <= rightIndex; i++) {
         if (maiorElemento < array[i])
            maiorElemento = array[i];

         if (menorElemento > array[i])
            menorElemento = array[i];
      }

      //Criando um array auxiliar e setando todos seus valores com 0.
      Integer[] aux = new Integer[maiorElemento - menorElemento + 1];
      for (int i = 0; i < aux.length; i++)
         aux[i] = 0;

      // Contando quantas vezes o valor se repete no array original... 		
      for (int i = leftIndex; i <= rightIndex; i++)
         aux[array[i] - menorElemento] += 1;

      /**
      // Fazendo a acumulada.
      for(int i = 1; i < aux.length; i++){
    	  aux[i] = aux[i] + aux[i - 1];
      }
      
      //Criando novo vetor e ordenando.
      Integer[] ordenado = new Integer[array.length];// TALVEZZ DE ERROOO NO TAAMNHO
      for(int i = rightIndex; i >= leftIndex; i--){
    	  ordenado[aux[ array[i] - menorElemento] - 1]= array[i];////////////
    	  aux[array[i] - menorElemento] -= 1;
      }
      
      // Igualando o array original com o ordenado.
      for(int i = leftIndex; i <= rightIndex; i++){
    	  array[i] = ordenado[i];
      }
      **/
      
      
      // Ordenando o array original.
      int j = leftIndex;
      for (int i = 0; i < aux.length; i++) {
         if (aux[i] != 0) {
            while (aux[i] > 0) {
               array[j] = i + menorElemento; // Ja que o axiliar comeca no indice zero e nao no indice de menor elemento, tem-se que fazer essa soma.
               aux[i] = aux[i] - 1;
               j++;
            }
         }

      }
     
   }

}
