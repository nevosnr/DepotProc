package herts.davidneve.depotprocessor.model;

public class CombinedDataStore {

        private String _parcelId;
        private float _weight;
        private float _length;
        private float _width;
        private float _height;
        private int _daysInDepot;
        private String _customerName;
    
        public CombinedDataStore(String parcelId, float weight, float length, float width, float height, int daysInDepot, String customerName) {
            this._parcelId = parcelId;
            this._weight = weight;
            this._length = length;
            this._width = width;
            this._height = height;
            this._daysInDepot = daysInDepot;
            this._customerName = customerName;
        }
    
        public String getParcelId() {
             return _parcelId;
             }

        public float getWeight() {
             return _weight;
             }

        public float getLength() {
             return _length;
             }

        public float getWidth() {
             return _width;
             }

        public float getHeight() {
             return _height;
             }

        public int getDaysInDepot() {
             return _daysInDepot;
             }

        public String getCustomerName() {
             return _customerName;
             }

    
    
}
