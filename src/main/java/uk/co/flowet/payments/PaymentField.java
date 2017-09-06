package uk.co.flowet.payments;

import static uk.co.flowet.payments.PaymentField.ElementType.*;

final class PaymentField {

    public enum ElementType{
        FIELD, DROPDOWN, CHECKBOX;
    }

    public enum Supreme{

        FULL_NAME("order_billing_name", FIELD),
        EMAIL("order_email", FIELD),
        TEL("order_tel", FIELD),
        ADDRESS_1("bo", FIELD),
        ADDRESS_2("oba3", FIELD),
        ADDRESS_3("order_billing_address_3", FIELD),
        CITY("order_billing_city", FIELD),
        POSTCODE("order_billing_zip", FIELD),
        COUNTRY("order_billing_country", DROPDOWN),
        TYPE("credit_card_type", DROPDOWN),
        NUMBER("cnb", FIELD),
        EXP_DATE_1("credit_card_month", DROPDOWN),
        EXP_DATE_2("credit_card_year", DROPDOWN),
        CVV("vval", FIELD),
        TERMS("order_terms", CHECKBOX);

        private final String id;
        private final ElementType elementType;

        Supreme(String id, ElementType elementType) {
            this.id = id;
            this.elementType = elementType;
        }

        public String id() {
            return id;
        }

        public ElementType elementType() {
            return elementType;
        }
    }
}
