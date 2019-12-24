package cuzhy.com.member

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cuzhy.com.model.User

class ListAdapte (val context: Context, val list: ArrayList<User>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        val userId = view.findViewById(R.id.user_id) as TextView
        val userEmail = view.findViewById(R.id.user_email) as TextView
        val userFirstName = view.findViewById(R.id.user_first_name) as TextView
        val userLastName = view.findViewById(R.id.user_last_name) as TextView
        val userAvatar = view.findViewById(R.id.user_avatar) as TextView

        userId.text = list[position].id.toString()
        userEmail.text = list[position].email
        userFirstName.text = list[position].firstName
        userLastName.text = list[position].lastName
        userAvatar.text = list[position].avatar

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}