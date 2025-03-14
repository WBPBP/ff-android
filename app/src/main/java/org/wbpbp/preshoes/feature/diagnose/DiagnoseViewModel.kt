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

package org.wbpbp.preshoes.feature.diagnose

import org.koin.core.inject
import org.wbpbp.preshoes.R
import org.wbpbp.preshoes.common.base.BaseViewModel
import org.wbpbp.preshoes.repository.SensorDeviceStateRepository
import org.wbpbp.preshoes.util.Alert
import org.wbpbp.preshoes.util.SingleLiveEvent

class DiagnoseViewModel : BaseViewModel() {
    private val sensorDeviceStateRepo: SensorDeviceStateRepository by inject()

    val navigateEvent = SingleLiveEvent<Int>()

    fun startUnifiedDiagnosis() {
        val readyToGo = sensorDeviceStateRepo.allConnected.value ?: false

        if (!readyToGo) {
            Alert.usual(R.string.fail_not_paired)
            return
        }

        navigateEvent.postValue(R.id.action_unified_diagnosis)
    }
}