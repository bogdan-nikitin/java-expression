package expression;

import java.util.List;

@FunctionalInterface
public interface Expression extends ToMiniString {
    int evaluate(int x);
}
