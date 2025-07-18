package com.tss.model.discount;

public class DiscountManager implements IDiscountManager {

    private RegularDiscount regularDiscount;

    public DiscountManager() {
        this.regularDiscount = RegularDiscount.loadDiscount();
    }

    @Override
    public double applyDiscounts(double total) {
        if (total > 500) {
            return regularDiscount.applyDiscount(total);
        }
        return total;
    }

    @Override
    public void setDiscount(IDiscount discount) {
        if (discount instanceof RegularDiscount) {
            this.regularDiscount = (RegularDiscount) discount;
        }
    }

    public RegularDiscount getRegularDiscount() {
        return this.regularDiscount;
    }
}
