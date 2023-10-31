package com.example.vietcal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.divider.MaterialDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ExtensionFragment extends Fragment {
    private RecyclerView rcvExtension;
    private View mView;
    private MainActivity mMainActivity;
    public ExtensionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_extension, container, false);
        mMainActivity = (MainActivity) getActivity();

        rcvExtension = mView.findViewById(R.id.rcv_extension);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        rcvExtension.setLayoutManager(linearLayoutManager);

        ExtensionAdapter extensionAdapter = new ExtensionAdapter(getListExtension());
        rcvExtension.setAdapter(extensionAdapter);

        RecyclerView.ItemDecoration itemDecoration = new MaterialDividerItemDecoration(mMainActivity, MaterialDividerItemDecoration.VERTICAL);
        rcvExtension.addItemDecoration(itemDecoration);

        return mView;
    }

    private List<Extension> getListExtension() {
        List<Extension> listExtension = new ArrayList<>();
        listExtension.add(new Extension("Hoá Đơn Tiền Điện", R.drawable.ic_electricity_bill));
        listExtension.add(new Extension("Hoá Đơn Tiền Nước", R.drawable.ic_water_bill));
        listExtension.add(new Extension("Lương Gross -> Net", R.drawable.ic_gross_net));
        listExtension.add(new Extension("Lương Net -> Gross", R.drawable.ic_gross_net));
        listExtension.add(new Extension("Quy Đổi Tiền Tệ", R.drawable.ic_currency));
        listExtension.add(new Extension("Quy Đổi Nhiệt Độ", R.drawable.ic_temperature));
        listExtension.add(new Extension("Quy Đổi Tốc Độ", R.drawable.ic_speed));
        listExtension.add(new Extension("Tính BMI", R.drawable.ic_bmi));
        return listExtension;
    }
}