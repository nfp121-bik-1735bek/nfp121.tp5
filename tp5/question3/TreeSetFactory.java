package question3;

import java.util.Set;
import java.util.TreeSet;


public class TreeSetFactory<T extends Comparable<T>> /* à compléter */implements Factory<TreeSet<T>>/* à compléter */{
    
     public TreeSet<T> create(){
            
            return new TreeSet<T>();
    }
}
