package musicOrganiser;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class listener extends MouseAdapter {
	
	private JTable table;
	
	public listener(JTable table) {
		this.table = table;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Point point = event.getPoint();
		int currentRow = table.rowAtPoint(point);
		table.setRowSelectionInterval(currentRow, currentRow);
	}
}