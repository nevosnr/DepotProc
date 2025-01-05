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
import herts.davidneve.depotprocessor.model.Customer;
import herts.davidneve.depotprocessor.model.Parcel;
import herts.davidneve.depotprocessor.model.Processor;

public class CombinedViewer extends JFrame{

    private JTable comboTable;
    private DefaultTableModel comboTableModel;

    public CombinedViewer(Map<String, Customer> customerMap, Map<String, Parcel> parcelMap){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Combined View of Parcels and Customer Data");
        setLayout(new GridBagLayout());
        GridBagConstraints gbconstrian = new GridBagConstraints();

        comboTableModel = new DefaultTableModel();
        comboTableModel.setColumnIdentifiers(new String[] {"Parcel ID", "Customer Name", "Days in Depot", "Costs to be payed"});
        comboTable = new JTable(comboTableModel);

        Processor processor = new Processor();
        Map<String, CombinedDataStore> combineMap = processor.combine(customerMap, parcelMap);

        for(CombinedDataStore combinedDataStore : combineMap.values()){
            comboTableModel.addRow(new Object[]{
                combinedDataStore.getParcelId(),
                combinedDataStore.getCustomerName(),
                combinedDataStore.getDaysInDepot()
            });
        }
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
}
