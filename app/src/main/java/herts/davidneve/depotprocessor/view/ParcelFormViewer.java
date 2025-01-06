package herts.davidneve.depotprocessor.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import herts.davidneve.depotprocessor.model.CombinedDataStore;

public class ParcelFormViewer extends JFrame {

    private JCheckBox _parcelCollected;
    private CombinedDataStore combinedData;
    private Map<String, CombinedDataStore> combineMap;

    public ParcelFormViewer(CombinedDataStore combinedData, Map<String, CombinedDataStore> combineMap){    
    
        this.combinedData = combinedData;
        this.combineMap = combineMap;
    
        setTitle("Parcel Details");
        setLayout(new GridBagLayout());
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.insets = new Insets(5,5,5,5);
        gbConstraints.fill = GridBagConstraints.HORIZONTAL;

        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        add(new JLabel("Parcel ID: " + combinedData.getParcelId()),gbConstraints);
        gbConstraints.gridx = 1;
        add(new JLabel("Customer Name: " + combinedData.getCustomerName()), gbConstraints);

        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        add(new JLabel("Width: " + combinedData.getWidth()),gbConstraints);
        gbConstraints.gridx = 1;
        add(new JLabel("Height: " + combinedData.getHeight()), gbConstraints);
        gbConstraints.gridx = 2;
        add(new JLabel("Length: " + combinedData.getLength()), gbConstraints);
        gbConstraints.gridx = 3;
        add(new JLabel("Weight: " + combinedData.getWeight()), gbConstraints);

        gbConstraints.gridx = 0;
        gbConstraints.gridy = 2;
        add(new JLabel("Days in depot:  " + combinedData.getDaysInDepot()), gbConstraints);
        gbConstraints.gridy ++;

        add(new JLabel("Cost of Delivery:   £" + combinedData.getCost()), gbConstraints);
        gbConstraints.gridx = 1;
        gbConstraints.gridx = 0;
        gbConstraints.gridy ++;

        _parcelCollected = new JCheckBox("Collected");
        add(_parcelCollected, gbConstraints);
        gbConstraints.gridy ++;

        pack();
        setLocationRelativeTo(this);
        setVisible(true);
    }    
}
