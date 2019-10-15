package com.mmm.retail.helper

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.mmm.retail.R
import com.mmm.retail.RetailApplication
import com.mmm.retail.activity.CartActivity
import com.mmm.retail.extensions.gone
import com.mmm.retail.extensions.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.toolbar_custom_view.view.*

class BaseToolbar : Toolbar {
    private var isCartAvailable: Boolean = false
    private val compositeDisposable = CompositeDisposable()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.toolbar_custom_view, null)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view?.setPadding(0, 0, 20, 0)
        addView(view, params)

        cartLayout.setOnClickListener { v ->
            if (!isCartAvailable) {
                Snackbar.make(v, "Cart empty.", Snackbar.LENGTH_SHORT).show()
            } else {
                CartActivity.start(context)
            }
        }

        compositeDisposable.add(RetailApplication.app().event().toObservable()
                .subscribe { updateCartCount() })
    }

    private fun setCardCount(count: Int) {
        when {
            count == 0 -> {
                isCartAvailable = false
                txtCartCount.gone()
            }
            count > 9 -> {
                isCartAvailable = true
                txtCartCount.text = context!!.getString(R.string.more_card_count, 9)
                txtCartCount.visible()
            }
            else -> {
                isCartAvailable = true
                txtCartCount.text = count.toString()
                txtCartCount.visible()
            }
        }
    }

    fun updateCartCount() {
            compositeDisposable.add(RetailDatabase.getInstant(context).cartAccess().countCart()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.setCardCount(it) },
                        { it.printStackTrace() }))
    }

    fun onDestroyToolbar() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
