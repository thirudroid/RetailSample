package com.mmm.retail.fragment


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.mmm.retail.R
import com.mmm.retail.adapter.ProductListAdapter
import com.mmm.retail.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsFragment : BaseFragment() {
    override fun setLayout() = R.layout.fragment_products

    private val viewModel by viewModel<ProductViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListView.setHasFixedSize(true)
        productListView.layoutManager = GridLayoutManager(activity, 2)
        productListView.adapter = ProductListAdapter(requireContext(), viewModel.getProducts(requireContext().applicationContext, arguments?.getString("KEY")?:""))
    }

    companion object {
        fun getInstant(key: String): ProductsFragment {
            val fragment = ProductsFragment()
            val bundle = Bundle()
            bundle.putString("KEY", key)
            fragment.arguments = bundle
            return fragment
        }
    }
}
