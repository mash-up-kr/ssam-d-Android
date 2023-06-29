import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleGapItemDecorator(
    private val gapSize: Int,
    private val orientation: SimpleGapDecoratorDirection
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        if (orientation == SimpleGapDecoratorDirection.VERTICAL) {
            outRect.top = if (itemPosition == 0) 0 else gapSize
            outRect.bottom = gapSize
        } else {
            outRect.left = if (itemPosition == 0) 0 else gapSize
            outRect.right = gapSize
        }
    }
}

enum class SimpleGapDecoratorDirection {
    HORIZONTAL,
    VERTICAL
}