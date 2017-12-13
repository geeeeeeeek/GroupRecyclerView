package com.geeeeeeeek.grouprecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by geeeeeeeek
 * Date: 13/12/2017
 * Time: 9:35 PM
 */

public class GroupItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int mTitleHeight;   //分隔栏高度
    private Paint mPaint;  //画背景的画笔
    private Paint mTextPaint;//画文字的画笔
    private Rect mBounds;//文字边界
    private int mTextSize;
    private int mTextColor;
    private int mBackgroundColor;
    private DecorationListener mListener;

    public GroupItemDecoration(Context context,DecorationListener listener){
        mContext = context;
        mTitleHeight = 100;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.GRAY);
        mBounds = new Rect();
        mListener = listener;

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);//抗锯齿
        mTextPaint.setTextSize(30);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (position == 0) {//等于0肯定要有分组title的
                drawTitleArea(c, left, right, child, params, position);
            }else {//其他的通过判断
                if (mListener.getType(position)!= mListener.getType(position-1)) {
                    //且跟前一个tag不一样了，说明是新的分组，也要title
                    drawTitleArea(c, left, right, child, params, position);
                }
            }
        }
    }

    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        //绘制文字
        mTextPaint.getTextBounds(mListener.getTypeText(position), 0,mListener.getTypeText(position).length(), mBounds);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        //文字baseline计算方法： 矩形区域的top + bottom - fontMetrics.bottom - fontMetrics.top 除以 2
        int baseline = ( child.getTop() - mTitleHeight + child.getTop() - fontMetrics.bottom - fontMetrics.top) / 2;
        c.drawText(mListener.getTypeText(position), child.getPaddingLeft(), baseline, mTextPaint);
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //获得可见的第一个view的position
        int pos = ((LinearLayoutManager)(parent.getLayoutManager())).findFirstVisibleItemPosition();
        //要绘制的文字
        String tag = mListener.getTypeText(pos);
        //获得在屏幕上能看到的第一个view
        View child = parent.getChildAt(0);

        if(mListener.getType(pos)!= mListener.getType(pos+1)){
            //这是最后一个
            int bottom = child.getBottom();
            if(bottom<mTitleHeight){
                //底部小于分隔栏的高度了  说明已经上去了
                //绘制背景  随着最后一个的bottom一起向上移动
                //为了简洁我就没写各边的padding了
                c.drawRect(0, 0, parent.getRight(), bottom, mPaint);
                mPaint.getTextBounds(tag, 0, tag.length(), mBounds);

                //文字
                Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
                int baseline = (bottom + bottom -mTitleHeight - fontMetrics.bottom - fontMetrics.top) / 2;
                c.drawText(tag,child.getPaddingLeft(),baseline,mTextPaint);
                return;
            }
        }
        //如果不是最后一个 那就直接绘制在RecyclerView的顶部！
        //分隔线的背景
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);

        //分组的文字
        mTextPaint.getTextBounds(tag, 0, tag.length(), mBounds);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (mTitleHeight - fontMetrics.bottom - fontMetrics.top) / 2;
        c.drawText(tag, child.getPaddingLeft(),baseline,   mTextPaint);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position =  parent.getChildLayoutPosition(view);
        if (position == 0) {//第0个肯定是新的一组
            outRect.set(0, mTitleHeight, 0, 0);
        } else {//其他的通过判断
            if (mListener.getType(position)!= mListener.getType(position-1)) {//通过接口来获得类型
                outRect.set(0, mTitleHeight, 0, 0);//跟前一个tag不一样了，说明是新的分类，也要title
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }

    }


    public interface DecorationListener{
        int getType(int position);          //获得分组的type
        String getTypeText(int position);   //获得该组的文字信息
    }
}
