package com.crtyiot.signalscan.ui.screen

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.asStateFlow

// 注册_scanData作为state，用于存储扫描数据
class ScanViewModel : ViewModel() {
    // 定义4个输入框的state1-4
    private val _scanDatalist = List(4) { MutableStateFlow<String>("") }
    val scanDatalist = _scanDatalist.map { it.asStateFlow() }
    private val _scanstatus = MutableStateFlow<Boolean>(true)
    val scanstatus = _scanstatus.asStateFlow()

    // 定义扫码步骤
    private val _scanStep = MutableStateFlow<Int>(1)
    val scanStep = _scanStep.asStateFlow()

    fun addScanData(data: String) {
        // 索引从0开始
        val index = _scanStep.value - 1
        // 向当前输入框对应的state中赋值
        if (scanstatus.value) {
            _scanDatalist[index].value = data
        }

        // 更新扫描步骤
        if (_scanStep.value == 4) {
            // 如果当前是第4步，重置为第1步
            _scanStep.value = 1
            // 更新扫描状态为false
            _scanstatus.value = false
        } else {
            // 否则，进入下一步
            _scanStep.value += 1
        }
    }

    fun resetScanData() {
        // 重置所有输入框的值
        _scanDatalist.forEach { it.value = "" }
        // 重置扫描步骤
        _scanStep.value = 1
        // 重置扫描状态
        _scanstatus.value = true
    }
}