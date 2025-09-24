package com.example.projekt2septembar;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Prostor extends RecyclerView.ItemDecoration {

   public final int vertikalno;

    public Prostor(int vertikalno) {
        this.vertikalno = vertikalno;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom=vertikalno;

    }

}
