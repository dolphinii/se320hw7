public class Question3 
{ 
  public static <E extends Comparable<E>> int linearSearch(E[] list, E key) 
  { 
	  for(int x = 0; x<list.length;x++)
	  {
		  if(list[x].equals(key))
			  return x;
	  }
	  return -1;
  } 
   
  public static void main(String[] args) 
  { 
	  Integer[] list = {3, 4, 5, 1, -3, -5, -1}; 
	  System.out.println(linearSearch(list, 2)); 
	  System.out.println(linearSearch(list, 5));  
  } 
}