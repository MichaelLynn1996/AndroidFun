package xyz.sealynn.androidfun.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import xyz.sealynn.androidfun.R;

/**
 * @author Michael Lynn
 * @title: RecyclerFragment
 * @projectName AndroidFun
 * @description: TODO
 * @date 2019/4/1012:49
 */
public class RecyclerFragment extends Fragment {

    RecyclerView mRecycler;
    AppCompatTextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler, container, false);

        mRecycler = root.findViewById(R.id.recycler_view);
        textView = root.findViewById(R.id.text);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
