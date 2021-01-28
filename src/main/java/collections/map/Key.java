package collections.map;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Key {
    private int key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key1 = (Key) o;
        return Objects.equals(key, key1.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
