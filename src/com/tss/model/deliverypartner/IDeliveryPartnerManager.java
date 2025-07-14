package com.tss.model.deliverypartner;

public interface IDeliveryPartnerManager {
	void addPartner(String name);
    void removePartner(int index);
    String assignPartner();   
    String[] getPartners();
}
