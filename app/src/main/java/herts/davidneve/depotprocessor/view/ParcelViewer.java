package herts.davidneve.depotprocessor.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import herts.davidneve.depotprocessor.model.CombinedDataStore;
import herts.davidneve.depotprocessor.model.Customer;
import herts.davidneve.depotprocessor.model.Parcel;
import herts.davidneve.depotprocessor.model.ParcelManager;
import herts.davidneve.depotprocessor.model.Processor;
import herts.davidneve.depotprocessor.model.QOfCustomers;

public class ParcelViewer extends JFrame {

    private JTable _parcelTable;
    private JTable _customerTable;
    private DefaultTableModel _tableModel;
    private DefaultTableModel _customerTableModel;
    private JButton _slctParcelFileBtn, _slctCustomerFileBtn, _viewCombinedDataBtn, _addCustomer, _addParcel;
    private JLabel _statusLbl;
    private ParcelManager _parcelManager;
    private QOfCustomers _customerQueue;
    private Map<String, Parcel> parcelMap;
    private Map<String, Customer> customerMap;

    public ParcelViewer(){
        _parcelManager = new ParcelManager();
        _customerQueue = new QOfCustomers();
        _tableModel = new DefaultTableModel();
        _customerTableModel = new DefaultTableModel();
        _tableModel.setColumnIdentifiers(new String[] {"Parcel ID","Weight","Length","Width","Height","Days in Depot"});
        _customerTableModel.setColumnIdentifiers(new String[] {"Parcel ID", "Full Name"});
        _parcelTable = new JTable(_tableModel);
        _customerTable = new JTable(_customerTableModel);

        _statusLbl = new JLabel("Status: Waiting for user Action...");

        setTitle("File uploading");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbConstrian = new GridBagConstraints();
        
        //Create Buttons
        _slctParcelFileBtn = new JButton("Select Parcel File");
        _slctCustomerFileBtn = new JButton("Select Customer File");
        _viewCombinedDataBtn = new JButton("View Combined Data");
        _addCustomer = new JButton("Add Customer");
        _addParcel = new JButton("Add Parcel");

        //Add event listeners to Buttons
        _slctParcelFileBtn.addActionListener(new FileChooseListener(true));
        _slctCustomerFileBtn.addActionListener(new FileChooseListener(false));
        _viewCombinedDataBtn.addActionListener(new ViewCombinedDataListener());
        _addCustomer.addActionListener(new AddCustomerListener());
        _addParcel.addActionListener(new AddParcelListener());
        //Add buttons to a panel that is 2x2 using the inbuilt gridbaglayout provides granular ability to set postioning of GUI elements.
        gbConstrian.fill = GridBagConstraints.BOTH; 
        gbConstrian.insets = new Insets(5,5,5,5); //little bit of padding!

        //Buttons defined in a grid by their x/y coordinates.
        gbConstrian.gridx = 0;
        gbConstrian.gridy = 0;
        add(_slctParcelFileBtn, gbConstrian);
        gbConstrian.gridx = 0;
        gbConstrian.gridy = 1;
        add(_slctCustomerFileBtn, gbConstrian);     
        gbConstrian.gridx  = 0;
        gbConstrian.gridy = 2;
        add(_viewCombinedDataBtn, gbConstrian);

        //Status Label telling user what is required.
        gbConstrian.gridy = 3;
        gbConstrian.weighty = 0.0;
        add(_statusLbl, gbConstrian);

        //Scroll panel to display parcels loaded into the application.
        gbConstrian.gridy = 4;
        gbConstrian.weightx = 1.0;
        gbConstrian.weighty = 1.0;
        add(new JScrollPane(_parcelTable), gbConstrian);

        //Additional buttons to add extra Customers and/or Parcels.
        gbConstrian.gridy = 5;
        gbConstrian.weighty = 0.0;
   
        add(_addParcel, gbConstrian);
        gbConstrian.gridy = 6;
        add(_addCustomer, gbConstrian);

        //Scroll panel to display customers loaded into the application.
        gbConstrian.gridy = 7;
        gbConstrian.weightx = 1.0;
        gbConstrian.weighty = 1.0;
        add(new JScrollPane(_customerTable), gbConstrian);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private class FileChooseListener implements ActionListener {
        private boolean isParcelFile;

        public FileChooseListener(boolean isParcelFile){
            this.isParcelFile = isParcelFile;
        }

        @Override
        public void actionPerformed(ActionEvent event){
            JFileChooser filechooser = new JFileChooser();
            int result = filechooser.showOpenDialog(ParcelViewer.this);

            if(result ==JFileChooser.APPROVE_OPTION){
                File selectedFile = filechooser.getSelectedFile();
                _statusLbl.setText("Selected file: " + selectedFile.getAbsolutePath());

                if(isParcelFile){
                    _parcelManager.readParcels(selectedFile.getAbsolutePath());
                    parcelMap = _parcelManager.getParcelMap();
                    updateParcelTable();
                }
                else{
                    _customerQueue.readCustomers(selectedFile.getAbsolutePath());
                    customerMap = _customerQueue.getCustomerMap();
                    updateCustomerTable();
                }
            }
        }
    }
    private class ViewCombinedDataListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if(parcelMap != null && customerMap != null){
                Processor processor = new Processor();
                TreeMap<String, CombinedDataStore> combMap = processor.combine(customerMap, parcelMap);
                new CombinedViewer(combMap);
            } else{
                _statusLbl.setText("You must upload both parcel and customer data first!");
            }
        }
    }

    private class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            JTextField idField = new JTextField(5);
            JTextField firstNameField = new JTextField(15);
            JTextField lastNameField = new JTextField(20);

            JPanel addCustPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbconstrain = new GridBagConstraints();
            gbconstrain.insets = new Insets(5,5,5,5);

            gbconstrain.gridx = 0; 
            gbconstrain.gridy = 0; 
            gbconstrain.anchor = GridBagConstraints.EAST; 
            addCustPanel.add(new JLabel("ID:"), gbconstrain); 
            gbconstrain.gridx = 1; 
            gbconstrain.anchor = GridBagConstraints.WEST; 
            addCustPanel.add(idField, gbconstrain); 
            gbconstrain.gridx = 0; 
            gbconstrain.gridy = 1; 
            gbconstrain.anchor = GridBagConstraints.EAST; 
            addCustPanel.add(new JLabel("First Name:"), gbconstrain); 
            gbconstrain.gridx = 1; 
            gbconstrain.anchor = GridBagConstraints.WEST; 
            addCustPanel.add(firstNameField, gbconstrain); 
            gbconstrain.gridx = 0; 
            gbconstrain.gridy = 2; 
            gbconstrain.anchor = GridBagConstraints.EAST; 
            addCustPanel.add(new JLabel("Last Name:"), gbconstrain); 
            gbconstrain.gridx = 1; 
            gbconstrain.anchor = GridBagConstraints.WEST; 
            addCustPanel.add(lastNameField, gbconstrain);

            int result = JOptionPane.showConfirmDialog(null, addCustPanel, "Add Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if(result == JOptionPane.OK_OPTION){
                String id = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                Customer newCustomer = new Customer(firstName + " " + lastName, id);
                _customerQueue.addCustomer(newCustomer);
                customerMap = _customerQueue.getCustomerMap();
                updateCustomerTable();
            }

        }
    }

    private class AddParcelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JTextField idField = new JTextField(5);
            JTextField weightField = new JTextField(10);
            JTextField lengthField = new JTextField(10);
            JTextField widthField = new JTextField(10);
            JTextField heightField = new JTextField(10);
            JTextField daysInDepotField = new JTextField(10);
    
            JPanel addParcelPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbconstrain = new GridBagConstraints();
            gbconstrain.insets = new Insets(5, 5, 5, 5); // Adding padding
    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 0;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("ID:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(idField, gbconstrain);    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 1;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("Weight:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(weightField, gbconstrain);    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 2;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("Length:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(lengthField, gbconstrain);    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 3;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("Width:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(widthField, gbconstrain);    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 4;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("Height:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(heightField, gbconstrain);    
            gbconstrain.gridx = 0;
            gbconstrain.gridy = 5;
            gbconstrain.anchor = GridBagConstraints.EAST;
            addParcelPanel.add(new JLabel("Days in Depot:"), gbconstrain);    
            gbconstrain.gridx = 1;
            gbconstrain.anchor = GridBagConstraints.WEST;
            addParcelPanel.add(daysInDepotField, gbconstrain);
    
            int result = JOptionPane.showConfirmDialog(null, addParcelPanel,
                "Add Parcel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
            if (result == JOptionPane.OK_OPTION) {
                String id = idField.getText();
                float weight = Float.parseFloat(weightField.getText());
                float length = Float.parseFloat(lengthField.getText());
                float width = Float.parseFloat(widthField.getText());
                float height = Float.parseFloat(heightField.getText());
                int daysInDepot = Integer.parseInt(daysInDepotField.getText());
                Parcel newParcel = new Parcel(id, weight, length, width, height, daysInDepot);
                _parcelManager.addParcel(newParcel);
                parcelMap = _parcelManager.getParcelMap();
                updateParcelTable();
            }
        }
    }
    

    private void updateParcelTable(){
        _tableModel.setRowCount(0);
        for(Parcel parcel : parcelMap.values()){
            _tableModel.addRow(new Object[] {
                parcel.getId(),
                parcel.getWeight(),
                parcel.getLength(),
                parcel.getWidth(),
                parcel.getHeight(),
                parcel.getDaysinDepot()
            });
        }
    }

    private void updateCustomerTable(){
        _customerTableModel.setRowCount(0);
        for(Customer customer : customerMap.values()){
            _customerTableModel.addRow(new Object[]{
                customer.getId(),
                customer.getFullName()
            });
        }
    }    
}
