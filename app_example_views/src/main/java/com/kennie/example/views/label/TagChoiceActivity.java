package com.kennie.example.views.label;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.example.views.R;
import com.kennie.example.views.label.utils.TagWordFactory;
import com.kennie.example.views.label.utils.ToastUtils;
import com.kennie.views.label.LabelTagLayout;
import com.kennie.views.label.LabelTagView;

public class TagChoiceActivity extends AppCompatActivity implements LabelTagView.OnTagClickListener, LabelTagView.OnTagLongClickListener {

    private LabelTagLayout mTagLayout1;
    private LabelTagLayout mTagLayout2;
    private LabelTagLayout mTagLayout3;
    private LabelTagView mTagDel;
    private LabelTagView mTagAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_label_tag_choice);
        initView();
    }

    private void initView() {
        mTagLayout1 = (LabelTagLayout) findViewById(R.id.tag_layout_1);
        mTagLayout2 = (LabelTagLayout) findViewById(R.id.tag_layout_2);
        mTagLayout3 = (LabelTagLayout) findViewById(R.id.tag_layout_3);
        mTagDel = (LabelTagView) findViewById(R.id.tag_del);
        mTagAdd = (LabelTagView) findViewById(R.id.tag_add);
        mTagLayout1.setTagClickListener(this);
        mTagLayout1.setTagLongClickListener(this);
        mTagLayout2.setTagClickListener(this);
        mTagLayout2.setTagLongClickListener(this);
        mTagLayout3.setTagClickListener(this);
        mTagLayout3.setTagLongClickListener(this);

        mTagAdd.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                String word = TagWordFactory.provideTagWord();
                mTagLayout1.addTag(word);
                mTagLayout2.addTag(word);
                mTagLayout3.addTag(word);
            }
        });
        mTagDel.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                mTagLayout1.deleteCheckedTags();
                mTagLayout2.deleteCheckedTags();
                mTagLayout3.deleteCheckedTags();
            }
        });
    }

    @Override
    public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
        ToastUtils.showToast(text);
    }

    @Override
    public void onTagLongClick(int position, String text, @LabelTagView.TagMode int tagMode) {
        ToastUtils.showToast("长按:" + text);
    }
}
