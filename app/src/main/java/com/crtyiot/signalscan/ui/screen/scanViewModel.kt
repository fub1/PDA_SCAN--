package com.crtyiot.signalscan.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.crtyiot.signalscan.MainApplication
import com.crtyiot.signalscan.data.repository.ScanDataRepository
import com.crtyiot.signalscan.data.source.local.model.ScanData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// 注册_scanData作为state，用于存储扫描数据
class ScanViewModel(private val repository: ScanDataRepository) : ViewModel() {
    // 从数据层获取数据
    val allScanData = repository.allScanData
    // 定义错误信息state
    private val _errormsg = MutableStateFlow<String?>(null)
    val errormsg : StateFlow<String?> = _errormsg
    // 定义4个输入框的state1-3
    private val _scanData = MutableStateFlow("")
    val scanData : StateFlow<String> = _scanData
    // 定义扫码状态
    private val _scanning = MutableStateFlow(true)
    val scanning : MutableStateFlow<Boolean> = _scanning
    // 扫码结果列表、4个元素
    private val _scanResultlist = MutableStateFlow<List<String>>(listOf(" ",""," ",""))
    val scanResultlist : StateFlow<List<String>> = _scanResultlist
    private val _scanstepindex = MutableStateFlow(0)
    val scanstepindex : StateFlow<Int> = _scanstepindex

    fun addScanData(data: String) {
    // Material mapping
    val materialMap = mapOf(
        "T721574" to "WE170305500",
        "T566424" to "RI170205700",
        "T566426" to "RI170205800",
        "T566434" to "RI170205900",
        "T566436" to "RI170206000",
        "T457016" to "LP170107700",
        "T457017" to "RI170507800",
        "T457015" to "MA170320800",
        "T457013" to "WE170706800",
        "T457015" to "MA170320800",
        "T457016" to "LP170107700",
        "T457017" to "RI170507800",
        "T457020" to "RI170407500",
        "T457021" to "RI170407600",
        "T457028" to "RI170507100",
        "T457029" to "RI170507200"
    )

    // Check data based on the value of scanstepindex
    when (_scanstepindex.value) {
        0 -> {
            // Check if the data is cms mat
            if (!materialMap.containsValue(data)) {
                // If the data is invalid, update the error message and return
                _errormsg.value = "CMS物料号错误"
                return
            }
        }
        1 -> {
            // Check if the data is the corresponding vda mat of cms mat
            val previousData = _scanResultlist.value[0]
            if (materialMap[previousData] != data) {
                // If the data is invalid, update the error message and return
                _errormsg.value = "客户物料号错误"
                return
            }
        }
        2 -> {
            // The data of this step should be a 7-digit pure number
            if (data.length != 7 || !data.all { it.isDigit() }) {
                // If the data is invalid, update the error message and return
                _errormsg.value = "VDA序列号校验失败"
                return
            }
        }
    }

    // If the data is valid, continue the original processing logic...
    val tmpScanList = _scanResultlist.value.toMutableList()
    tmpScanList[_scanstepindex.value] = data
    _scanResultlist.value = tmpScanList
    _scanstepindex.value += 1
    if (_scanstepindex.value == 3) {
        _scanning.value = false
    }
}




    fun resetScanData() {
        // 重置所有输入框的值
        _scanData.value = ""
        _scanResultlist.value = listOf(" ",""," ","")
        _scanstepindex.value = 0
        scanning.value = true
        _errormsg.value = null
    }
    // 用于回退扫码，扫错条码时的回退操作
    fun backScanData() {
        if (_scanstepindex.value > 0) {
            _scanstepindex.value -= 1
            val tmpScanList = _scanResultlist.value.toMutableList()
            tmpScanList[_scanstepindex.value] = " "
            _scanResultlist.value = tmpScanList
            _scanning.value = true
            _errormsg.value = null
        }
    }

    fun submitScanData() {
    val vdaMat = _scanResultlist.value[0]
    val cmsMat = _scanResultlist.value[1]
    val vdaPkg = _scanResultlist.value[2]
    val submitTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    val taskNumber = "Your Task Number" // 你需要根据你的需求来生成任务号

    val scanData = ScanData(vdaMat = vdaMat, cmsMat = cmsMat, vdaPkg = vdaPkg, submitTime = submitTime, taskNumber = taskNumber)
    viewModelScope.launch {
        repository.insert(scanData)
    }

    _scanData.value = ""
    _scanResultlist.value = listOf(" ",""," ","")
    _scanstepindex.value = 0
    scanning.value = true
}


    fun deleteScanData(scanData: ScanData) {
        viewModelScope.launch {
            repository.delete(scanData)
        }
    }

    fun observeScanData() = viewModelScope.launch {
        repository.allScanData.collect { scanDataList ->
            // 在这里处理扫描数据的变化
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val scanDataRepository = application.container.scanDataRepository
                ScanViewModel(scanDataRepository)
            }
        }
    }
}
