package herts.davidneve.depotprocessor.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import herts.davidneve.depotprocessor.model.CombinedDataStore;
public class CombinedViewer extends JFrame{

    private JTable comboTable;
    private DefaultTableModel comboTableModel;
    private Map<String, CombinedDataStore> combineMap;

    public CombinedViewer(Map<String, CombinedDataStore> combinedMap){
        this.combineMap = combinedMap;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Combined View of Parcels and Customer Data");
        setLayout(new GridBagLayout());
        GridBagConstraints gbconstrian = new GridBagConstraints();

        comboTableModel = new DefaultTableModel();
        comboTableModel.setColumnIdentifiers(new String[] {"Parcel ID", "Customer Name", "Days in Depot"});
        comboTable = new JTable(comboTableModel);

        updateTable();

        comboTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent event){
                int row = comboTable.rowAtPoint(event.getPoint());
                if (row >= 0){
                    String parcelId = (String) comboTableModel.getValueAt(row, 0);
                    CombinedDataStore combinedData = combineMap.get(parcelId);
                    showParcelDetailsForm(combinedData);
                }
            }
        });
        
        gbconstrian.fill = GridBagConstraints.BOTH;
        gbconstrian.insets = new Insets(5,5,5,5);

        gbconstrian.gridx = 0;
        gbconstrian.gridy = 0;
        gbconstrian.weightx = 1.0;
        gbconstrian.weighty = 1.0;
        add(new JScrollPane(comboTable), gbconstrian);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTable(){
        comboTableModel.setRowCount(0);
        for(CombinedDataStore combinedDataStore : combineMap.values()){
            comboTableModel.addRow(new Object[]{
                combinedDataStore.getParcelId(),
                combinedDataStore.getCustomerName(),
                combinedDataStore.getDaysInDepot()
            });
        }
    }

    public void tableRefresh(Map<String, CombinedDataStore> newCombinedMap){
        this.combineMap = newCombinedMap;
        updateTable();
    }
    
    private void showParcelDetailsForm(CombinedDataStore combinedData){
        new ParcelFormViewer(combinedData, combineMap, this);
    }
}
