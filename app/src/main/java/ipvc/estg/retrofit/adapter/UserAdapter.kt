package ipvc.estg.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.retrofit.R
import ipvc.estg.retrofit.api.User


class UserAdapter(val users: List<User>): RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerline, parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        return holder.bind(users[position])
    }
}

class UsersViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val website: TextView = itemView.findViewById(R.id.website)
    private val companyName:TextView = itemView.findViewById(R.id.companyName)
    private val coords:TextView = itemView.findViewById(R.id.coords)

    fun bind(user: User) {
        website.text = user.website
        companyName.text = user.company.name
        coords.text = user.address.geo.lat + "; " + user.address.geo.lng
    }

}