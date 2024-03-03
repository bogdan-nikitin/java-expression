package expression;

public interface FastToMiniString extends ToMiniString {
    default void buildMiniString(StringBuilder sb) {
        sb.append(this);
    }

    @Override
    default String toMiniString() {
        StringBuilder sb = new StringBuilder();
        buildMiniString(sb);
        return sb.toString();
    }
}
