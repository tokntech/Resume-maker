package com.nikitha.toknresumebuilder.helper;

public interface ItemTouchHelperAdapter {

/**
 * Called when an item has been dragged far enough to trigger a move. This is called every time
 * an item is shifted, and <strong>not</strong> at the end of a "drop" event.<br/>
 * <br/>*/
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}