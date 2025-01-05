package herts.davidneve.depotprocessor.controller;

import java.util.ArrayList;
import java.util.List;
public class Auditor {
    //Auditor class performs the singleton function. Only one instance of this obejct should exist to ensure duplication does not occur.

    private static Auditor instance;
    private List<String> _auditEntries = new ArrayList<>();

    private Auditor(){}

    public static Auditor getInstance(){
        //method ensures if no instance exists, it will be created.
        if(instance == null){
            instance = new Auditor();
        }
        return instance;
    }

    public void addEntry(String entry){
        _auditEntries.add(entry);
    }

    public List<String> getEntries(){
        return _auditEntries;
    }

    
}
