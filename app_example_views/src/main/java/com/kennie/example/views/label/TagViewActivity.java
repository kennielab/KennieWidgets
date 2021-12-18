package com.kennie.example.views.label;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseIntArray;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.example.views.R;
import com.kennie.example.views.label.drawable.CircleDrawable;
import com.kennie.example.views.label.drawable.MultiCircleDrawable;
import com.kennie.example.views.label.utils.ToastUtils;
import com.kennie.views.label.LabelTagView;


public class TagViewActivity extends AppCompatActivity {

    private LabelTagView mTagGoodOrBad;
    private LabelTagView mTagRightOrError;
    private LabelTagView mTagSmileOrCry;
    private LabelTagView mTagGetCode;
    private LabelTagView mTagSkip;
    private LabelTagView mTagSkip2;
    private LabelTagView mTagSingleCircle;
    private LabelTagView mTagMultiCircle;

    private int mCountNum = 5;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTagSkip2.setText("跳过 " + mCountNum);
            mTagSkip.setText((mCountNum--) + " 跳过");
            if (mCountNum == 0) {
                mCountNum = 5;
            }
            mHandler.postDelayed(this, 1000);
        }
    };
    private SparseIntArray mTimeSparse = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_label_tag_view);
        initView();
    }

    private void initView() {
        mTagGoodOrBad = (LabelTagView) findViewById(R.id.tag_good_or_bad);
        mTagRightOrError = (LabelTagView) findViewById(R.id.tag_right_or_error);
        mTagSmileOrCry = (LabelTagView) findViewById(R.id.tag_smile_or_cry);
        mTagGetCode = (LabelTagView) findViewById(R.id.tag_get_code);
        mTagSkip = (LabelTagView) findViewById(R.id.tag_skip);
        mTagSkip2 = (LabelTagView) findViewById(R.id.tag_skip_2);
        mTagGoodOrBad.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_good_or_bad)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagGoodOrBad.setChecked(!mTagGoodOrBad.isChecked());
                    }
                }, 2000);
            }
        });
        mTagRightOrError.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_right_or_error)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagRightOrError.setChecked(!mTagRightOrError.isChecked());
                    }
                }, 2000);
            }
        });
        mTagSmileOrCry.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                if (_isClickedNow(R.id.tag_smile_or_cry)) {
                    return;
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTagSmileOrCry.setChecked(!mTagSmileOrCry.isChecked());
                    }
                }, 2000);
            }
        });

        mTagGetCode.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                if (!mTagGetCode.isChecked()) {
                    mTagGetCode.setChecked(true);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTagGetCode.setChecked(false);
                            mTagGetCode.setText("重新获取");
                        }
                    } , 2000);

//                    RxHelper.countdown(10)
//                            .compose(TagViewActivity.this.<Integer>bindUntilEvent(ActivityEvent.DESTROY))
//                            .subscribe(new Subscriber<Integer>() {
//                                @Override
//                                public void onCompleted() {
//                                    mTagGetCode.setChecked(false);
//                                    mTagGetCode.setText("重新获取");
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    mTagGetCode.setChecked(false);
//                                    mTagGetCode.setText("重新获取");
//                                }
//
//                                @Override
//                                public void onNext(Integer time) {
//                                    mTagGetCode.setTextChecked("已发送(" + time + ")");
//                                }
//                            });
                }
            }
        });

        mTagSkip.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                ToastUtils.showToast(text);
            }
        });
        mTagSkip2.setTagClickListener(new LabelTagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text, @LabelTagView.TagMode int tagMode) {
                ToastUtils.showToast(text);
            }
        });
        mHandler.postDelayed(mRunnable, 1000);
        //
        mTagSingleCircle = (LabelTagView) findViewById(R.id.tag_single_circle);
        mTagSingleCircle.setDecorateIcon(new CircleDrawable());
        mTagMultiCircle = (LabelTagView) findViewById(R.id.tag_multi_circle);
        mTagMultiCircle.setDecorateIcon(new MultiCircleDrawable());
    }

    private boolean _isClickedNow(int id) {
        int lastTime = mTimeSparse.get(id);
        boolean isClickedNow = false;
        int curTime = (int) (System.currentTimeMillis() % 1000000);
        if (curTime - lastTime < 2000) {
            isClickedNow = true;
        }
        mTimeSparse.put(id, curTime);
        return isClickedNow;
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
