package view;

public abstract class DelayedAction implements  Comparable<DelayedAction>{
    private final long executeAfter;

    public DelayedAction(long executeAfter) {
        this.executeAfter = executeAfter;
    }

    public long getExecuteAfter() {
        return executeAfter;
    }

    @Override
    public int compareTo(DelayedAction o) {
        return Long.compare(executeAfter, o.executeAfter);
    }

    public abstract void run();
}
