package gui;

import service.Leaderboard;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * LeaderboardTableModel (Adapter): Custom Table Model that adapts the Leaderboard
 * data structure to the format required by the JTable GUI component.
 */
public class LeaderboardTableModel extends AbstractTableModel {
    private final Leaderboard leaderboard;
    private final String[] columnNames = {"Rank", "Player Name", "Score"};

    public LeaderboardTableModel(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    /**
     * Called whenever new data is submitted to force the JTable to redraw.
     */
    public void refresh() {
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return leaderboard.getAllEntries().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<?> entries = leaderboard.getAllEntries();
        if (rowIndex < 0 || rowIndex >= entries.size()) return null;

        Object entry = entries.get(rowIndex);
        if (entry instanceof model.LeaderboardEntry) {
            model.LeaderboardEntry lbEntry = (model.LeaderboardEntry) entry;
            switch (columnIndex) {
                case 0: return rowIndex + 1; // Rank is the position + 1
                case 1: return lbEntry.getName();
                case 2: return lbEntry.getScore();
                default: return null;
            }
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 2) {
            return Integer.class;
        }
        return String.class;
    }
}
