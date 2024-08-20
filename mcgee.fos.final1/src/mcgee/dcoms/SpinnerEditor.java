package mcgee.dcoms;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
    private JSpinner spinner;

    public SpinnerEditor() {
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Set minimum to 1
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));
        spinner.addChangeListener(e -> fireEditingStopped()); // Notify editor that editing has stopped
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }
}
