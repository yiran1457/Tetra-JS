package net.yiran.tetrajs.util;

public record SlotData (boolean required, String slot, int x, int y){
    public static SlotData create( String slot, int x, int y,boolean required) {
        return new SlotData(required, slot, x, y);
    }
    public static SlotData create(String slot, int x, int y) {
        return create(slot,x,y,false);
    }
}
