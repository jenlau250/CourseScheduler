package com.jen.android.coursescheduler_jlau;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Course.class}, version = 1, exportSchema = true)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDao courseDao();

    private static volatile CourseDatabase INSTANCE;

    //To delete all content and repopulate the database whenever the app is started
    private static CourseDatabase.Callback sCourseDatabaseCallback =
            new CourseDatabase.Callback(){

        @Override
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CourseDao mDao;

        PopulateDbAsync(CourseDatabase db) {
            mDao = db.courseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Course course = new Course("Hello");
            mDao.insert(course);
            course = new Course("World");
            mDao.insert(course);
            return null;
        }

    }

    static CourseDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (CourseDatabase.class) {
                if(INSTANCE == null) {
                    //Create db here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabase.class, "course_database")
                            .addCallback(sCourseDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


}
