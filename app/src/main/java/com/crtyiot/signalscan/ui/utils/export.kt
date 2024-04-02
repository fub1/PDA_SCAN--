import android.content.Context
import android.os.Environment
import com.crtyiot.signalscan.data.repository.ScanDataRepository
import com.crtyiot.signalscan.data.source.local.model.ScanData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

object CSVExportUtils {
    fun exportDataToCSV(scope: CoroutineScope, repository: ScanDataRepository, context: Context) {
        scope.launch {
            // Use withContext to switch to Dispatchers.IO for file operations
            withContext(Dispatchers.IO) {
                val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "scan_data.csv")
                FileOutputStream(file).use { fos ->
                    OutputStreamWriter(fos).use { writer ->
                        // Write the header
                        writer.write("VDA Mat,CMS Mat,VDA Pkg,Submit Time,Task Number\n")

                        // Collect data from the flow and write each row of data
                        repository.allScanData.collect { scanData ->
                            val row = "${scanData.},${scanData.cmsMat},${scanData.vdaPkg},${scanData.submitTime},${scanData.taskNumber}\n"
                            writer.write(row)
                        }
                    }
                }
            }
        }
    }
}