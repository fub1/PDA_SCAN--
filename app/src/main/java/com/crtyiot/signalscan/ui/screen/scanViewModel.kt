package com.crtyiot.signalscan.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
        // 用于更新输入框的值
        //_scanData.value = data
        // 测试用，用于反转扫码状态
        // scanning.value = !(scanning.value)
        // 用于写入扫码结果列表表格
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
    }
    // 用于回退扫码，扫错条码时的回退操作
    fun backScanData() {
        if (_scanstepindex.value > 0) {
            _scanstepindex.value -= 1
            val tmpScanList = _scanResultlist.value.toMutableList()
            tmpScanList[_scanstepindex.value] = " "
            _scanResultlist.value = tmpScanList
            _scanning.value = true
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



}

// 用于创建ViewModel实例。避免项目无法启动
class ScanViewModelFactory(private val repository: ScanDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}