/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils.item_decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.roxiemobiletestapp.core.utils.extensions.dp

class VerticalItemDecoration(
    private val includeTopPadding: Boolean = false,
    private val verticalSpacing: Int = KEY_DEFAULT_VERTICAL_SPACING,
    private val horizontalSpacing: Int = KEY_DEFAULT_HORIZONTAL_SPACING,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount

        when (position) {
            0 -> {
                outRect.top = if (includeTopPadding) verticalSpacing.dp else 0
                outRect.left = horizontalSpacing.dp
                outRect.right = horizontalSpacing.dp
            }
            itemCount?.minus(1) -> {
                outRect.bottom = verticalSpacing.dp
                outRect.top = verticalSpacing.dp
                outRect.left = horizontalSpacing.dp
                outRect.right = horizontalSpacing.dp
            }
            else -> {
                outRect.top = verticalSpacing.dp
                outRect.left = horizontalSpacing.dp
                outRect.right = horizontalSpacing.dp
            }
        }
    }

    companion object {
        private const val KEY_DEFAULT_VERTICAL_SPACING = 16
        private const val KEY_DEFAULT_HORIZONTAL_SPACING = 20
    }
}
