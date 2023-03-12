package com.example.itemvalidator.common;

public class ErrorCodes {

    public enum ResponseCode{
        INCORRECT_TITLE_LENGTH,
        INCORRECT_ITEMS_IN_SPECIFICS,
        MODEL_MISSING,
        INVALID_SITE_ID,
        VALIDATION_PASS
    }

    public enum ResponseMessage{
        INCORRECT_TITLE_LENGTH("Title length under 85 characters"),
        INCORRECT_ITEMS_IN_SPECIFICS("Number of items in specifics between 2 - 10"),
        MODEL_MISSING("Item Specifics must include a Model with a non empty value"),
        INVALID_SITE_ID("Site Id must be between 0 and 100"),
        VALIDATION_PASS("Validation pass")
        ;

        public final String text;
        ResponseMessage(String msg) {
            this.text = msg;
        }
    }


}
