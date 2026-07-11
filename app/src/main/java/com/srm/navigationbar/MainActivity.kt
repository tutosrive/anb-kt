/*
 * Copyright (c) 2026 tutosrive. All rights reserved.
 *
 * Author: tutosrive
 * GitHub: https://github.com/tutosrive
 *
 * This source code is PROPRIETARY and CONFIDENTIAL.
 * Unauthorized copying, modification, or distribution of this file,
 * via any medium, is strictly prohibited.
 *
 * This software is provided "as is", without warranty of any kind.
 * In no event shall the author be liable for any claim or damages.
 */

package com.srm.navigationbar

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.srm.navigationbar.utils.colorAnimation

class MainActivity : AppCompatActivity() {
    private val layoutView: View by lazy { findViewById<View>(R.id.main) }
    private val titleText: TextView by lazy { findViewById<TextView>(R.id.titleApp) }
    private val bottomBar: ChipNavigationBar by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }

    private var lastColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lastColor = (layoutView.background as ColorDrawable).color

        bottomBar.setOnItemSelectedListener { id ->
            val selected = when (id) {
                R.id.home1 -> R.color.home to "Home"
                R.id.all_commands -> R.color.all_commands to "All Commands"
                R.id.favorites -> R.color.favorites to "Favorites"
                R.id.settings -> R.color.settings to "Settings"
                else -> R.color.white to ""
            }

            val newColor = ContextCompat.getColor(this@MainActivity, selected.first)
            layoutView.colorAnimation(lastColor, newColor)
            lastColor = newColor

            titleText.text = selected.second
        }

        if (savedInstanceState == null) {
            bottomBar.showBadge(R.id.home1)
            bottomBar.showBadge(R.id.favorites, 34)
            bottomBar.showBadge(R.id.settings, 12)
        }
    }
}