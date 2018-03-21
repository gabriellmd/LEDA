package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

   @Override
   public Integer floor(Integer[] array, Integer x) {

      if (array == null)
         return null;
      if (array.length == 0)
         return null;

      boolean existeIgual = false;
      boolean floorInicializado = false;
      int floor = array[0];
      int i = 0;
      while (!existeIgual && i < array.length) {
         if (array[i].compareTo(x) == 0) {
            floorInicializado = true;
            floor = array[i];
            existeIgual = true;
         }

         if (array[i].compareTo(x) < 0) {
            if (!floorInicializado) {
               floor = array[i];
               floorInicializado = true;
            }

            else if (array[i].compareTo(floor) > 0)
               floor = array[i];
         }
         i++;
      }
      if (!floorInicializado)
         return null;

      if (existeIgual)
         return x;

      return floor;
   }

   @Override
   public Integer ceil(Integer[] array, Integer x) {

      if (array == null)
         return null;
      if (array.length == 0)
         return null;

      boolean existeIgual = false;
      boolean ceilInicializado = false;
      int ceil = array[0];
      int i = 0;
      while (!existeIgual && i < array.length) {
         if (array[i].compareTo(x) == 0) {
            ceilInicializado = true;
            ceil = array[i];
            existeIgual = true;
         }

         if (array[i].compareTo(x) > 0) {
            if (!ceilInicializado) {
               ceil = array[i];
               ceilInicializado = true;
            } else if (array[i].compareTo(ceil) < 0)
               ceil = array[i];
         }
         i++;
      }
      if (!ceilInicializado)
         return null;

      if (existeIgual)
         return x;

      return ceil;
   }
   /**
   private int buscaBinariaCeil(Integer[] array, Integer x, int inicio, int fim){
   	
   	int meio = (fim - inicio)/2;
   	
   	
   	if(inicio < fim){
   		meio = (fim - inicio)/2;
   		return buscaBinariaCeil(array, x, inicio, meio);
   	}
   	
   	ceil(array, x);
   	
   		return buscaBinariaCeil(array, x, meio + 1, fim);
   	
   }
    **/

}
