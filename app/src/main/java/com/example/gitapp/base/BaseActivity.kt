package com.example.gitapp.base


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity(),
    BaseFragment.Callback {
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mViewDataBinding?.let {
                ViewCompat.setOnApplyWindowInsetsListener(it.root) { _, insets ->
                    insets.consumeSystemWindowInsets()
                }
            }
        }


    }

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V
    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    open fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken,
                0
            )
        }
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String?) {
    }

    open fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = mViewModel ?: getViewModel()
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }


}