package model;

import java.util.Objects;

/**
 * LeaderboardEntry (Model): This class holds the data for one player's entry.
 * Implementing Comparable allows the main Leaderboard to sort the entries easily.
 */
public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private final String name;
    private final int score;

    /**
     * Standard constructor for a new leaderboard entry.
     * @param name The name of the player who achieved the score.
     * @param score The actual quiz score.
     */
    public LeaderboardEntry(String name, int score) {
        this.name = name;
        // Make sure the score is non-negative just in case
        this.score = Math.max(0, score);
    }

    // Standard data getters
    public String getName() { return name; }
    public int getScore() { return score; }

    /**
     * Defines the rank order. We want the highest score to appear first (descending).
     * If score A is higher than score B, A should be before B (return a negative number).
     * We achieve descending order by swapping the comparison: compare B to A.
     */
    @Override
    public int compareTo(LeaderboardEntry other) {
        // other.score - this.score gives descending order.
        return Integer.compare(other.score, this.score);
    }

    /**
     * Useful for printing the object state for easy debugging.
     */
    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * Essential for comparing two entries beyond just the memory location.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderboardEntry that = (LeaderboardEntry) o;
        return score == that.score && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
