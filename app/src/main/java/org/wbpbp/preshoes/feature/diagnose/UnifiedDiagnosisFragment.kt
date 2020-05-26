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

import android.view.View
import org.koin.android.ext.android.inject
import org.wbpbp.preshoes.R
import org.wbpbp.preshoes.common.base.BaseFragment
import org.wbpbp.preshoes.common.extension.getViewModel
import org.wbpbp.preshoes.common.extension.observe
import org.wbpbp.preshoes.databinding.UnifiedDiagnosisFragmentBinding
import org.wbpbp.preshoes.service.FakeDataGenerator

class UnifiedDiagnosisFragment : BaseFragment<UnifiedDiagnosisFragmentBinding>() {
    override val viewModel: UnifiedDiagnosisViewModel by getViewModel()
    private val generator: FakeDataGenerator by inject()

    override fun getLayoutRes() = R.layout.unified_diagnosis_fragment

    override fun initView(root: View) {
        generator.state = FakeDataGenerator.STATE_STANDING
    }

    override fun initBinding(binding: UnifiedDiagnosisFragmentBinding) {
        binding.vm = viewModel.apply {
            observe(navigateUpEvent) {
                activity?.onBackPressed()
            }
        }
    }

    override fun onStop() {
        super.onStop()

        // Diagnosis session must be cleared when the fragments view is lost:
        // The session contains reference of the view.
        viewModel.clearSession()
    }
}