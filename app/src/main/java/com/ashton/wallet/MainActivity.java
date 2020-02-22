package com.ashton.wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TransferAdapter transferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        mViewPager.setAdapter(new CardPageAdapter(list));
        mViewPager.setClipToPadding(false);
        mViewPager.setClipChildren(false);

        mRecyclerView = findViewById(R.id.transfer_recycerView);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        List<Transfer> empty = new ArrayList<>();
        transferAdapter = new TransferAdapter(empty);
        mRecyclerView.setAdapter(transferAdapter);


        getTransfers();
    }

    private Single<List<Transfer>> datas() {
        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                    List<Transfer> transfers = new ArrayList<>();

                    for (int i = 0; i < 20; i++) {
                        Transfer transfer = new Transfer();
                        transfer.setDate((5 + i) + " Aug");

                        if (new Random().nextInt(100) % 2 == 0) {
                            transfer.SetType(true);
                            double ltc = new Random().nextInt(100);
                            transfer.setAmount("+" + (ltc / 10000) + " LTC");
                            transfer.setDollar("$" + ltc * 0.749);
                        } else {
                            transfer.SetType(false);
                            double ltc = new Random().nextInt(100);
                            transfer.setAmount("-" + (ltc / 10000) + " LTC");
                            transfer.setDollar("$" + ltc * 0.749);
                        }

                        transfers.add(transfer);
                    }

                    emitter.onSuccess(transfers);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            });

            thread.run();
        });
    }

    private void setRecycler(List<Transfer> transfers) {
        io.reactivex.rxjava3.core.Observable<Transfer> list = io.reactivex.rxjava3.core.Observable.fromIterable(transfers);

        list.subscribe(
                transfer -> transferAdapter.addTransfer(transfer),
                throwable -> {
                },
                () -> {
                });
    }

    private void getTransfers() {
        Single<List<Transfer>> listSingle = datas();

        listSingle.subscribeWith(new DisposableSingleObserver<List<Transfer>>() {
            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Transfer> transfers) {
                setRecycler(transfers);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
