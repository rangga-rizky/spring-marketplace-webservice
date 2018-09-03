package com.rangga.tokokita.payload.request;

import com.rangga.tokokita.model.Card;
import com.rangga.tokokita.model.Shipping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull
    @Valid
    private Shipping shipping;

    @NotNull
    @Valid
    private CardRequest payment_method;

    public void setPayment_method(CardRequest payment_method) {
        this.payment_method = payment_method;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public CardRequest getPayment_method() {
        return payment_method;
    }

    public Shipping getShipping() {
        return shipping;
    }
}
