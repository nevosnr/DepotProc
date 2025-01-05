package herts.davidneve.depotprocessor.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import herts.davidneve.depotprocessor.model.Customer;
import herts.davidneve.depotprocessor.model.CustomerReader;
import herts.davidneve.depotprocessor.model.Parcel;
import herts.davidneve.depotprocessor.model.ParcelReader;

public class ParcelViewer extends JFrame {

    private JTable _parcelTable;
    private JTable _customerTable;
    private DefaultTableModel _tableModel;
    private DefaultTableModel _customerTableModel;
    private JButton _slctParcelFileBtn, _slctCustomerFileBtn, _prcParcelBtn, _prcCustomerBtn;
    private JLabel _statusLbl;
    private ParcelReader _parcelReader;
    private CustomerReader _customerReader;

    public ParcelViewer(){
        _parcelReader = new ParcelReader();
        _customerReader = new CustomerReader();
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
        _prcParcelBtn = new JButton("Process Parcel File");
        _prcCustomerBtn = new JButton("Process Customer File");

        //Add event listeners to Buttons
        _slctParcelFileBtn.addActionListener(new FileChooseListener(true));
        _slctCustomerFileBtn.addActionListener(new FileChooseListener(false));
        _prcParcelBtn.addActionListener(new ProcessFileListener(true));
        _prcCustomerBtn.addActionListener(new ProcessFileListener(false));

        //Add buttons to a panel that is 2x2 using the inbuilt gridbaglayout provides granular ability to set postioning of GUI elements.
        gbConstrian.fill = GridBagConstraints.BOTH; 
        gbConstrian.insets = new Insets(5,5,5,5); //little bit of padding!
        //Buttons defined in a grid by their x/y coordinates.
        gbConstrian.gridx = 0;
        gbConstrian.gridy = 0;
        add(_slctParcelFileBtn, gbConstrian);
        gbConstrian.gridx = 1;
        gbConstrian.gridy = 0;
        add(_slctCustomerFileBtn, gbConstrian);
        gbConstrian.gridx = 0;
        gbConstrian.gridy = 1;
        add(_prcParcelBtn, gbConstrian);
        gbConstrian.gridx = 1;
        gbConstrian.gridy = 1;
        add(_prcCustomerBtn, gbConstrian);          
        //scroll panel for output display shown below buttons (y pos of 2)
        gbConstrian.gridx = 0; 
        gbConstrian.gridy = 2;
        gbConstrian.gridwidth = 2;
        gbConstrian.weightx = 1.0;
        gbConstrian.weighty = 1.0;
        add(new JScrollPane(_parcelTable), gbConstrian);
        gbConstrian.gridy = 3;
        add(new JScrollPane(_customerTable), gbConstrian);

        //status updates shown below panel (y pos of 3)
        gbConstrian.gridy = 3;
        gbConstrian.weighty = 0.0;
        add(_statusLbl, gbConstrian);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ProcessFileListener implements ActionListener {
        private boolean isParcelFile;

        public ProcessFileListener(boolean isParcelFile){
            this.isParcelFile = isParcelFile;
        }

        @Override
        public void actionPerformed(ActionEvent event){
            _statusLbl.setText("Processing " + (isParcelFile ? "parcel" : "customer") + " file...");
        }
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
                    ProcessFile(selectedFile, true);
                }
                else{
                    ProcessFile(selectedFile, false);
                }
            }
        }
    }

    private void ProcessFile(File file, boolean isParcelFile) {
        if(isParcelFile){
            Map<String, Parcel> parcelMap = _parcelReader.readParcels(file.getAbsolutePath());
            updateParcelTable(parcelMap);
        }else{
            Map<String, Customer> customerMap = _customerReader.readCustomers(file.getAbsolutePath());
            updateCustomerTable(customerMap);
        }
    }

    private void updateParcelTable(Map<String, Parcel> parcelMap){
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

    private void updateCustomerTable(Map<String, Customer> customerMap){
        _customerTableModel.setRowCount(0);
        for(Customer customer : customerMap.values()){
            _customerTableModel.addRow(new Object[]{
                customer.getId(),
                customer.getFullName()
            });
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(ParcelViewer::new);
    }
    
}
