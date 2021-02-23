package shop.model;

import java.time.LocalDateTime;

public interface Expirable {
    LocalDateTime setLastUseDate();
}
