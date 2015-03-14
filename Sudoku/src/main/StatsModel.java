package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StatsModel extends AbstractTableModel{
	private String[] columnNames;
	private Vector<String[]> data;
	
	
	public StatsModel(){
		this.columnNames = new String[]{"Player", "Puzzle", "Time"};
		String[] empty = new String[]{"", "", ""};
		this.data = new Vector<String[]>();
		for (int i = 0; i < 10; i++){
			data.add(empty);
		}
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int arg0) {
		return this.columnNames[arg0];
	}

	@Override
	public int getRowCount() {
		return 10;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public void addEntry(String[] entry) {
		data.insertElementAt(entry, 0);
		System.out.print("Add entry is called\n");
		System.out.print(entry[0] + "," + entry[1] + "," + entry[2] + "\n");
		sort();
	}

	private void sort() {
		Map<String, String[]> map = new HashMap<String, String[]>();
		Vector<String> keys = new Vector<String>();
		for (int i =0; i < this.data.size(); i++){
			String key = this.data.get(i)[2];
			if (key.equals("")){
				break;
			}
			String[] value = this.data.get(i);
			map.put(key, value);
			keys.add(key);
		}
		data.clear();
		data = this.sort(map, keys);
	}
	
	private Vector<String[]> sort(Map<String, String[]> myMap, Vector<String> keys){
		Vector<String[]> returnVector = new Vector<String[]>();
		for (int i = 0; i < 10; i++){
			returnVector.add(new String[]{"","",""});
		}
		java.util.Collections.sort(keys);		
		for (int i = keys.size() - 1; i >= 0; i--){
			String key = keys.get(i);
			String[] value = myMap.get(key);
			returnVector.insertElementAt(value, 0);
		}
		return returnVector;		
	}

	public void clear() {
		data.removeAllElements();
		for (int i = 0; i < 10; i++){
			data.add(new String[]{"","",""});
		}
	}
}
