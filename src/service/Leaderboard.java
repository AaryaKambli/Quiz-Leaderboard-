package service;

import model.LeaderboardEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Leaderboard (Service/Manager): Manages the list of scores.
 * This class handles all the logic related to adding scores and keeping the list ordered.
 */
public class Leaderboard {
    private final List<LeaderboardEntry> entries;

    /**
     * Initializes the leaderboard with some sample entries to start with.
     */
    public Leaderboard() {
        this.entries = new ArrayList<>();
        // Add some initial data
        addEntry("Aarya", 950);
        addEntry("Naman", 880);
        addEntry("Ronaldo", 1020);
        addEntry("Messi", 750);
    }

    /**
     * Adds a new score entry to the leaderboard. The list is sorted immediately
     * after adding the entry to maintain real-time ranking.
     * @param name Player name.
     * @param score Player score.
     */
    public void addEntry(String name, int score) {
        String safeName = name != null && !name.trim().isEmpty() ? name.trim() : "Anonymous Player";

        // The LeaderboardEntry constructor handles the non-negative check,
        // but it's good practice to ensure the name is cleaned up here.
        this.entries.add(new LeaderboardEntry(safeName, score));

        // Re-sort the entire list after insertion
        applyRankOrder();
    }

    /**
     * Sorts the entire list of entries using the ordering defined in LeaderboardEntry.
     * Renamed from 'sortEntries' to 'applyRankOrder' for better method clarity.
     */
    private void applyRankOrder() {
        Collections.sort(this.entries);
    }

    /**
     * Returns the current, sorted list of entries for display.
     * @return An unmodifiable list of all entries.
     */
    public List<LeaderboardEntry> getAllEntries() {
        return Collections.unmodifiableList(entries);
    }
}
