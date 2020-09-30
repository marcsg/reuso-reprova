package br.ufmg.reuso.marcelosg.reprova.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String id) {
        super("Could not find item with id=" + id);
    }
}
