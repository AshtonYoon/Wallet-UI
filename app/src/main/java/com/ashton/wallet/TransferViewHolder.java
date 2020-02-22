package com.ashton.wallet;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class TransferViewHolder extends RecyclerView.ViewHolder {
    private final AppCompatTextView mType;
    private final AppCompatTextView mDate;
    private final AppCompatTextView mAmount;
    private final AppCompatTextView mDollar;

    private Resources mResources;

    public TransferViewHolder(@NonNull View itemView) {
        super(itemView);

        mType = itemView.findViewById(R.id.transfer_type);
        mDate = itemView.findViewById(R.id.transfer_date);
        mAmount = itemView.findViewById(R.id.transfer_amount);
        mDollar = itemView.findViewById(R.id.transfer_dollar);

        mResources = itemView.getResources();
    }

    public void BindData(Transfer transfer) {
        if (transfer.ismType()) {
            mType.setText("Received");
            mType.setTextColor(mResources.getColor(R.color.transferReceive));
            mAmount.setTextColor(mResources.getColor(R.color.transferReceive));
            mDollar.setTextColor(mResources.getColor(R.color.transferReceive));
        } else {
            mType.setText("Send");
            mType.setTextColor(mResources.getColor(R.color.transferSend));
            mAmount.setTextColor(mResources.getColor(R.color.transferSend));
            mDollar.setTextColor(mResources.getColor(R.color.transferSend));
        }

        mDate.setText(transfer.getDate());
        mAmount.setText(transfer.getAmount());
        mDollar.setText(transfer.getDollar());


    }
}
