/**
 * Copyright (C) 2020 WBPBP <potados99@gmail.com>
 *
 * This file is part of Preshoes (https://github.com/WBPBP).
 *
 * Preshoes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Preshoes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.wbpbp.preshoes.storage

import androidx.lifecycle.MutableLiveData
import org.wbpbp.preshoes.entity.Sample
import org.wbpbp.preshoes.repository.SensorStateRepository

class SensorStateRepositoryImpl : SensorStateRepository {
    private val _isLeftDeviceConnected = MutableLiveData<Boolean>()
    private val _isRightDeviceConnected = MutableLiveData<Boolean>()

    private val _leftDeviceSensorValue = MutableLiveData<Sample>()
    private val _rightDeviceSensorValue = MutableLiveData<Sample>()

    private val _isLeftDeviceCharging = MutableLiveData<Boolean>()
    private val _isRightDeviceCharging = MutableLiveData<Boolean>()

    private val _leftDeviceBatteryLevel = MutableLiveData<Int>()
    private val _rightDeviceBatteryLevel = MutableLiveData<Int>()

    override val isLeftDeviceConnected
        get() = _isLeftDeviceConnected
    override val isRightDeviceConnected
        get() = _isRightDeviceConnected

    override val leftDeviceSensorValue
        get() = _leftDeviceSensorValue
    override val rightDeviceSensorValue
        get() = _rightDeviceSensorValue

    override val isLeftDeviceCharging
        get() = _isLeftDeviceCharging
    override val isRightDeviceCharging
        get() = _isRightDeviceCharging

    override val leftDeviceBatteryLevel
        get() = _leftDeviceBatteryLevel
    override val rightDeviceBatteryLevel
        get() = _rightDeviceBatteryLevel
}