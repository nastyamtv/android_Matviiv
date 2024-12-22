import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "TestDB"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "MyTable"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
    }

    private val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT UNIQUE);
    """

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)

        // Вставка початкових даних
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME) VALUES ('Item 1')")
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME) VALUES ('Item 2')")
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME) VALUES ('Item 3')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
