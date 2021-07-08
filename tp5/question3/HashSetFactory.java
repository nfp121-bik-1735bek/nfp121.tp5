package question3;

import java.util.Set;
import java.util.HashSet;

public class HashSetFactory<T>/* à compléter */implements Factory<HashSet<T>>/* à compléter */{
    public HashSet<T> create(){
        return new HashSet<T>();
    }
}