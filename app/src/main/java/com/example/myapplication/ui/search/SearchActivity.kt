package com.example.myapplication.ui.search

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.search.result.HotKeyFragment
import com.example.myapplication.ui.search.result.SearchResultFragment
import com.example.myapplication.utils.KeyBoardUtils
import com.pzj.wanandroid_kotlin.base.BaseVMActivity
import com.pzj.wanandroid_kotlin.base.BaseVMFragment
import kotlinx.android.synthetic.main.activity_search.*


/**
 * 搜索界面
 */
class SearchActivity :BaseVMActivity() {


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }


    override fun initView() {
        super.initView()
        addFragment(HotKeyFragment.newInstance(), "HotKeyFragment")

      search.requestFocus()
        cancel.setOnClickListener {
            finish()
        }

      search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (getInputData() == null) {
                        return true
                    }

                    //隐藏光标和软键盘
                   search.clearFocus()
                    KeyBoardUtils.hideKeyboard(search)
                    addFragment(
                        SearchResultFragment.newInstance(getInputData()!!),
                        "SearchResultFragment"
                    )
                    return true
                }

                return false
            }

        })
    }

    override fun initData() {

    }

    override fun startDataObserver() {

    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.container, fragment, tag)
        beginTransaction.commit()
    }
    private fun getInputData(): String? {
        val data = search.text.toString().trim()
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(this, "请输入后再查询", Toast.LENGTH_SHORT).show()
            return null
        }
        return data
    }

    fun setHotKeyInputSearch(hotKey: String) {
      search.setText(hotKey)
    }

}