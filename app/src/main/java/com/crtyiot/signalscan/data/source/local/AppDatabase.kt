import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.crtyiot.signalscan.data.source.local.Dao.ScanDataDao
import com.crtyiot.signalscan.data.source.local.model.ScanData

@Database(entities = [ScanData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scanDataDao(): ScanDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "scan_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}