package rs.digitize.backend.search;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS, IS_NULL, NOT_NULL;

    protected static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", "<", "~"};

    public static final String OR_PREDICATE_FLAG = "'";

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_OPERATOR = "OR";

    public static final String AND_OPERATOR = "AND";

    public static final String LEFT_PARENTHESIS = "(";

    public static final String RIGHT_PARENTHESIS = ")";

    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            case '~':
                return LIKE;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case EQUALITY:
                return ":";
            case NEGATION:
                return "!";
            case GREATER_THAN:
                return ">";
            case LESS_THAN:
                return "<";
            case LIKE:
            case STARTS_WITH:
            case ENDS_WITH:
            case CONTAINS:
                return "~";
            case IS_NULL:
                return ":null";
            case NOT_NULL:
                return ":notnull";
            default:
                return null;
        }
    }
}