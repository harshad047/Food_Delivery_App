package com.tss.model.deliverypartner;

import com.tss.model.repositary.Repositary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeliveryPartnerManager implements IDeliveryPartnerManager, Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> partners;
	private static final String FILE_NAME = "delivery_partners.ser";

	public DeliveryPartnerManager() {
		List<String> loaded = Repositary.readFromFile(FILE_NAME);
		if (loaded != null) {
			this.partners = new ArrayList<>(loaded);
			return;
		}
		if(loaded.size()==0)
		{
		this.partners = new ArrayList<>(Arrays.asList("Swiggy", "Zomato"));
		saveToFile();
		}
		this.partners = new ArrayList<>();
	}

	@Override
	public void addPartner(String name) {
		partners.add(name);
		saveToFile();
	}

	@Override
	public void removePartner(int index) {
		
		if(partners.size()==1)
		{
			System.out.println("Only One Delivery Partner is there Cannot Remove");
			return;
		}
		if (index >= 0 && index < partners.size()) {
			partners.remove(index);
			saveToFile();
		}
	}

	@Override
	public String assignPartner() {
		if (partners.isEmpty()) {
			return "No delivery partners available.";
		}
		return partners.get(new Random().nextInt(partners.size()));
	}

	@Override
	public String[] getPartners() {
		return partners.toArray(new String[0]);
	}

	public void saveToFile() {
		Repositary.saveToFile(partners, FILE_NAME);
	}

	public void loadFromFile() {
		List<String> loaded = Repositary.readFromFile(FILE_NAME);
		if (loaded != null) {
			partners.clear();
			partners.addAll(loaded);
		}
	}
}
