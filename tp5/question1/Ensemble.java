package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    public boolean add(T t) {
        // à compléter pour la question1-1
        if (t == null)
            return false;
        
        if(!table.contains(t)){
            
            return table.add(t);
        }
        
        return false;
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
        Ensemble<T> union_Ens = new Ensemble<T>();
        union_Ens.addAll(this);
        

        if (e == null) 
            return union_Ens;
        
        union_Ens.addAll(e);
        
        return union_Ens;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
        Ensemble<T> inter_Ens = new Ensemble<T>();
        
        if (e == null) 
            return inter_Ens;
        
        inter_Ens.addAll(this);
        inter_Ens.retainAll(e);
        return inter_Ens;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
        Ensemble<T> diff_Ens = new Ensemble<T>();
        diff_Ens.addAll(this);
        diff_Ens.removeAll(e);
        
        return diff_Ens;
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) {
        // à compléter pour la question1-2
        Ensemble<T> diffSym_Ens = new Ensemble<T>();
        
        if(e == null)
            return diffSym_Ens;
        
        diffSym_Ens = this.union(e);
        diffSym_Ens.removeAll(this.inter(e));
            
            
        return diffSym_Ens;
    }
    
}
