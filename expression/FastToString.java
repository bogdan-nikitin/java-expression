package expression;

public interface FastToString {
    default void buildString(StringBuilder sb) {
        sb.append(this);
    }
}
