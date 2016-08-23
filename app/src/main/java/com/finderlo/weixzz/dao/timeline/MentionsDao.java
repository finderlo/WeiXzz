package com.finderlo.weixzz.dao.timeline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.finderlo.weixzz.Constants;
import com.finderlo.weixzz.dao.HttpClientUtils;
import com.finderlo.weixzz.dao.UrlConstants;
import com.finderlo.weixzz.dao.WeiboParameters;
import com.finderlo.weixzz.dao.timeline.BaseTimelineDao;
import com.finderlo.weixzz.database.Table.MentionsTable;
import com.finderlo.weixzz.model.model.StatusListModel;
import com.google.gson.Gson;

import static com.finderlo.weixzz.BuildConfig.DEBUG;


/**
 * Created by Finderlo on 2016/8/16.
 * Dao Data Access Object
 */
public class MentionsDao extends BaseTimelineDao<StatusListModel> {


    public MentionsDao(Context context) {
        super(context);
    }


    @Override
    public void cache() {
        mDatabase.beginTransaction();
        mDatabase.delete(MentionsTable.NAME," 1 = 1 ",null);
        ContentValues values = new ContentValues();
        values.put(MentionsTable.JSON,new Gson().toJson(mListModel));
        mDatabase.insert(MentionsTable.NAME,null,values);
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    @Override
    public StatusListModel load() {
        StatusListModel listModel ;
        listModel = getMentionsTimeline(Constants.HOME_TIMELINE_PAGE_SIZE,++mCurrentPage);
        return listModel;
    }

    private StatusListModel getMentionsTimeline(int count, int page) {
        WeiboParameters param = new WeiboParameters();
        param.put("count",count);
        param.put("page",page);

        try {
            String json = HttpClientUtils.doGetRequstWithAceesToken(UrlConstants.MENTIONS,param);
            return new Gson().fromJson(json,StatusListModel.class);
        } catch (Exception e) {
            if (DEBUG) {
                Log.d(TAG, "Can't get comment home timeline, " + e.getClass().getSimpleName());
                Log.d(TAG, Log.getStackTraceString(e));
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Cursor query() {
        return mDatabase.rawQuery("select * from "+MentionsTable.NAME,null);
    }

    @Override
    protected Class<? extends StatusListModel> getListClass() {
        return StatusListModel.class;
    }
}
