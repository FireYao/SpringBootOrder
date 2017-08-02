package com.simpletour.exception;

public class ItemStockNotEnoughException extends RuntimeException {

    public ItemStockNotEnoughException(String message) {
        super(message);
    }
}
