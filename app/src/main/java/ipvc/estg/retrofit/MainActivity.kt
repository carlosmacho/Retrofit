package ipvc.estg.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.retrofit.adapter.CommentAdapter
import ipvc.estg.retrofit.adapter.UserAdapter
import ipvc.estg.retrofit.api.Comment
import ipvc.estg.retrofit.api.EndPoints
import ipvc.estg.retrofit.api.OutputPost
import ipvc.estg.retrofit.api.ServiceBuilder
import ipvc.estg.retrofit.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        //val call = request.getUsers()
        val call = request.getComments()

/*        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = UserAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CommentAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSingle(view: View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        //val call = request.getUserById(2)
        val call = request.getCommentById(2)

/*        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val c:User = response.body()!!
                    Toast.makeText(this@MainActivity, c.address.zipcode, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })*/

        call.enqueue(object : Callback<Comment>{
            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                if (response.isSuccessful){
                    val c:Comment = response.body()!!
                    Toast.makeText(this@MainActivity, c.email, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Comment>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun totalComments(view: View){
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getComments()

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    val totalComments = response.body()?.size ?: 0
                    Toast.makeText(this@MainActivity, "Total comments: $totalComments", Toast.LENGTH_SHORT).show()

                }
            }
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun getNameByEmail(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getNameByEmail("Hudson.Blick@ruben.biz")

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments: List<Comment>? = response.body()
                    if (!comments.isNullOrEmpty()) {
                        val comment: Comment = comments[0]
                        Toast.makeText(this@MainActivity, "Name: " + comment.name, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "No comments found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getEmailCountEndingWithCom(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getComments()

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments: List<Comment>? = response.body()
                    if (!comments.isNullOrEmpty()) {
                        val count = comments.count { it.email.endsWith(".com") }
                        Toast.makeText(this@MainActivity, "$count .com emails", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "No comments found", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun post(view: View) {
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postTest("teste")

        call.enqueue(object : Callback<OutputPost>{
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                if (response.isSuccessful){
                    val c: OutputPost = response.body()!!
                    Toast.makeText(this@MainActivity, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

