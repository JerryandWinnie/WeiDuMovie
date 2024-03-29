package com.example.john.greendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bw.movie.model.entity.JsonBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "JSON_BEAN".
*/
public class JsonBeanDao extends AbstractDao<JsonBean, Long> {

    public static final String TABLENAME = "JSON_BEAN";

    /**
     * Properties of entity JsonBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property BannerJson = new Property(1, String.class, "bannerJson", false, "BANNER_JSON");
        public final static Property AtHotJson = new Property(2, String.class, "atHotJson", false, "AT_HOT_JSON");
        public final static Property SoonJson = new Property(3, String.class, "soonJson", false, "SOON_JSON");
        public final static Property HotJson = new Property(4, String.class, "hotJson", false, "HOT_JSON");
    }


    public JsonBeanDao(DaoConfig config) {
        super(config);
    }
    
    public JsonBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"JSON_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"BANNER_JSON\" TEXT," + // 1: bannerJson
                "\"AT_HOT_JSON\" TEXT," + // 2: atHotJson
                "\"SOON_JSON\" TEXT," + // 3: soonJson
                "\"HOT_JSON\" TEXT);"); // 4: hotJson
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"JSON_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, JsonBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String bannerJson = entity.getBannerJson();
        if (bannerJson != null) {
            stmt.bindString(2, bannerJson);
        }
 
        String atHotJson = entity.getAtHotJson();
        if (atHotJson != null) {
            stmt.bindString(3, atHotJson);
        }
 
        String soonJson = entity.getSoonJson();
        if (soonJson != null) {
            stmt.bindString(4, soonJson);
        }
 
        String hotJson = entity.getHotJson();
        if (hotJson != null) {
            stmt.bindString(5, hotJson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, JsonBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String bannerJson = entity.getBannerJson();
        if (bannerJson != null) {
            stmt.bindString(2, bannerJson);
        }
 
        String atHotJson = entity.getAtHotJson();
        if (atHotJson != null) {
            stmt.bindString(3, atHotJson);
        }
 
        String soonJson = entity.getSoonJson();
        if (soonJson != null) {
            stmt.bindString(4, soonJson);
        }
 
        String hotJson = entity.getHotJson();
        if (hotJson != null) {
            stmt.bindString(5, hotJson);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public JsonBean readEntity(Cursor cursor, int offset) {
        JsonBean entity = new JsonBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bannerJson
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // atHotJson
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // soonJson
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // hotJson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, JsonBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBannerJson(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAtHotJson(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSoonJson(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHotJson(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(JsonBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(JsonBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(JsonBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
