package app;

import java.util.function.Function;

public class Helpers {
    public static <T> T tap(T value, Function<T, Object> callback) {
        callback.apply(value);

        return value;
    }
}
