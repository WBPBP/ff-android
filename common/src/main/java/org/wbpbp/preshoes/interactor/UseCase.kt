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

package org.wbpbp.preshoes.interactor

import android.os.Handler
import android.os.Looper
import org.wbpbp.preshoes.functional.Result
import timber.log.Timber

/**
 * Abstract class for Use Case (Interactor in terms of Clean Architecture).
 * Any use case in this application should implement this.
 */
abstract class UseCase<in Params, out Type> {
    abstract fun run(params: Params): Result<Type>

    /**
     * Use thread instead of coroutine because it ruins Realm.
     */
    operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {
        Thread {
            try {
                Timber.v("UseCase ${this::class.java.name} running on ${Thread.currentThread().name}")
                val result = run(params)
                Handler(Looper.getMainLooper()).post { onResult(result) }
            } catch (e: Exception) {
                Timber.w("Exception inside another thread.")
                Timber.w(e)
            }
        }.start()
    }
}