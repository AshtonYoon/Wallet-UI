package com.ashton.wallet;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {
    private final Context context;
    private final int horizontalMarginInDp;

    public HorizontalMarginItemDecoration(Context context, @DimenRes int horizontalMarginInDp) {
        this.context = context;
        this.horizontalMarginInDp = horizontalMarginInDp;
    }

    private int getHorizontalMarginInPx() {
        return (int) context.getResources().getDimension(horizontalMarginInDp);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.right = getHorizontalMarginInPx();
        outRect.left = getHorizontalMarginInPx();
    }
}
