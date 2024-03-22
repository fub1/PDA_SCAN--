package com.crtyiot.signalscan.ui.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// 注册_scanData作为state，用于存储扫描数据
class ScanViewModel : ViewModel() {
    // 定义4个输入框的state1-4
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

    fun submitScanData() {
        _scanData.value = ""
        _scanResultlist.value = listOf(" ",""," ","")
        _scanstepindex.value = 0
        scanning.value = true
    }
}