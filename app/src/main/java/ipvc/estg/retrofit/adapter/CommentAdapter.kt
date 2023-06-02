package ipvc.estg.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.retrofit.R
import ipvc.estg.retrofit.api.Comment


class CommentAdapter(val comments: List<Comment>): RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerline_comments, parent, false)
        return CommentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        return holder.bind(comments[position])
    }
}

class CommentsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val name: TextView = itemView.findViewById(R.id.name)
    private val email:TextView = itemView.findViewById(R.id.email)
    private val body:TextView = itemView.findViewById(R.id.body)
    private val id:TextView = itemView.findViewById(R.id.id)

    fun bind(comment: Comment) {
        name.text = "Name: " + comment.name
        body.text = "Body: " + comment.body
        email.text = "Email: " + comment.email
        id.text = "ID: " + comment.id.toString()
    }

}