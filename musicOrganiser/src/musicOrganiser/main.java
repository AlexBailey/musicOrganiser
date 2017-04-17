package musicOrganiser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class main extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel dTableM; 
	private JPopupMenu popMenu;
	private JMenuItem menuAdd;
	private JMenuItem menuRemove;
	private JMenuItem menuRemoveAll;
	
	public main() {
		super("ab00631 Music Organiser");

		String[] columns = new String[] {"Artist", "Track", "Album", "Genre"};
		String[][] rows = new String[][] {
			{"Artist", "Track", "Album", "Genre"}	
		};

		dTableM = new DefaultTableModel(rows, columns){
		 private static final long serialVersionUID = 1L;
			public Class getColumnClass(int column) {
		        if (column >= 0 && column <= getColumnCount())
		          return getValueAt(0, column).getClass();
		        else
		        	return Object.class;
		      }
		    };
		table = new JTable(dTableM);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dTableM);
	    table.setRowSorter(sorter);
	    getContentPane().add(new JScrollPane(table));
		
	    popMenu = new JPopupMenu();
	    menuAdd = new JMenuItem("Add A New Track");
	    menuRemove = new JMenuItem("Remove Current Selected Track");
	    menuRemoveAll = new JMenuItem("Remove All Tracks From Library");
		
	    menuAdd.addActionListener(this);
		menuRemove.addActionListener(this);
		menuRemoveAll.addActionListener(this);
		
		popMenu.add(menuAdd);
		popMenu.add(menuRemove);
		popMenu.add(menuRemoveAll);
		
		table.setComponentPopupMenu(popMenu);
		table.addMouseListener(new listener(table));
		
		add(new JScrollPane(table));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 150);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new main().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JMenuItem menu = (JMenuItem) event.getSource();
		if (menu == menuAdd) {
			addNewTrack();
		} else if (menu == menuRemove) {
			removeSelectedTrack();
		} else if (menu == menuRemoveAll) {
			removeAllTracks();
		}
	}
	
	private void addNewTrack() {
		dTableM.addRow(new String[0]);
	}
	
	private void removeSelectedTrack() {
		int selectedRow = table.getSelectedRow();
		dTableM.removeRow(selectedRow);
	}
	
	private void removeAllTracks() {
		int rowCount = dTableM.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			dTableM.removeRow(0);
		}
	}
}