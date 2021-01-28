package collections.map;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@NoArgsConstructor
@Getter
public class MyHashMap {

    LinkedList<Entry>[] myHashMap = new LinkedList[2];
    int size = 0;

    public void put(Key key, Value value){
        if(size>=myHashMap.length){
            resize();
        }
        int ix = getIndex(key) % myHashMap.length;
        if(myHashMap[ix]==null){
            myHashMap[ix] = new LinkedList<>();
            myHashMap[ix].add(new Entry(key,value));
            size++;
            return;
        }
        else {
            for(Entry entry: myHashMap[ix]){
                if (entry.key.equals(key)){
                    entry.value=value;
                    size++;
                    return;
                }
            }
        }
        myHashMap[ix].add(new Entry(key,value));
        size++;
        return;
    }

    public Value get(Key key){
        int ix = getIndex(key) % myHashMap.length;
        if(myHashMap[ix]==null){
            return null;
        }
        for(Entry entry: myHashMap[ix]){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    public void remove(Key key){
        if(key==null){
            return;
        }

        int ix = getIndex(key) % myHashMap.length;
        if(myHashMap[ix]==null){
            return;
        }

        Entry toRemove = null;

        for (Entry entry:myHashMap[ix]){
            if(entry.key.equals(key)){
                toRemove=entry;
                break;
            }
        }
        if (toRemove==null){
            return;
        }
        myHashMap[ix].remove(toRemove);
        size--;
    }

    public boolean containsKey(Key key){
        if(key==null){
            return false;
        }
        int ix = getIndex(key) % myHashMap.length;

        if(myHashMap[ix]==null){
            return false;
        }

        for(Entry entry:myHashMap[ix]){
            if(entry.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    public void resize(){
        LinkedList<Entry>[] oldHashMap = myHashMap;
        myHashMap = new LinkedList[size*2];
        size=0;

        for (int i =0; i<oldHashMap.length;i++){
            if (oldHashMap[i]==null){
                continue;
            }
            for (Entry entry : oldHashMap[i]){
                put(entry.key,entry.value);
            }
        }
    }

    public int getIndex(Key key){
        return key.hashCode();
    }

}
