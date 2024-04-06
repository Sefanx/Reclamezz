package com.example.reclamezz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "MY_DATABSE.db";
    private static final int DATABSE_VERSION = 1;
    private static final String TABLE_NAME ="reclamation_library";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_ADRESSE ="adresse_reclamation";
    private static final String COLUMN_DESCRIPTION ="description_reclamation";

    private static final String TABLE_NAME_S ="suggestion_library";
    private static final String COLUMN_ID_S ="_idS";
    private static final String COLUMN_ADRESSE_S ="adresse_suggestion";
    private static final String COLUMN_DESCRIPTION_S ="description_suggestion";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_ADRESSE + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        String query_S =
                "CREATE TABLE " + TABLE_NAME_S +
                        " (" + COLUMN_ID_S + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_ADRESSE_S + " TEXT, " +
                        COLUMN_DESCRIPTION_S + " TEXT);";
        db.execSQL(query);
        db.execSQL(query_S);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_S);
        onCreate(db);

    }

    void addSuggestion(String adresse, String description){
        if(adresse.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Erreur ,remplir les champs au-dessus", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(" + COLUMN_ID_S + ") FROM " + TABLE_NAME_S, null);
        int maxId = 0;
        if (cursor != null && cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
            cursor.close();
        }
        // Increment the maximum ID value by 1
        int newId = maxId + 1;
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ID_S, newId);
        cv.put(COLUMN_ADRESSE_S, adresse);
        cv.put(COLUMN_DESCRIPTION_S, description);
        long result=db.insert(TABLE_NAME_S,null,cv);
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    void addReclamation(String adresse, String description){
        if(adresse.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Erreur ,remplir les champs au-dessus", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db=this.getWritableDatabase();
        // Get the maximum ID value from the reclamation_library table
        Cursor cursor = db.rawQuery("SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME, null);
        int maxId = 0;
        if (cursor != null && cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
            cursor.close();
        }

        // Increment the maximum ID value by 1
        int newId = maxId + 1;
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_ID, newId);
        cv.put(COLUMN_ADRESSE, adresse);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllDataS(){
        String query_S = "SELECT * FROM "+ TABLE_NAME_S;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = null;
        if (db != null){
            cursor = db.rawQuery(query_S, null);
        }
        return cursor;
    }
    void updateDataRec(String row_id, String adresse, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ADRESSE, adresse);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void updateDataSugg(String row_id, String adresse, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ADRESSE_S, adresse);
        cv.put(COLUMN_DESCRIPTION_S, description);
        long result = db.update(TABLE_NAME_S, cv,COLUMN_ID_S + "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            updateIdsAfterDeleteRec();
        }
    }
    void deleteOneRowS(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_S, COLUMN_ID_S+ "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            updateIdsAfterDeleteSugg();
        }
    }
    void resetIds(String tableName, String idColumnName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(idColumnName);
            int id = 1;

            do {
                int currentId = cursor.getInt(idIndex);
                if (currentId != id) {
                    ContentValues cv = new ContentValues();
                    cv.put(idColumnName, id);
                    db.update(tableName, cv, idColumnName + "=?", new String[]{String.valueOf(currentId)});
                }
                id++;
            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    // Call this method after deleting a row from suggestion_library table
    void updateIdsAfterDeleteSugg() {
        resetIds(TABLE_NAME_S, COLUMN_ID_S);
    }
    void updateIdsAfterDeleteRec() {
        resetIds(TABLE_NAME, COLUMN_ID);
    }
}
